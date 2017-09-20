package com.jel.tech.netty;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Random;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ByteProcessor;
/**
 * 如名所述……
 * @author jelex.xu
 * @date 2017年9月20日
 */
public class ByteBufExamples {

	private static final ByteBuf BYTE_BUF_FROM_SOMEWHERE = Unpooled.buffer(1024);
	private static final Random random = new Random();
	private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();
	private static final ChannelHandlerContext CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE  = null;

	public static void heapBuf() {

		ByteBuf heapBuf = BYTE_BUF_FROM_SOMEWHERE;
		if(heapBuf.hasArray()) {
			byte[] array = heapBuf.array();
			int offset = heapBuf.arrayOffset() + heapBuf.readerIndex(); //可读的起始位置
			int length = heapBuf.readableBytes(); //equals writeIndex - readIndex
			handleArray(array, offset ,length);
		}
	}

	private static void handleArray(byte[] array, int offset, int length) {

	}

	/*
	 * because the data isn’t on the heap,
	 * you may have to make a copy, as shown next.
	 */
	public static void directBuf() {
		ByteBuf directBuf = BYTE_BUF_FROM_SOMEWHERE;
		//表示direct allocated bytebuf
		if(!directBuf.hasArray()) {
			int length = directBuf.readableBytes();
			byte[] array = new byte[length];
			directBuf.getBytes(0, array);
			handleArray(array, 0, length);
		}
	}

	/*
	 * Listing 5.3 Composite buffer pattern using ByteBuffer
	 * 这里演示jdk nio的处理，然后秀下netty自己的做法，显出自己的高大上！
	 */
	public static void byteBufferComposite(ByteBuffer header, ByteBuffer body) {

		ByteBuffer[] message = new ByteBuffer[]{header, body};
		ByteBuffer message2 = ByteBuffer.allocate(header.remaining() + body.remaining());
		message2.put(header);
		message2.put(body);
		message2.flip();
	}

	/*
	 * Listing 5.4 Composite buffer pattern using CompositeByteBuf
	 * 和上一个例子对比
	 */
	public static void byteBufComposite() {
		CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
		ByteBuf headerBuf = BYTE_BUF_FROM_SOMEWHERE;
		ByteBuf bodyBuf = BYTE_BUF_FROM_SOMEWHERE;
		messageBuf.addComponents(headerBuf, bodyBuf);
		//...
		messageBuf.removeComponent(0); // remove the element at index 0
		for(ByteBuf buf : messageBuf) {
			System.out.println(buf.toString());
		}
	}

	/*
	 * CompositeByteBuf may not allow access to a backing array,
	 *  so accessing the data in a
	 *	CompositeByteBuf resembles the direct buffer pattern
	 */
	public static void byteBufCompositeArray() {
		CompositeByteBuf compositeBuf = Unpooled.compositeBuffer();
		int len = compositeBuf.readableBytes();
		byte[] array = new byte[len];
		compositeBuf.getBytes(0, array);
		handleArray(array,0, len);
	}

	/*
     * Listing 5.6 Access data
     */
	public static void byteBufRelativeAccess() {
		ByteBuf buf = BYTE_BUF_FROM_SOMEWHERE;
		for(int i=0; i<buf.capacity(); i++) {
			byte b = buf.getByte(i);
			System.out.println((char)b);
		}
	}

	/*
     * Listing 5.7 Read all data
     */
	public static void readAllData() {
		ByteBuf buf = BYTE_BUF_FROM_SOMEWHERE;
		while(buf.isReadable()) {
			System.out.println(buf.readByte());
		}
	}

	/**
     * Listing 5.8 Write data
     */
	public static void write() {
		ByteBuf buf = BYTE_BUF_FROM_SOMEWHERE;
		while(buf.writableBytes() >= 4) {
			buf.writeInt(random.nextInt());
		}
	}

	/*
	 * Listing 5.9 Using ByteBufProcessor to find \r
	 */
	public static void byteBufProcess() {
		ByteBuf buf = BYTE_BUF_FROM_SOMEWHERE;
		int index = buf.forEachByte(ByteProcessor.FIND_CR);
	}

	/*
	 * Listing 5.10 Slice a ByteBuf
	 */
	public static void byteBufSlice() {
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("Netty in action rocks!", utf8);
		ByteBuf slice = buf.slice(0, 15);
		System.out.println(slice.toString(utf8));
		buf.setByte(0, 'J');
		System.out.println((char)slice.getByte(0));
		/*
		 * running result:
		    Netty in action
			J
		 */
	}

	public static void main(String[] args) {
//		byteBufSlice();
//		byteBufCopy();
//		byteBufSetGet();
//		byteBufReadWrite();
		referenceCounting();
	}

	/**
     * Listing 5.11 Copying a ByteBuf
     */
	public static void byteBufCopy() {
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("Netty in action rocks!", utf8);
		ByteBuf copy = buf.copy(0, 15);
		System.out.println(copy.toString(utf8)); //加上utf8 参数就不一样的显示
		buf.setByte(0, 'J');
		System.out.println((char)copy.getByte(0));
		/*
		 * running result:
		    Netty in action
			N
		 */
	}

	/**
     * Listing 5.12 get() and set() usage
     */
    public static void byteBufSetGet() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        System.out.println((char)buf.getByte(0)); //N
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.setByte(0, (byte)'J');
        System.out.println((char)buf.getByte(0)); //J
        /*
         * both true,because don’t modify the indices
         */
        System.out.println(readerIndex == buf.readerIndex());
        System.out.println(writerIndex == buf.writerIndex());
    }


	/**
     * Listing 5.13 read() and write() operations on the ByteBuf
     */
	public static void byteBufReadWrite() {

		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("Netty in action rocks!", utf8);
		System.out.println((char)buf.readByte()); //N
		int readerIndex = buf.readerIndex();
		int writerIndex = buf.writerIndex();
		buf.writeByte((byte)'?');
		System.out.println(readerIndex);//1
		System.out.println(writerIndex);//22
		System.out.println(buf.toString(utf8)); //etty in action rocks!?
	}

	/*
	 * Listing 5.14 Obtaining a ByteBufAllocator reference
	 */
	public static void obtainByteBufAllocatorReference() {
		Channel channel = CHANNEL_FROM_SOMEWHERE;
		ByteBufAllocator allocator = channel.alloc();
		//...
		ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE;
		ByteBufAllocator allocator2 = ctx.alloc();
	}

	/*
	 * Listing 5.15 Reference counting
	 */
	public static void referenceCounting() {
		Channel channel = CHANNEL_FROM_SOMEWHERE;
		ByteBufAllocator allocator = channel.alloc();
		//..
		ByteBuf buf = allocator.directBuffer();
		System.out.println(buf.refCnt() == 1); //true
	}

	/*
	 * Listing 5.16 Release reference-counted object
	 */
	public static void releaseReferenceCountedObject() {
		ByteBuf buf = BYTE_BUF_FROM_SOMEWHERE;
		boolean released = buf.release();
		//...
	}
}
