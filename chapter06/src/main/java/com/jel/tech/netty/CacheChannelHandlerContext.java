package com.jel.tech.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
/**
 * 缓存一个ChannelHandlerContext,
 * Other advanced uses can be supported by caching a reference to a
 * ChannelHandlerContext for later use, which might take place
 *  outside any ChannelHandler methods and could even originate
 *  from a different thread. This listing shows this pattern
 *  being used to trigger an event.
 * @author jelex.xu
 * @date 2017年9月22日
 */
public class CacheChannelHandlerContext extends ChannelHandlerAdapter {

	private ChannelHandlerContext ctx;

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		this.ctx = ctx;
	}

	public void send(String msg) {
		ctx.writeAndFlush(msg);
	}


}
