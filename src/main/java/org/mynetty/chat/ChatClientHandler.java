package org.mynetty.chat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
    private static final SimpleDateFormat simpleFormatter =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

//        System.out.println( s.trim() + simpleFormatter.format(new Date())+"\n");
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String messageStr = buf.toString(CharsetUtil.UTF_8);
        System.out.println(messageStr);

    }
}
