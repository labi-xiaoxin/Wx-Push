package com.xiaoxin.core.entity;

import javax.validation.constraints.NotNull;

/**
 * @author wangyp
 * @date 2022/08/25
 **/
public class SendObject {
    /**
     * 微信ID
     */
    @NotNull(message = "微信ID不能为空")
    private String userId;

    /**
     * 模版ID
     */
    @NotNull(message = "模版ID不能为空")
    private String templateId;

    /**
     * 测试号appID
     */
    @NotNull(message = "测试号ID不能为空")
    private String appId;

    /**
     * 测试号appSecret
     */
    @NotNull(message = "测试号appsecret不能为空")
    private String secret;

    /**
     * 模版内容
     */
    @NotNull(message = "模版内容不能为空")
    private String templateContent;

    /**
     * 跳转地址
     */
    private String href;

    /**
     * 高德Key
     */
    private String amapKey;

    /**
     * 诗歌类型
     */
    private String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAmapKey() {
        return amapKey;
    }

    public void setAmapKey(String amapKey) {
        this.amapKey = amapKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
