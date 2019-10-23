package com.sxmd.gateway.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;

import java.net.SocketAddress;

/**
 * Description: 服务端
 *
 * @author cy
 * @date 2019年09月03日 8:51
 * Version 1.0
 */
public class ServerMain {

    public static EventLoopGroup worker = new NioEventLoopGroup();

    public static void main(String[] args){
        ServerMain.bind(8754);
    }

    public static void bind(int port) {
        EventLoopGroup boss = new NioEventLoopGroup();
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childOption(ChannelOption.SO_SNDBUF,2048)
                    .childAttr(AttributeKey.valueOf("name"),"123")
                    .childHandler(new ServerPipeLine());
            ChannelFuture f = boot.bind(port).sync();
            System.out.println("服务端开始监听，等待客户端连接");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
