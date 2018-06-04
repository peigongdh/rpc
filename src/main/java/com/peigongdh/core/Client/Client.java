package com.peigongdh.core.Client;

import com.peigongdh.core.protocol.Response;

import java.lang.reflect.Method;

public interface Client {
    Response sendMessage(Class<?> clazz, Method method, Object[] args);

    <T> T proxyInterface(Class<T> serviceInterface);

    void close();
}

