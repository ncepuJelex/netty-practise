package com.jel.tech.netty;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 这个注解得要哦！如果你想让这个Handler用在多个ChannelHandlerContext中，
 * Because a ChannelHandler can belong to more than one ChannelPipeline,
 * it can be bound to multiple ChannelHandlerContext instances.
 * A ChannelHandler intended for this use must be annotated with @Sharable;
 * otherwise, attempting to add it to more than one ChannelPipeline
 *  will trigger an exception，
 *  这个Handler符合在多个pipeline中使用的要求，即有Sharable注解，并且doesn’t hold any state，
 *  下一个UnsharableHandler类是个不满足要求的例子，不是因为没有注解，而是有hold state.
 * @author jelex.xu
 * @date 2017年9月22日
 */
@Sharable
public class SharableHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("Channel read message: " + msg);
		//Log method calls and forwards to next ChannelHandler
		ctx.fireChannelRead(msg);
	}

}
