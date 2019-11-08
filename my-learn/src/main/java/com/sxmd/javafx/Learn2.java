package com.sxmd.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;

/**
 * Description:
 *
 * @author cy
 * @date 2019年11月08日 9:45
 * Version 1.0
 */
public class Learn2 extends Application {

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
        root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        root.setCenterShape(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
