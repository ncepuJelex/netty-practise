package com.jel.tech.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nio 实现的java 小 socket server，
 * netty 借此来秀下自己的优势！
 * @author jelex.xu
 * @date 2017年9月18日
 */
public class PlainNioServer {

	public void serve(int port) throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		InetSocketAddress address = new InetSocketAddress(port);
		serverChannel.bind(address);
		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		//要发送的数据
		final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());

		for(;;) {
			try {
				selector.select();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readyKeys.iterator();
			while(iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();

				try {
					if(key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel channel = server.accept();
						channel.configureBlocking(false);
						channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, msg.duplicate());
						System.out.println("Accepted connection from :" + channel);
					}
					if(key.isWritable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						ByteBuffer buf = (ByteBuffer) key.attachment();
						while(buf.hasRemaining()) {
							if(channel.write(buf) == 0) {
								break;
							}
						}
						channel.close();
					}
				} catch (Exception e) {
					key.cancel();
					try {
						key.channel().close();
					} catch (Exception e1) {
					}
				}
			}
		}
	}
	public static void main(String[] args) {

		PlainNioServer s = new PlainNioServer();
		try {
			s.serve(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * running result:
		 * Jelex:~ zhenhua$ telnet localhost 8888
			Trying ::1...
			Connected to localhost.
			Escape character is '^]'.
			Hi!
			Connection closed by foreign host.
			Jelex:~ zhenhua$
		 */
	}
}
