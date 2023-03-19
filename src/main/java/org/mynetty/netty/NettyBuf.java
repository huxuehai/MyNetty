package org.mynetty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class NettyBuf {
    public static void main(String[] args) {
        // ByteBuf 通过 readerIndex和 writerIndex 和 capacity 将buffer分成了三个区域
        //  已经读取到的区域 [0,readerIndex)
        //  可以读取到的区域 [readerIndex,writerIndex)
        //  可以写的区域 [writerIndex,capacity)
        //ridx: 0, widx: 10, cap: 256         ridx:开始读取的下角标   widx： 包含的数据最大下角标 cap：初始化容量
        //ridx: 0, widx: 8, cap: 10         ridx:开始读取的下角标   widx： 包含的数据最大下角标 cap：初始化容量
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 8; i++) {
            buffer.writeByte(i);
        }
        // NettyBuf :UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 8, cap: 10)
        System.out.println(" NettyBuf :"+buffer);
        for (int i = 0; i < 5; i++) {
            buffer.getByte(i);
        }
        // NettyBuf :UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 8, cap: 10)
        System.out.println(" NettyBuf :"+buffer);
        for (int i = 0; i < 6; i++) {
            buffer.readByte();
        }
        // NettyBuf :UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 6, widx: 8, cap: 10)
        System.out.println(" NettyBuf :"+buffer);

        ByteBuf buf = Unpooled.copiedBuffer("hello world 的!", CharsetUtil.UTF_8);
        if(buf.hasArray()){
            byte[] content = buf.array();
            //将  content 转成字符串
            System.out.println(new String(content, CharsetUtil.UTF_8));
        }

    }
}
