package com.sxmd.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Description:
 *
 * @author cy
 * @date 2019年11月07日 18:17
 * Version 1.0
 */
public class LearnStart extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String path = getClass().getResource("/title.png").toURI().toString();
        System.out.println(path);
        primaryStage.setAlwaysOnTop(true); // 显示窗口的位置
        primaryStage.setFullScreenExitHint("提醒"); // 退出全屏提醒
        primaryStage.setResizable(true); // 是否可调整大小
        primaryStage.setTitle("标题"); // 标题
        primaryStage.setIconified(false); // 最小化
        primaryStage.getIcons().add(new Image(path));
        primaryStage.setFullScreen(true); // 铺满全屏
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }
}
