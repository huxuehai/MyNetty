package org.mynetty.chat;

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


public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final SimpleDateFormat simpleFormatter =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String messageStr = buf.toString(CharsetUtil.UTF_8);
        Channel channel = ctx.channel();
        channels.forEach(channel1 -> {
            if(channel1 != channel){
                channel1.writeAndFlush(messageStr);
            }else {
                channel.writeAndFlush("【已发送消息】："+ messageStr+ simpleFormatter.format(new Date())+"\n");
            }
        });
        System.out.println(channel.remoteAddress()+" 发送了消息： "+ messageStr + simpleFormatter.format(new Date())+"\n");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("channelRead0"+ msg);
        Channel channel = channelHandlerContext.channel();
        String message = " 用户 " + channel.remoteAddress()+" 发送了消息： "+ msg + simpleFormatter.format(new Date())+"\n";
//        channels.forEach(channel1 -> {
//            if(channel1 != channel){
//                channel1.writeAndFlush(message);
//            }else {
//                channel.writeAndFlush("【已发送消息】："+ msg+ simpleFormatter.format(new Date())+"\n");
//            }
//        });
        System.out.println(channel.remoteAddress()+" 发送了消息： "+ msg + simpleFormatter.format(new Date())+"\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String message = " 用户 " + channel.remoteAddress()+" 上线了，  于"+ simpleFormatter.format(new Date())+"\n" ;
        channels.forEach(channel1 -> {
            if(channel1 != channel){
                channel1.writeAndFlush(message);
            }
//            else {
//                channel.writeAndFlush("【自己】 尊敬的用户" + channel.remoteAddress()+"您上线了，于"+ simpleFormatter.format(new Date())+" 欢淫回来" +"\n");
//            }
        });
//        channels.writeAndFlush(message);
        channels.add(channel);
        System.out.println(message);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String message = " 用户 " + channel.remoteAddress()+" 下线了"+ simpleFormatter.format(new Date())+"\n" ;
        channels.forEach(channel1 -> {
            if(channel1 != channel){
                channels.writeAndFlush(message);
            }
//            else {
//                channel.writeAndFlush("【自己】 尊敬的用户"+channel.remoteAddress()+"您已下线"+ simpleFormatter.format(new Date())+"\n");
//            }
        });
//        channels.writeAndFlush(message);
        channels.add(channel);
        System.out.println(message);
    }
}
