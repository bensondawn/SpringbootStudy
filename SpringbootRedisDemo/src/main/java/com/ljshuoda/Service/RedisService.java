package com.ljshuoda.Service;

import java.util.concurrent.TimeUnit;

public interface RedisService {

    void set(String key, Object value,long timeout, TimeUnit unit);

    Object get(String key);

    void setListLeft(String key,Object value,long timeout, TimeUnit unit);

    Object getListLeft(String key);

    Object getElement(String key,int index);

    void del(String key);

    void setTimeout(String key,long timeout, TimeUnit unit);
}
