package com.sxmd.gateway.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import io.netty.util.internal.StringUtil;

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
        ByteBuf buf = (ByteBuf) msg;
        byte[] reg = new byte[buf.readableBytes()];
        buf.readBytes(reg);
        String body = new String(reg, "UTF-8");
        // 回复消息
        for (int i = 0; i < 200; i++) {
            String respMsg = "5A022B81A10A0000000000000000F0030904EC03020006303244303030323046410D0A3A3130303646303030323136393838343730363230323037354338323041303631303046303645424545310D0A3A3130303730303030413036393431314541313631303132383033444130373230323037353041323039330D0A3A3130303731303030453036313030463036334245413036383030323830344430444646384334314343430D0A3A3130303732303030303032304132363839303437413036383030323830344430444646384238314331390D0A3A3130303733303030303032304132363839303437303132334446463841383243303032313230303041380D0A3A3130303734303030464646373434464430383230323037353446463439363730413036313030463037420D0A3A3130303735303030343542454130363934313145413136313031323830434441453036393431314537350D0A3A3130303736303030453136313030323830324430303732303230373530344530303032303230373546380D0A3A3130303737303030304532303834463832303030303046303331424541303638303032383034443043430D0A3A3130303738303030444646383638314330303230413236383930343741303638303032383034443030390D0A3A3130303739303030444646383534314330303230413236383930343730313233444646383443324339450D0A3A3130303741303030303032313230303046464637313246443041323032303735344646343936373046420D0A3A3130303742303030413036313030463031334245413036393431314541313631303132383043444146450D0A3A3130303743303030453036393431314545313631303032383032443030393230323037353034453041330D0A3A3130303744303030303032303230373530453230383446383230303030304630464642444130363845360D0A3A3130303745303030303032383034443044464638313431433030323041323638393034374130363846440D0A3A3130303746303030303032383034443044464638463031423030323041323638393034373031323346360D0A3A3130303830303030444646384638324230303231323030304646463745304643304332303230373531410D0A3A3130303831303030363432304130363130304630453242444130363934313145413136313031323833310D0A3A3130303832303030314344414130363830303238303444304446463844343142303032304132363844450D0A3A3130303833303030393034374130363830303238303444304446463841433142303032304132363831350D0A3A3130303834303030393034373031323344464638423832423030323132303030464646374245464330329800A5";
            byte[] bytes = StringUtil.decodeHexDump(respMsg);
            ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
            cxf.writeAndFlush(byteBuf);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 客户端断开连接了
        ctx.channel().close();
        // 关闭服务端
        // System.out.println("服务端请求关闭");
        // ctx.channel().parent().close();
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
