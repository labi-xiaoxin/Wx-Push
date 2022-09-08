package com.xiaoxin.core.core;

import com.xiaoxin.comm.ReturnCode;
import com.xiaoxin.core.entity.SendObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

/**
 * @author wangyp
 * @date 2022/08/25
 **/
public class WxMessagePush {

    /**
     * 推送微信公众号内容
     *
     * @param sendObject 对象数据
     */
    public static ReturnCode pushMessage(SendObject sendObject) {
        WxMpDefaultConfigImpl mpConfig = new WxMpDefaultConfigImpl();
        mpConfig.setAppId(sendObject.getAppId());
        mpConfig.setSecret(sendObject.getSecret());
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(mpConfig);

        WxMpTemplateMsgService templateMsgService = wxMpService.getTemplateMsgService();
        try {
            templateMsgService.sendTemplateMsg(MessageFactory.buildMessage(sendObject));
            return ReturnCode.success();
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ReturnCode.fail().message(e.getMessage());
        }
    }
}
