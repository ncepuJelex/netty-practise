package com.jel.tech.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 当重写 channelRead() 方法时，那就变成你的责任
 * 去显式地释放和ByteBuf相关的内存资源了,如下所示，
 * 但是这种方式显得太笨了，后面看使用 SimpleChannelInboundHandler 的例子
 * @author jelex.xu
 * @date 2017年9月21日
 */
public class DiscardHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ReferenceCountUtil.release(msg);
	}
}
