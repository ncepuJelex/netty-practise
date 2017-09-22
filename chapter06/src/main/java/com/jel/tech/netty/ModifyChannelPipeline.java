package com.jel.tech.netty;
import static com.jel.tech.netty.DummyChannelPipeline.DUMMY_INSTANCE;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelPipeline;

/**
 * 修改pipeline
 * @author jelex.xu
 * @date 2017年9月22日
 */
public class ModifyChannelPipeline {

	private static final ChannelPipeline CHANNEL_PIPELINE_FROM_SOMEWHERE = DUMMY_INSTANCE;

	public static void modifyPipeline() {
		ChannelPipeline pipeline = CHANNEL_PIPELINE_FROM_SOMEWHERE;
		FirstHandler firstHandler = new FirstHandler();
		pipeline.addLast("handler1", firstHandler);
		pipeline.addFirst("handler2", new SecondHandler());
		pipeline.addLast("handler3", new ThirdHandler());
		//...
		pipeline.remove("handler3");
		pipeline.remove(firstHandler);
		pipeline.replace("handler2", "handler4", new FourthHandler());
	}

	private static final class FirstHandler extends ChannelHandlerAdapter {

	}

	private static final class SecondHandler extends ChannelHandlerAdapter {

	}

	private static final class ThirdHandler extends ChannelHandlerAdapter {

	}

	private static final class FourthHandler extends ChannelHandlerAdapter {

	}




}
