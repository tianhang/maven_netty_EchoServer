/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *
 * @author tianhang
 */
@Sharable
class EchoServerHandler extends ChannelInboundHandlerAdapter {

	public EchoServerHandler() {
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("read complete !");
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
		.addListener(ChannelFutureListener.CLOSE);	
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("Server received: " + msg);
		ctx.write(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception{
		ctx.close();
	}
	
}
