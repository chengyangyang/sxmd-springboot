package com.sxmd.content.fx.main;

import com.sxmd.WebsocketToolApplication;
import com.sxmd.content.websocket.Proxy;
import com.sxmd.content.websocket.WebSocketServerHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Data;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

}
