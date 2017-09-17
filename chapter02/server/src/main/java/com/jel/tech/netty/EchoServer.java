package com.jel.tech.netty;

import java.net.InetSocketAddress;
import java.util.Scanner;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

	private final int port;

	public EchoServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) {

		System.out.println("输入server监听的端口：");
		Scanner sc = new Scanner(System.in);
		int port = sc.nextInt();
		sc.close();
		try {
			new EchoServer(port).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() throws Exception {
		final EchoServerHandler serverHandler = new EchoServerHandler();
		//accepting new connections and reading/writing data
		EventLoopGroup group = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group).channel(NioServerSocketChannel.class)
			.localAddress(new InetSocketAddress(port))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(serverHandler);
				}
			});
			/*
			 * Waits for this future until it is done,
			 * and rethrows the cause of the failure if
			 * this future failed.
			 */
			ChannelFuture f = b.bind().sync();
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}

	}

}
