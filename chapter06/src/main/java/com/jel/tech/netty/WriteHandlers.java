package com.jel.tech.netty;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;
/**
 * Listing 6.6 Accessing the Channel from a ChannelHandlerContext
 * Listing 6.7 Accessing the ChannelPipeline from a ChannelHandlerContext
 * Listing 6.8 Calling ChannelHandlerContext write()
 * @author jelex.xu
 * @date 2017年9月22日
 */
public class WriteHandlers {

	private static final ChannelHandlerContext CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE = DummyChannelHandlerContext.DUMMY_INSTANCE;
	private static final ChannelPipeline CHANNEL_PIPELINE_FROM_SOMEWHERE = DummyChannelPipeline.DUMMY_INSTANCE;
	/*
	 * 通过channel来写数据，在整个pipeline中传递
	 */
	public static void writeViaChannel() {
		ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE;
		Channel channel = ctx.channel();
		channel.write(Unpooled.copiedBuffer("Netty in action!", CharsetUtil.UTF_8));
 	}
	/*
	 * 通过pipeline来写数据
	 */
	public static void writeViaPipeline() {
		ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE;
		ChannelPipeline pipeline = ctx.pipeline();
		pipeline.writeAndFlush(Unpooled.copiedBuffer("Netty in action!", CharsetUtil.UTF_8));
	}
	/*
			这 个 整 体 是 一 个 p i p e l i n e

-->>流程这样走！从左到右，从上到下
channel——————ChannelHandler——————ChannelHandler——————ChannelHandler
				|					|					|
		ChannelHandlerContext—>ChannelHandlerContext->ChannelHandlerContext
	 */
	public static void writeAtFixedHandler() {
		ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE;
		ctx.write(Unpooled.copiedBuffer("Netty in action!", CharsetUtil.UTF_8));
	}
}
