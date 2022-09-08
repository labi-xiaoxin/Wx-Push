package com.xiaoxin.api.annotion;

import java.lang.annotation.*;

/**
 * 限制接口同一IP请求次数
 * @author wangyp
 * @date 2022/09/02
 **/
@Documented
@Target(ElementType.METHOD) // 说明该注解只能放在方法上面
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRequest {
    long time() default 6000; // 限制时间 单位：毫秒
    int count() default 5; // 允许请求的次数
}
