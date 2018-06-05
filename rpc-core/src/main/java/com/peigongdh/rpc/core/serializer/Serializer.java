package com.peigongdh.rpc.core.serializer;

public interface Serializer {
    byte[] serialize(Object obj);

    <T> T deserialize(byte[] bytes);
}
