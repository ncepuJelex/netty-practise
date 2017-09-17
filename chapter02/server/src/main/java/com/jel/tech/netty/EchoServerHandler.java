package com.jel.tech.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * echo sever using netty.first app learning.
 * netty 中的handler相当于callback一样，它把业务和技术分开来
 *
 * @author jelex.xu
 * @date 2017年9月17日
 */
@Sharable //表示可供多个channel共享使用
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	/*
	 * Called for each incoming message
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//此ByteBuf不是java.nio原生的ByteBuffer，看清楚！
		ByteBuf in = (ByteBuf) msg;
		//这一行信息量好大啊！使用了ByteBuf的转换为String的方法，以及获取字符集的方式
		System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
		//因为是回声嘛！所以server拿到client信息后，直接返回回去给client！
		ctx.write(in);
	}

	/*
	 * Notifies the handler that the last call made to channel-
	 *	Read() was the last message in the current batch
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		/*
		 * Flushes pending messages to the remote peer and closes the channel.
		 * 第一行相当于：Shortcut for call write(Object) and flush().
		 * 第2行：Adds the specified listener to this future.
		 * 	The specified listener is notified when this future is done.
		 *  If this future is already completed, the specified listener
		 *  is notified immediately.
		 */
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
			.addListener(ChannelFutureListener.CLOSE);
	}
	/*
	 * Called if an exception is thrown during the read operation
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//先只打印出error信息
		cause.printStackTrace();
		//关闭channel
		ctx.close();
	}
}
