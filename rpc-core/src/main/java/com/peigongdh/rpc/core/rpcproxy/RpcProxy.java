package com.peigongdh.rpc.core.rpcproxy;

import com.peigongdh.rpc.core.Client.Client;

public interface RpcProxy {
    <T> T proxyInterface(Client client, final Class<T> serviceInterface);
}
