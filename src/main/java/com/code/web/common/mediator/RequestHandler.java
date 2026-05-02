package com.code.web.common.mediator;

public interface RequestHandler<T extends Request<R>, R> {

    R handler(T request);

    Class<T> getRequestType();
 

}
