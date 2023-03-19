package org.mynetty.spiltpackages;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;


import java.util.List;

public class MyMessageDecoder extends ByteToMessageDecoder  {
    int len = 0;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println(" MyMessageDecoder decode 被调用");

        System.out.println(in);

        if(in.readableBytes() >= 4){
            if(len == 0){
                len = in.readInt();
            }
            if(in.readableBytes() < len){
                System.out.println(" 可读数据已经不够，请继续等待");
                return;
            }
            byte[] content  = new byte[len];
            in.readBytes(content);
            MyMessageProtocol myMessageProtocol = new MyMessageProtocol();
            myMessageProtocol.setLen(len);
            myMessageProtocol.setBytes(content);
            out.add(myMessageProtocol);

            len = 0;

        }
    }
}
