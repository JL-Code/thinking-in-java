package org.tinking.in.java.netty.owerview.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Netty 服务器示例：
 * 主要步骤：
 * 1.创建服务端 Handler
 * 1.1 创建类并继承 ChannelInboundHandlerAdapter、标上 @ChannelHandler.Sharable 注解
 * 1.2 重写 channelRead 方法，处理接收的消息
 *
 * 代码说明：
 * 1）EventLoopGroup：实际项目中，这里创建两个EventLoopGroup的实例，一个负责接收客户端 的连接，
 * 另一个负责处理消息I/O，这里为了简单展示流程，让一个实例把这两方面的活都干了；
 * 2）NioServerSocketChannel：通过工厂通过工厂方法设计模式实例化一个 channel，
 * 这个在大家还没有能够熟练使用 Netty 进行项目开发的情况下，不用去深究。
 * <p>创建时间: 2023/2/7 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class NettyServerBootstrap {

    private final int port;

    public NettyServerBootstrap(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();//Netty的Reactor线程池，初始化了一个NioEventLoop数组，用来处理I/O操作,如接受新的连接和读/写数据
        try {
            ServerBootstrap b = new ServerBootstrap();//用于启动NIO服务
            b.group(group)
                    .channel(NioServerSocketChannel.class) //通过工厂方法设计模式实例化一个channel
                    .localAddress(new InetSocketAddress(port))//设置监听端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //ChannelInitializer是一个特殊的处理类，他的目的是帮助使用者配置一个新的Channel,用于把许多自定义的处理类增加到pipline上来
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {//ChannelInitializer 是一个特殊的处理类，他的目的是帮助使用者配置一个新的 Channel。
                            ch.pipeline().addLast(new ServerMessageHandler());//配置childHandler来通知一个关于消息处理的InfoServerHandler实例
                        }
                    });

            //绑定服务器，该实例将提供有关IO操作的结果或状态的信息
            ChannelFuture channelFuture = b.bind().sync();
            System.out.println("在" + channelFuture.channel().localAddress() + "上开启监听");

            //阻塞操作，closeFuture()开启了一个channel的监听器（这期间channel在进行各项工作），直到链路断开
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();//关闭EventLoopGroup并释放所有资源，包括所有创建的线程
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyServerBootstrap(9900).run();
    }
}
