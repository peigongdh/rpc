package com.peigongdh.core.protocol;

import com.peigongdh.core.serializer.KryoSerializer;
import com.peigongdh.core.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder<Object> {
    private Serializer serializer = new KryoSerializer();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf out) {
        byte[] bytes = serializer.serialize(msg);
        int length = bytes.length;
        out.writeInt(length);
        out.writeBytes(bytes);
    }
}
