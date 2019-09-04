package com.sxmd.gateway.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


/**
 * Description:
 *
 * @author cy
 * @date 2019年09月03日 9:10
 * Version 1.0
 */
public class ServerPipeLine extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //以$为分隔符
        ByteBuf buf = Unpooled.copiedBuffer("$".getBytes());
        // 解决粘包的问题
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new DelimiterBasedFrameDecoder(2018,buf));
        // pipeline.addLast(new StringDecoder());
        pipeline.addLast(new ServerOutHandler());
        pipeline.addLast(new ServerInHandler());
    }
}
