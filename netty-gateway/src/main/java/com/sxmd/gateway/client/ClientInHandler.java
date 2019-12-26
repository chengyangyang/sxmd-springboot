package com.sxmd.gateway.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import io.netty.util.internal.StringUtil;

/**
 * Description:
 *
 * @author cy
 * @date 2019年09月03日 9:39
 * Version 1.0
 */
public class ClientInHandler extends SimpleChannelInboundHandler<Object> {

    private int count;

    public ClientInHandler() {
        System.out.println("客户端接收数据，初始化");
    }

    /**
     * 当客户端和服务端 TCP 链路建立成功之后，Netty 的 NIO 线程会调用 channelActive 方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户连接服务端成功");
        for (int i = 0; i < 2; i++) {
            String reqMsg = "我是客户端";
            ByteBuf reqByteBuf = Unpooled.copiedBuffer(reqMsg.getBytes());
            /**
             * writeBytes：将指定的源数组的数据传输到缓冲区
             * 调用 ChannelHandlerContext 的 writeAndFlush 方法将消息发送给服务器
             */
            ctx.writeAndFlush(reqByteBuf);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String s = ByteBufUtil.hexDump(buf).toUpperCase();
        System.out.println("服务端返回"+ count++ +"：" + s);
    }

    /**
     * 当发生异常时，打印异常 日志，释放客户端资源
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /**释放资源*/
        cause.printStackTrace();
        ctx.close();
    }

}
