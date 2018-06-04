package com.peigongdh.core.protocol;

import com.peigongdh.core.serializer.KryoSerializer;
import com.peigongdh.core.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcDecoder extends LengthFieldBasedFrameDecoder {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcDecoder.class);
    private Serializer serializer = new KryoSerializer();

    public RpcDecoder(int maxFrameLength) {
        super(maxFrameLength, 0, 4, 0, 4);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf decode = (ByteBuf) super.decode(ctx, in);
        if (decode != null) {
            int byteLength = decode.readableBytes();
            byte[] byteHolder = new byte[byteLength];
            decode.readBytes(byteHolder);
            return serializer.deserialize(byteHolder);
        }
        LOGGER.debug("Decoder Result is null");
        return null;
    }
}
