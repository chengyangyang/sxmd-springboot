package com.sxmd.content.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
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
            future.syncUninterruptibly();
            future.channel().closeFuture().syncUninterruptibly();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("websocket服务启动失败");
        } finally {
            group.shutdownGracefully();
        }
    }


}