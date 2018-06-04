package com.peigongdh.core.protocol;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    private long requestId;
    private Object response;
    private Throwable throwable;
}
