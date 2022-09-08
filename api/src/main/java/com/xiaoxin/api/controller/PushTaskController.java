package com.xiaoxin.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxin.api.annotion.LimitRequest;
import com.xiaoxin.comm.ReturnCode;
import com.xiaoxin.core.core.WxMessagePush;
import com.xiaoxin.core.entity.SendObject;
import com.xiaoxin.entity.service.ISendObjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangyp
 * @date 2022/08/29
 **/
@RestController
@RequestMapping("/task/pushTask")
@CrossOrigin(origins = "*")
public class PushTaskController {

    @Autowired
    private ISendObjectService iSendObjectService;

    @PostMapping("/addPushTask")
    @LimitRequest(count = 5)
    public ReturnCode addPushTask(@RequestBody SendObject sendObject) {
        ReturnCode returnCode = WxMessagePush.pushMessage(sendObject);
        if (returnCode.getSuccess()) {
            //成功了才存库
            com.xiaoxin.entity.entity.SendObject save = new com.xiaoxin.entity.entity.SendObject();
            ReturnCode select = this.select(sendObject);
            if (select.getSuccess()) {
                //已有数据
                save = (com.xiaoxin.entity.entity.SendObject) select.getData().get("sendObject");
            }
            BeanUtils.copyProperties(sendObject, save, "updateTime","id");
            save.setAppSecret(sendObject.getSecret());
            if (save.getId() != null) {
                iSendObjectService.updateById(save);
            } else {
                iSendObjectService.save(save);
            }

            return ReturnCode.success().data("sendObject", save);
        }
        return returnCode;
    }

    @PostMapping("/getTask")
    public ReturnCode select(@RequestBody SendObject sendObject) {
        QueryWrapper<com.xiaoxin.entity.entity.SendObject> sendObjectQueryWrapper = new QueryWrapper<>();
        sendObjectQueryWrapper.eq("user_id", sendObject.getUserId());
        sendObjectQueryWrapper.eq("template_id", sendObject.getTemplateId());
        sendObjectQueryWrapper.eq("app_id", sendObject.getAppId());
        sendObjectQueryWrapper.eq("app_secret", sendObject.getSecret());

        com.xiaoxin.entity.entity.SendObject one = iSendObjectService.getOne(sendObjectQueryWrapper);
        if (ObjectUtils.isEmpty(one)) {
            return ReturnCode.fail().message("查不到数据");
        }
        return ReturnCode.success().data("sendObject", one);
    }

    @PostMapping("/removeTask")
    public ReturnCode remove(@RequestBody SendObject sendObject) {
        QueryWrapper<com.xiaoxin.entity.entity.SendObject> sendObjectQueryWrapper = new QueryWrapper<>();
        sendObjectQueryWrapper.eq("user_id", sendObject.getUserId());
        sendObjectQueryWrapper.eq("template_id", sendObject.getTemplateId());
        sendObjectQueryWrapper.eq("app_id", sendObject.getAppId());
        sendObjectQueryWrapper.eq("app_secret", sendObject.getSecret());

        com.xiaoxin.entity.entity.SendObject one = iSendObjectService.getOne(sendObjectQueryWrapper);
        if (ObjectUtils.isEmpty(one)) {
            return ReturnCode.fail().message("查不到数据");
        }

        iSendObjectService.removeById(one);
        return ReturnCode.success();
    }

    @GetMapping("/rePush/{id}")
    public String rePush(@PathVariable String id) {
        com.xiaoxin.entity.entity.SendObject select = iSendObjectService.getById(id);
        if (null != select) {
            SendObject sendObject = new SendObject();
            BeanUtils.copyProperties(select, sendObject);
            sendObject.setSecret(select.getAppSecret());
            WxMessagePush.pushMessage(sendObject);
            return "再次推送成功！";
        }
        return "查不到数据，无法再次推送！";
    }
}
