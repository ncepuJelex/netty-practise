package com.jel.tech.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Because the exception will continue to flow in the inbound direction (just as
 * with all inbound events), the ChannelInboundHandler that implements the
 * preceding logic is usually placed last in the ChannelPipeline.
 *
 * @author jelex.xu
 * @date 2017年9月22日
 */
public class InboundExceptionHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
