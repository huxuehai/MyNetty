package org.mynetty.spiltpackages;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;




public class MyMessageEncoder extends MessageToByteEncoder<MyMessageProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MyMessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("调用加密方法  MyMessageEncoder encode");

        out.writeInt(msg.getLen());
        out.writeBytes(msg.getBytes());
    }
}
