package com.peigongdh.core.utils;

import com.peigongdh.core.protocol.Response;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ResponseMapHelper {
    public static ConcurrentMap<Long, BlockingQueue<Response>> responseMap = new ConcurrentHashMap<Long, BlockingQueue<Response>>();
}
