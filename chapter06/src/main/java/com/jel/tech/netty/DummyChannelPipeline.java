package com.jel.tech.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultChannelPipeline;

/**
 * dummy, so you know,被其它类做示例性的引用
 * @author jelex.xu
 * @date 2017年9月22日
 */
public class DummyChannelPipeline extends DefaultChannelPipeline {

	public static final ChannelPipeline DUMMY_INSTANCE = new DummyChannelPipeline(null);

	protected DummyChannelPipeline(Channel channel) {
		super(channel);
	}

}
