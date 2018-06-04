package com.peigongdh.core.rpcproxy;

import com.peigongdh.core.Client.Client;

public interface RpcProxy {
    <T> T proxyInterface(Client client, final Class<T> serviceInterface);
}
