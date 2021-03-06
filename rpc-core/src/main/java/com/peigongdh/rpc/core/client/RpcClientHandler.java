package com.peigongdh.rpc.core.client;

import com.peigongdh.rpc.core.protocol.Response;
import com.peigongdh.rpc.core.utils.ResponseMapHelper;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/*因为要在不同channel中共享使用responseMap的blockingQueue，所以要加此注解*/

@ChannelHandler.Sharable
public class RpcClientHandler extends SimpleChannelInboundHandler<Response> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcClientHandler.class);

    // 因为此处这个要公用，故拿出来单独放到一个类中来调用
    // public static ConcurrentMap<Long, BlockingQueue<Response>> responseMap = new ConcurrentHashMap<Long, BlockingQueue<Response>>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response msg) throws Exception {
        // 此处的业务逻辑就是拿到对应id，将返回信息放入相应blockingQueue中
        BlockingQueue<Response> blockingQueue = ResponseMapHelper.responseMap.get(msg.getRequestId());
        if (blockingQueue != null) {
            blockingQueue.put(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("Exception caught on {}, ", ctx.channel(), cause);
        ctx.channel().close();
    }
}
