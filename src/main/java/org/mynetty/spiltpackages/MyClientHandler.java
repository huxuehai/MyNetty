package org.mynetty.spiltpackages;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


import java.nio.charset.StandardCharsets;


public class MyClientHandler extends SimpleChannelInboundHandler<MyMessageProtocol> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 5 ; i++) {
            String msg = "客户端 请求消息送到";
            MyMessageProtocol myMessageProtocol = new MyMessageProtocol();
            myMessageProtocol.setLen(msg.getBytes(StandardCharsets.UTF_8).length);
            myMessageProtocol.setBytes(msg.getBytes(StandardCharsets.UTF_8));
            ctx.writeAndFlush(myMessageProtocol);

        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        System.out.println("服务端发送数据长度 "+msg);

    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageProtocol msg) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
