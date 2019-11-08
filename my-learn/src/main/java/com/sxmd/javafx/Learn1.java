package com.sxmd.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Description:
 *
 * @author cy
 * @date 2019年11月08日 9:45
 * Version 1.0
 */
public class Learn1 extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("/fxml/parent.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.load();
        AnchorPane root = (AnchorPane)fxmlLoader.getRoot();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Screen primary = Screen.getPrimary();
        double dpi = primary.getDpi();
        Rectangle2D bounds = primary.getBounds();
        Rectangle2D visualBounds = primary.getVisualBounds();
        double maxX = bounds.getMaxX();
        double maxY = bounds.getMaxY();
        double vmaxX = visualBounds.getMaxX();
        double vmaxY = visualBounds.getMaxY();
        System.out.println("屏幕dpi："+dpi);
        System.out.println("屏幕大小："+maxX+","+maxY);
        System.out.println("可视大小："+vmaxX+","+vmaxY);
        primaryStage.show();
    }
}
