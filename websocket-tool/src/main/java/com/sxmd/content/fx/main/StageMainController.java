package com.sxmd.content.fx.main;

import com.sxmd.WebsocketToolApplication;
import com.sxmd.content.utils.CheckUtil;
import com.sxmd.content.websocket.Proxy;
import com.sxmd.content.websocket.WebSocketServerHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.sxmd.content.utils.CheckUtil.usePulicKeyEncry;

/**
 * Description:   主窗口
 *
 * @author cy
 * @date 2019年10月30日 13:57
 * Version 1.0
 */
@Data
public class StageMainController {

    public Button but;
    public TextField contentPort;
    public TextArea sendmsg;
    public TextArea cevice;
    public TextArea sendmsghis;
    public static Thread thread = null;
    ScheduledExecutorService scheduledExecutorService = null;
    public Button lockmsg;
    public Button timing;
    public static String msg = "请进行注册，尊重创作";

    private boolean isClock = false;
    private boolean isTime = false;

    public void start(){
        if(Proxy.future != null){
            but.setText("监听");
            Proxy.future.channel().close();
        }else {
            String port = contentPort.getText();
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Proxy.initFrontend(Integer.valueOf(port));
                }
            });
            thread.start();
            but.setText("停止");
        }
    }

    public void sendMsg(){
        String text = sendmsg.getText();
        if(null == text || text.trim() == ""){
            return;
        }
        if(!WebsocketToolApplication.verify){
            text += msg;
        }
        WebSocketServerHandler.sendMessageToWebsocket(text);
        sendmsghis.appendText(WebsocketToolApplication.getPre() +"\n" +text +"\n");
        if(!isClock && !isTime){
            sendmsg.clear();
        }
    }

    public void clear(){
        cevice.clear();
    }
    // 定时
    public void timing() throws Exception{
        // 定时发送内容
        chanageTime();
        if(isTime){
            TimerTask timerTask = new TimerTask(){
                @Override
                public void run() {
                    sendMsg();
                }
            };
             scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleAtFixedRate(timerTask, 1,10, TimeUnit.SECONDS);
        }else if(scheduledExecutorService != null){
            scheduledExecutorService.shutdownNow();
            scheduledExecutorService = null;
        }
    }
    // 锁定内容
    public void lockmsg(){
        chanageClock();
    }

    public void clearsendmsg(){
        sendmsghis.clear();
    }


    public void chanageClock(){
        isClock = !isClock;
        if(isClock){
            lockmsg.setText("取消");
        }else {
            lockmsg.setText("锁定");
        }
    }

    public void chanageTime(){
        isTime = !isTime;
        if(isTime){
            timing.setText("取消");
        }else {
            timing.setText("定时");
        }
    }

    // 注册的点击事件
    public void registered() throws Exception{
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        String s = usePulicKeyEncry();
        TextArea label1 = new TextArea(s);
        TextArea label2 = new TextArea("请将顶部的注册码发送至邮箱 390518881@qq.com 获取验证码,每位10元");
        VBox vBox = new VBox();
        Button button = new Button("注册");
        button.setStyle("-fx-background-color: red;-fx-font-size: 20");
        TextArea label3 = new TextArea();
        label3.setPromptText("根据上面提示，获取验证码，并进行输入");
        vBox.getChildren().addAll(label3,button);
        ImageView imageView = new ImageView();
        imageView.setStyle("-fx-background-color: #EEE9BF");
        URI uri = StageMainController.class.getClassLoader().getResource("IMG_20191104_203644.jpg").toURI();
        System.out.println(uri.toString());
        Image image = new Image(uri.toString());
        imageView.setImage(image);
        imageView.setFitHeight(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
        label1.setEditable(false);
        label2.setEditable(false);
        borderPane.setTop(label1);
        borderPane.setLeft(label2);
        borderPane.setBottom(vBox);
        borderPane.setCenter(imageView);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean registered = false;
                if(!StringUtils.isEmpty(label3.getText())){
                    registered = CheckUtil.registered(label3.getText());
                }
                if(registered){
                    label3.setText("注册成功！关闭重新打开！");
                }else {
                    label3.setText("注册失败");
                }
            }
        });
    }
}
