package com.manning.nettyinaction.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Listing 2.3 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:norman.maurer@googlemail.com">Norman Maurer</a>
 */
@Sharable
public class EchoClientHandler extends
        SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx,
        ByteBuf in) throws Exception {
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
        System.out.println("Client received: " + ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}
