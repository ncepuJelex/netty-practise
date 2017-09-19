package com.jel.tech.netty;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyNioServer {

	public void serve(int port) throws Exception {
		final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {

							@Override
							public void channelActive(ChannelHandlerContext ctx) throws Exception {
								ctx.writeAndFlush(buf.duplicate()).addListener(new ChannelFutureListener() {

									public void operationComplete(ChannelFuture future) throws Exception {
										if(future.isSuccess()) {
											System.out.println("write successfully.");
										} else {
											System.err.println("fk, should error!");
											future.cause().printStackTrace();
										}
									}

								});
							}
						});
					}

				});
			ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync(); //不要写成f.channel().close().sync();不然就挂了！
		} finally {
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) {

		try {
			new NettyNioServer().serve(8888);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * running result:
		 * Jelex:~ zhenhua$ telnet localhost 8888
			Trying ::1...
			Connected to localhost.
			Escape character is '^]'.
			Hi!

			控制台输出 write successfully.
		 */
	}
}
