package org.mynetty.spiltpackages;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyServerHandler extends SimpleChannelInboundHandler<MyMessageProtocol> {
    private static int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageProtocol msg) throws Exception {
        System.out.println("=== 收到服务端的消息如下====");
        System.out.println("长度 = "+msg.getLen());
        System.out.println("内容= "+ new String(msg.getBytes(),CharsetUtil.UTF_8));
        System.out.println("服务端接收到的消息总包数量 = " +(++count));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessageProtocol myMessageProtocol = (MyMessageProtocol) msg;
        System.out.println("=== 收到服务端的消息如下====");
        System.out.println("长度 = "+myMessageProtocol.getLen());
        System.out.println("内容= "+ new String(myMessageProtocol.getBytes(),CharsetUtil.UTF_8));
        System.out.println("服务端接收到的消息总包数量 = " +(++count));
        ctx.channel().writeAndFlush("收到");

    }

}
