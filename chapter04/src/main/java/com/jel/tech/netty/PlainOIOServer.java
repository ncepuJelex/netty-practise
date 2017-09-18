package com.jel.tech.netty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * java jdk中非阻塞socket server，
 * netty想借此秀一下自己！
 * @author jelex.xu
 * @date 2017年9月18日
 */
public class PlainOIOServer {

	public void server(int port) throws IOException {
		final ServerSocket server = new ServerSocket(port);
		try {
			for(;;) {
				final Socket client = server.accept();
				System.out.println("Accepted connection from: " + client);

				new Thread(new Runnable() {
					public void run() {
						OutputStream out;
						try {
							out = client.getOutputStream();
							out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
							out.flush();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								client.close();
							} catch (IOException e) {
							}
						}
					}
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		PlainOIOServer s = new PlainOIOServer();
		try {
			s.server(8888);
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
