package com.jel.tech.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 因为SimpleChannelInboundHandler会自动释放资源，所以不要持有
 * msg的引用，以便之后使用，会失效的！后面会有想持有引用的办法！6.1.6节内容。
 * @author jelex.xu
 * @date 2017年9月21日
 */
public class SimpleDiscardHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		//...
		//that's it,No need for any explicit release of resources!!!
	}

}
