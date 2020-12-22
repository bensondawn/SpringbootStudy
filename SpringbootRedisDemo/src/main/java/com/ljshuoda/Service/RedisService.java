package com.ljshuoda.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface RedisService {

    void set(String key, Object value,long timeout, TimeUnit unit);

    Object get(String key);

    /**
     * 获取List结构中的属性
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * 获取List结构的长度
     */
    Long lSize(String key);

    void setListLeft(String key,Object value,long timeout, TimeUnit unit);

    Object getListLeft(String key);

    Object getElement(String key,int index);

    void del(String key);

    void setTimeout(String key,long timeout, TimeUnit unit);
}
