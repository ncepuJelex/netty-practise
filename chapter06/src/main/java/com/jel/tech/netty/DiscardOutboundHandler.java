package com.jel.tech.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

/**
 * On the outbound side,
 * if you handle a write() operation and discard a message,
 *  you’re responsible for releasing it
 * @author jelex.xu
 * @date 2017年9月21日
 */
public class DiscardOutboundHandler extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		ReferenceCountUtil.release(msg);
		/*
		 * 这句很重要！不然可能会有这样一种场景：
		 * ChannelFutureListener has not been notified
		 * about a message that has been handled
		 */
		promise.setSuccess(); //Notifies ChannelPromise that data was handled
	}


}
