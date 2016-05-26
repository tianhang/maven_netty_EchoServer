/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tianhang
 */
public class EchoClient implements Runnable {

	private final String host;
	private final int port;

	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
				.channel(NioSocketChannel.class)
				.remoteAddress(new InetSocketAddress(host, port))
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					public void initChannel(SocketChannel ch)
						throws Exception {
						ch.pipeline().addLast(
							new EchoClientHandler());
					}
				});
			ChannelFuture f = b.connect().sync();
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws Exception {
//		if (args.length != 2) {
//			System.err.println(
//				"Usage: " + EchoClient.class.getSimpleName()
//				+ " <host> <port>");
//			return;
//		}
// Parse options.
		final String host = "127.0.0.1";
		final int port = 8888; 
		System.out.println("client  run ....");
		for(int i=0;i<10000;i++)
		{
			System.out.println(i+"---->");
			new Thread(new EchoClient(host, port)).start();
				
		}
		System.out.println("client  finish ....");

	}

	@Override
	public void run() {
		try {
			start();
		} catch (Exception ex) {
			Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
