package com.jel.tech.netty;

import java.net.SocketAddress;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.EventExecutor;

public class DummyChannelHandlerContext implements ChannelHandlerContext {

	public static final DummyChannelHandlerContext DUMMY_INSTANCE = new DummyChannelHandlerContext();

	public ChannelFuture bind(SocketAddress localAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture connect(SocketAddress remoteAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture disconnect() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture close() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture deregister() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture bind(SocketAddress localAddress, ChannelPromise promise) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture connect(SocketAddress remoteAddress, ChannelPromise promise) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture disconnect(ChannelPromise promise) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture close(ChannelPromise promise) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture deregister(ChannelPromise promise) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture write(Object msg) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture write(Object msg, ChannelPromise promise) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture writeAndFlush(Object msg, ChannelPromise promise) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture writeAndFlush(Object msg) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelPromise newPromise() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelProgressivePromise newProgressivePromise() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture newSucceededFuture() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelFuture newFailedFuture(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelPromise voidPromise() {
		// TODO Auto-generated method stub
		return null;
	}

	public Channel channel() {
		// TODO Auto-generated method stub
		return null;
	}

	public EventExecutor executor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelHandler handler() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isRemoved() {
		// TODO Auto-generated method stub
		return false;
	}

	public ChannelHandlerContext fireChannelRegistered() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelHandlerContext fireChannelUnregistered() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelHandlerContext fireChannelActive() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelHandlerContext fireChannelInactive() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelHandlerContext fireExceptionCaught(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelHandlerContext fireUserEventTriggered(Object evt) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelHandlerContext fireChannelRead(Object msg) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelHandlerContext fireChannelReadComplete() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelHandlerContext fireChannelWritabilityChanged() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelHandlerContext read() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelHandlerContext flush() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChannelPipeline pipeline() {
		// TODO Auto-generated method stub
		return null;
	}

	public ByteBufAllocator alloc() {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Attribute<T> attr(AttributeKey<T> key) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> boolean hasAttr(AttributeKey<T> key) {
		// TODO Auto-generated method stub
		return false;
	}

}
