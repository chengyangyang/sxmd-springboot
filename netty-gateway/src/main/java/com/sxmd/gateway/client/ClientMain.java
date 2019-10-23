package com.sxmd.gateway.client;

import com.sxmd.gateway.server.ServerMain;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * Description: 客户端
 *
 * @author cy
 * @date 2019年09月03日 8:51
 * Version 1.0
 */
public class ClientMain {

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            new Thread(new ClientThread()).start();
        }
    }

    static class ClientThread implements Runnable{
        @Override
        public void run() {
            connect("192.168.141.124",8754);
        }
        public void connect(String host,int port){
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(ServerMain.worker)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .attr(AttributeKey.valueOf("name"),"aaaa")
                        .handler(new ClientPipeLine());
                ChannelFuture sync = b.connect(host, port).sync();
                System.out.println(Thread.currentThread().getName() + ",客户端发起异步连接..........");
                /**等待客户端链路关闭*/
                sync.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                group.shutdownGracefully();
            }
        }
    }

}
