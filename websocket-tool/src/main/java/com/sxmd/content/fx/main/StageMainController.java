package com.sxmd.content.fx.main;

import com.sxmd.content.websocket.Proxy;
import com.sxmd.content.websocket.WebSocketServerHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Data;

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
    public static Thread thread = null;

    public void start(){
        if(thread != null){
            thread.stop();
            thread = null;
            but.setText("监听");
            return;
        }
        String port = contentPort.getText();
         thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Proxy.initFrontend(Integer.valueOf(port));
            }
        });
        thread.start();
        but.setText("停止");
        System.out.println("==============服务启动成功=============");
    }

    public void sendMsg(){
        String text = sendmsg.getText();
        WebSocketServerHandler.sendMessageToWebsocket(text);
        sendmsg.clear();
    }

    public void clear(){
        cevice.clear();
    }



}
