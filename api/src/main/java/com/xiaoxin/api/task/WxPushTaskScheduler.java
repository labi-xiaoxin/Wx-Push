package com.xiaoxin.api.task;

import com.xiaoxin.core.core.WxMessagePush;
import com.xiaoxin.entity.entity.SendObject;
import com.xiaoxin.entity.service.ISendObjectService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

/**
 * 微信推送定时器
 *
 * @author wangyp
 * @date 2022/09/06
 **/
@Component
@Slf4j
public class WxPushTaskScheduler {

    @Autowired
    private ISendObjectService iSendObjectService;

    /**
     * 发送邮件线程池：(核心线程2,最大线程4,空闲线程存活时间0,时间单位SECONDS,阻塞队列ArrayBlockingQueue(20),线程名称为：Thread-sendEmailToCustomer-X )
     */
    private static final ExecutorService THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadFactory() {
        private int id = 0;

        @Override
        public Thread newThread(@NotNull Runnable r) {
            Thread t = new Thread(r);
            t.setName("Thread-WxPushTaskScheduler-" + id++);
            return t;
        }
    });

    @Scheduled(cron = "0 0 8 * * ? ")
    private void push() {
        List<SendObject> all = iSendObjectService.list();
        for (SendObject sendObject : all) {
            THREAD_POOL_EXECUTOR.execute(() -> {
                com.xiaoxin.core.entity.SendObject pushObject = new com.xiaoxin.core.entity.SendObject();
                BeanUtils.copyProperties(sendObject, pushObject);
                pushObject.setSecret(sendObject.getAppSecret());
                WxMessagePush.pushMessage(pushObject);
            });
        }
    }
}
