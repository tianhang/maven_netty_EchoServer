/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 *
 * @author tianhang
 */
@Sharable
class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	
	final String str = "dddddddddddddddddfdsfgdhffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"; 
	@Override
	protected void channelRead0(ChannelHandlerContext chc, ByteBuf i) throws Exception 	  {
		System.out.println("read 0");
		System.out.println("Client received: "+ ByteBufUtil
.hexDump(i.readBytes(i.readableBytes())));
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Connected");
    ctx.writeAndFlush(Unpooled.copiedBuffer("Netty MAY rock!"+str, CharsetUtil.UTF_8));
		System.out.println("write finsh");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}
