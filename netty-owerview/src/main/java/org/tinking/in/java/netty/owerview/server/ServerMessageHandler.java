package org.tinking.in.java.netty.owerview.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * TODO
 * <p>创建时间: 2023/2/7 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
@ChannelHandler.Sharable
public class ServerMessageHandler extends ChannelInboundHandlerAdapter {

    // 重写 channelRead 方法，处理收到的客户端消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 处理收到的数据，并反馈消息到客户端
        ByteBuf in = (ByteBuf) msg;
        System.out.printf("收到客户端的消息：%s",in.toString(CharsetUtil.UTF_8));
        // 写入并发送消息到远端（客户端）
        // Unpooled#copiedBuffer ???
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好，我是服务端，我已经收到你发送的消息", CharsetUtil.UTF_8));
    }

    // 重写 exceptionCaught ，处理异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
