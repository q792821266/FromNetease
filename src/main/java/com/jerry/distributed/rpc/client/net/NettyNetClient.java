package com.jerry.distributed.rpc.client.net;

import com.jerry.distributed.rpc.discovery.ServiceInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;


/**
 * description: NettyNetClient <br>
 * date: 2021/1/3 11:06 <br>
 *
 * @author: Jerry <br>
 * @version: 1.0 <br>
 */
public class NettyNetClient implements NetClient {

    private static Logger Logger = LoggerFactory.getLogger(NettyNetClient.class);

    public byte[] sendRequest(byte[] data, ServiceInfo serviceInfo) throws Throwable {

        String[] addInfoArray = serviceInfo.getAddress().split(":");
        final SendHandler sendHandler = new SendHandler(data);

        byte[] respData;
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(sendHandler);
                        }
                    });
            bootstrap.connect(addInfoArray[0], 1).sync();
            respData = (byte[]) sendHandler.rspData();
            Logger.info("sendRequest get Reply -{}",respData);
        } finally {
            group.shutdownGracefully();
        }

        return respData;
    }

    private class SendHandler extends ChannelInboundHandlerAdapter {
        private CountDownLatch cdl;
        private Object readMessage = null;
        private byte[] data;

        public SendHandler(byte[] data) {
            cdl = new CountDownLatch(1);
            this.data = data;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            Logger.info("连接服务端成功");
            ByteBuf reqBuf = Unpooled.buffer(data.length);
            reqBuf.writeBytes(data);
            Logger.info("客户端发送消息-{}", data);
            ctx.writeAndFlush(reqBuf);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            Logger.info("读取数据...");
            ByteBuf bytes = (ByteBuf) msg;
            byte[] resp = new byte[bytes.readableBytes()];
            bytes.readBytes(resp);
            readMessage = Arrays.toString(resp) + " ??? !";
            cdl.countDown();
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            Logger.error("异常发生 - {}", cause.getMessage());
            ctx.close();
        }

        public Object rspData(){
            try {
                cdl.await();
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            return readMessage;
        }
    }
}
