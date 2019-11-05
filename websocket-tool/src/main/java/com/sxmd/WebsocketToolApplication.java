package com.sxmd;

import com.sxmd.content.fx.main.StageMainController;
import com.sxmd.content.utils.CheckUtil;
import com.sxmd.content.utils.ComputerInfoUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.prefs.Preferences;

/**
 * Description:
 *
 * @author cy
 * @date 2019年10月30日 8:55
 * Version 1.0
 */
public class WebsocketToolApplication extends Application {


    public static StageMainController stageMainController = null;
    public static boolean verify = false;
    @Override
    public void start(Stage primaryStage) {
        // 进行文件注册
        ComputerInfoUtil.init();
        // 进行校验
        Preferences preferences = Preferences.userNodeForPackage(WebsocketToolApplication.class);
        // preferences.putBoolean("verify", false);
        verify = preferences.getBoolean("verify", false);
        URL resource = getClass().getResource("/fx/main/StageMain.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stageMainController = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.getRoot());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/title.png")));
        // 窗口显示
        primaryStage.show();
        //primaryStage.setResizable(false);
        primaryStage.setTitle("websocket server tool");
        // 监听窗口的关闭
        primaryStage.setOnCloseRequest(x->{
            // 关闭线程
            Thread thread = StageMainController.thread;
            if(thread != null){
                thread.stop();
            }
        });
    }

    public static String getPre(){
       return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "--->";
    }

    public static void main(String[] args) {
        System.out.println("========程序开始启动=======");
        launch(args);
        System.out.println("完成");
    }
}
