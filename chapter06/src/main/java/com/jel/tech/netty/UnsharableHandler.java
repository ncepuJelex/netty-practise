package com.jel.tech.netty;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * The problem with this code is that it has state;
 *  namely the instance variable count, which tracks the number
 *  of method invocations. Adding an instance of this class to
 *  the ChannelPipeline will very likely produce errors when it’s
 *  accessed by concurrent channels.当然用synchronized上锁就好了！
 *
 *  In summary, use @Sharable only if you’re certain that your ChannelHandler is thread-safe.
 * @author jelex.xu
 * @date 2017年9月22日
 */
@Sharable
public class UnsharableHandler extends ChannelInboundHandlerAdapter {

	private int count;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		count++;
		System.out.println("Channel read msg: " + msg);
		ctx.fireChannelRead(msg);
	}


}
