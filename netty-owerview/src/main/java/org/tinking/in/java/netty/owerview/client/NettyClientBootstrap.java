package org.tinking.in.java.netty.owerview.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Netty 客户端启动类：
 * 内容包括：main 函数、连接方法
 * 主要流程：
 * 1. 创建 EventLoopGroup（线程组） 实例；
 * 2. 创建 Bootstrap 实例（重要）；
 * 3. 配置 Bootstrap（重要）；
 * 4. 连接远端，生成 ChannelFuture 实例（重要）；
 * 关闭链接释放资源。
 *
 * <p>创建时间: 2023/2/7 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class NettyClientBootstrap {

    private final String host;
    private final int port;

    public NettyClientBootstrap(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws InterruptedException {
        //* 1. 创建 EventLoopGroup（线程组） 实例；
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //* 2. 创建 Bootstrap 实例（重要）；
            Bootstrap bs = new Bootstrap();
            //* 3. 配置 Bootstrap（重要）；
            bs.group(group)
                    .channel(NioSocketChannel.class)//实例化一个Channel
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>()//进行通道初始化配置
                    {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new ClientMessageHandler());//添加我们自定义的Handler
                        }
                    });
            //* 4. 连接远端，生成 ChannelFuture 实例（重要）；
            //连接到远程节点；等待连接完成
            ChannelFuture future = bs.connect().sync();

            //发送消息到服务器端，编码格式是utf-8
            future.channel().writeAndFlush(Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8));

            //阻塞操作，closeFuture()开启了一个channel的监听器（这期间channel在进行各项工作），直到链路断开
            future.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyClientBootstrap("localhost", 9900).run();
    }
}
