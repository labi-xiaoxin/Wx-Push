package com.xiaoxin.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author wangyp
 * @date 2022/08/25
 **/
@Getter
@Setter
public class SendObject {
    /**
     * 数据ID
     **/
    private Long id;

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
}
