package com.sxmd.gateway.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Description:
 *
 * @author cy
 * @date 2019年09月03日 9:35
 * Version 1.0
 */
public class ClientPipeLine extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //以$为分隔符
        ByteBuf buf = Unpooled.copiedBuffer("$".getBytes());
        ChannelPipeline pipeline = channel.pipeline();
        // 防止粘包的问题
        pipeline.addLast(new DelimiterBasedFrameDecoder(2018,buf));
        // pipeline.addLast(new StringDecoder());
        pipeline.addLast(new ClientInHandler());
    }
}
