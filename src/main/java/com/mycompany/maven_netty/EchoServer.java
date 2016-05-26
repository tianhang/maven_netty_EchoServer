/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

/**
 *
 * @author tianhang
 */
public class EchoServer {

	private int port;

	public EchoServer(int port) {
		this.port = port;
	}

	public void start() throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
				.channel(NioServerSocketChannel.class)
				.localAddress(new InetSocketAddress(port))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel c) throws Exception {
						c.pipeline().addLast(new EchoServerHandler());
					}

				});

			ChannelFuture f = b.bind().sync();
			System.out.println(EchoServer.class.getName()
				+ " started and listen on " + f.channel().localAddress()
			);
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws Exception {
//		if (args.length != 1) {
//			System.err.println(
//				"Usage: " + EchoServer.class.getSimpleName()
//				+ " <port>");
//		}
		int port = 8888;
		new EchoServer(port).start();
	}
}
