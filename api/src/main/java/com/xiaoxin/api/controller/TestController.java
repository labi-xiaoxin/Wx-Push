package com.xiaoxin.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxin.api.annotion.LimitRequest;
import com.xiaoxin.comm.ReturnCode;
import com.xiaoxin.core.core.WxMessagePush;
import com.xiaoxin.core.entity.SendObject;
import com.xiaoxin.entity.service.ISendObjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangyp
 * @date 2022/08/29
 **/
@RestController
@RequestMapping("/test/testPush")
@CrossOrigin(origins = "*")
public class TestController {

    @Autowired
    private ISendObjectService iSendObjectService;

    @LimitRequest(count = 5)
    @PostMapping("/test")
    public ReturnCode saveComment(@RequestBody SendObject sendObject){
        QueryWrapper<com.xiaoxin.entity.entity.SendObject> sendObjectQueryWrapper = new QueryWrapper<>();
        sendObjectQueryWrapper.eq("user_id", sendObject.getUserId());
        sendObjectQueryWrapper.eq("template_id", sendObject.getTemplateId());
        sendObjectQueryWrapper.eq("app_id", sendObject.getAppId());
        sendObjectQueryWrapper.eq("app_secret", sendObject.getSecret());

        com.xiaoxin.entity.entity.SendObject one = iSendObjectService.getOne(sendObjectQueryWrapper);
        if(one != null){
            sendObject.setId(one.getId());
        }

        return WxMessagePush.pushMessage(sendObject);
    }
}
