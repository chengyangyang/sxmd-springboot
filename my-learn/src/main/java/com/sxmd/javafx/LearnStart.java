package com.sxmd.javafx;

import javafx.application.Application;
import javafx.stage.Stage;

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
        primaryStage.setAlwaysOnTop(true); // 显示窗口的位置
        primaryStage.setFullScreenExitHint("xiana");
        primaryStage.show();
    }
}
