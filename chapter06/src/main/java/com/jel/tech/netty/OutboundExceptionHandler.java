package com.jel.tech.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * The second option is to add a ChannelFutureListener to the ChannelPromise
 * that is passed as an argument to the ChannelOutboundHandler methods.
 * What happens if your ChannelOutboundHandler itself throws an exception?
 * In this case, Netty itself will notify any listeners that have been registered
 *  with the corre- sponding ChannelPromise.
 *  这种方式可能更方便些，但第一种方式更常用吧
 * @author jelex.xu
 * @date 2017年9月22日
 */
public class OutboundExceptionHandler extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		promise.addListener(new ChannelFutureListener() {

			public void operationComplete(ChannelFuture future) throws Exception {
				if(!future.isSuccess()) {
					future.cause().printStackTrace();
					future.channel().close();
				}
			}
		});
	}


}
