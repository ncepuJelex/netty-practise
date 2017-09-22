package com.jel.tech.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Adding a ChannelFutureListener is a matter of calling addListener
 * (ChannelFuture- Listener) on a ChannelFuture instance,
 * and there are two ways to do this. The one most commonly used is
 * to invoke addListener() on the ChannelFuture that is returned by
 * an outbound operation (for example write()).
 * 这是第一种方式，最后还有一种方式，请看OutboundExceptionHandler类
 * @author jelex.xu
 * @date 2017年9月22日
 */
public class ChannelFuture {

	private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();
	private static final ByteBuf SOME_MES_FROM_SOMEWHERE = Unpooled.buffer(1024);

	public static void addChannelFutureListener() {
		Channel channel = CHANNEL_FROM_SOMEWHERE;
		ByteBuf msg = SOME_MES_FROM_SOMEWHERE;
		//...
		io.netty.channel.ChannelFuture future = channel.write(msg);
		future.addListener(new ChannelFutureListener() {

			public void operationComplete(io.netty.channel.ChannelFuture future) throws Exception {
				if(!future.isSuccess()) {
					future.cause().printStackTrace();
					future.channel().close();
				}
			}
		});
	}
}
