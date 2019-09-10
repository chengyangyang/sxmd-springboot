package com.sxmd.gateway.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.net.SocketAddress;

/**
 * Description: 服务端接收
 *
 * @author cy
 * @date 2019年09月03日 9:14
 * Version 1.0
 */
public class ServerInHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext cxf, Object msg) throws Exception {
        System.out.println("通道id为：" + cxf.channel().id().asLongText());
        Object name = cxf.channel().attr(AttributeKey.valueOf("name")).get();
        System.out.println("传递的数据为：" + name);
        ByteBuf buf = (ByteBuf) msg;
        byte[] reg = new byte[buf.readableBytes()];
        buf.readBytes(reg);
        String body = new String(reg, "UTF-8");
        System.out.println(Thread.currentThread().getName() + "服务端收到的消息：" + body);
        // 回复消息
        String respMsg = "你好，" + body + "，我收到了你的消息$";
        ByteBuf byteBuf = Unpooled.copiedBuffer(respMsg.getBytes());
        cxf.channel().attr(AttributeKey.valueOf("addredss")).set("abc");
        cxf.writeAndFlush(byteBuf);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 客户端断开连接了
        ctx.channel().close();
        // 关闭服务端
        System.out.println("服务端请求关闭");
        ctx.channel().parent().close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /**flush：将消息发送队列中的消息写入到 SocketChannel 中发送给对方，为了频繁的唤醒 Selector 进行消息发送
         * Netty 的 write 方法并不直接将消息写如 SocketChannel 中，调用 write 只是把待发送的消息放到发送缓存数组中，再通过调用 flush
         * 方法，将发送缓冲区的消息全部写入到 SocketChannel 中
         * */
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /**当发生异常时，关闭 ChannelHandlerContext，释放和它相关联的句柄等资源 */
        cause.printStackTrace();
        ctx.close();
    }

}
