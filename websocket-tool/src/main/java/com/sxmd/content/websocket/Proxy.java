package com.sxmd.content.websocket;

import com.sxmd.WebsocketToolApplication;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class Proxy {
    public static ChannelFuture future = null;
    public static void initFrontend(int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebsocketPipline(port));
            future = bootstrap.bind(inetSocketAddress);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess()){
                        WebsocketToolApplication.stageMainController.getCevice().appendText(WebsocketToolApplication.getPre() + "\nwebsocket 启动成功\n");
                    }
                }
            });
            future.syncUninterruptibly();
            future.channel().closeFuture().syncUninterruptibly();
            WebsocketToolApplication.stageMainController.getCevice().appendText(WebsocketToolApplication.getPre() + "\nwebsocket 关闭成功\n");
            future = null;
        } catch (Exception e) {
            e.printStackTrace();
            future = null;
            System.out.println("websocket服务启动失败");
        } finally {
            group.shutdownGracefully();
        }
    }


}