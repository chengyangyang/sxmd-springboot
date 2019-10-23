package com.sxmd.gateway.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.apache.tomcat.util.buf.HexUtils;

/**
 * Description:
 *
 * @author cy
 * @date 2019年09月04日 9:20
 * Version 1.0
 */
public class ServerOutHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        ctx.writeAndFlush(msg,promise);
    }
}
