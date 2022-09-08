package com.xiaoxin.api.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author:wyp
 * @Date:2020/8/24 16:40
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 实现自动添加
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //setFieldValByName:根据名称设置属性值(属性名,属性值，元对象参数)
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);

    }

    /**
     * 实现自动更新
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("updateTime", new Date(), metaObject);

    }
}
