package com.sxmd;

import com.sxmd.content.fx.main.StageMainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Description:
 *
 * @author cy
 * @date 2019年10月30日 8:55
 * Version 1.0
 */
public class WebsocketToolApplication extends Application {

    public static StageMainController stageMainController = null;
    @Override
    public void start(Stage primaryStage) throws Exception{
        URL resource = getClass().getResource("/fx/main/StageMain.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.load();
        stageMainController = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.getRoot());
        primaryStage.setScene(scene);
        // 窗口显示
        primaryStage.show();
        primaryStage.setResizable(false);
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

    public static void main(String[] args) {
        System.out.println("========程序开始启动=======");
        launch(args);
        System.out.println("完成");
    }
}
