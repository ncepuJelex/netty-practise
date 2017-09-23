package com.jel.tech.netty;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    private static final Channel CHANEL_FROM_SOMEWHERE = new NioSocketChannel();

    /*
     * Although the ScheduledExecutorService API is straightforward,
     *  under heavy load it can introduce performance costs.
     *  In the next section we’ll see how Netty provides the same
     *  functionality with greater efficiency.
     */
    public static void schedule() {
    		ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);
    		exec.schedule(new Runnable() {

				public void run() {
					System.out.println("Now it is 60 seconds later.");
				}

    		}, 60, TimeUnit.SECONDS);

    }

    /*
     * 这种方式比上面java 原生的好！我好像还没有看出来，哎~
     */
    public static void scheduleViaEventLoop() {
    		Channel ch = CHANEL_FROM_SOMEWHERE;
    		ScheduledFuture<?> future = ch.eventLoop().schedule(new Runnable() {

				public void run() {
					System.out.println("60 seconds later.");
				}

    		}, 60, TimeUnit.SECONDS);
    }

    /*
     * 每60秒跑一次
     */
    public static void scheduleAtFixedRateViaEventLoop() {
    		Channel ch = CHANEL_FROM_SOMEWHERE;
    		ScheduledFuture<?> future = ch.eventLoop().scheduleAtFixedRate(new Runnable() {

				public void run() {
					System.out.println("Run every 60 seconds");
				}

    		}, 60, 60, TimeUnit.SECONDS);

    		//... some other codes run here
    		boolean mayInterruptIfRunning = false;
    		/*
    		 * 取消一个正常执行的任务
    		 */
    		future.cancel(mayInterruptIfRunning);
    }


}
