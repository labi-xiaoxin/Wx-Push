package com.xiaoxin.api.controller;

import com.xiaoxin.api.annotion.LimitRequest;
import com.xiaoxin.comm.ReturnCode;
import com.xiaoxin.core.core.WxMessagePush;
import com.xiaoxin.core.entity.SendObject;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangyp
 * @date 2022/08/29
 **/
@RestController
@RequestMapping("/test/testPush")
@CrossOrigin(origins = "*")
public class TestController {

    @LimitRequest(count = 5)
    @PostMapping("/test")
    public ReturnCode saveComment(@RequestBody SendObject sendObject){
        return WxMessagePush.pushMessage(sendObject);
    }
}
