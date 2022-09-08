package com.xiaoxin.core.core;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.xiaoxin.core.consts.XiaoxinConst;
import com.xiaoxin.core.entity.SendObject;
import com.xiaoxin.core.entity.WeatherInfo;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.*;

import static cn.hutool.core.date.DateUtil.age;
import static com.xiaoxin.core.core.GaodeUtil.getAdcCode;

/**
 * @author DokiYolo
 * Date 2022-08-22
 */
public class MessageFactory {

    public static WxMpTemplateMessage buildMessage(SendObject sendObject) {

        return WxMpTemplateMessage.builder()
                //跳转路径：如果href有参数，则跳转指定路径；否则判断是否有id，有则触发再次推送，否则跳转默认路径。
                .url(StrUtil.emptyToDefault(sendObject.getHref(), null == sendObject.getId() ? XiaoxinConst.XIAO_XIN_GITEE : XiaoxinConst.HOST.concat("task/pushTask/rePush/").concat(String.valueOf(sendObject.getId()))))
                .toUser(sendObject.getUserId()).templateId(sendObject.getTemplateId())
                .data(buildData(sendObject))
                .build();
    }

    private static List<WxMpTemplateData> buildData(SendObject sendObject) {
        List<WxMpTemplateData> list = new ArrayList<>();

        JSONObject jsonObject = JSONObject.parseObject(sendObject.getTemplateContent());
        HashMap<String, String> city = new HashMap<>(2);
        HashMap<String, String> province = new HashMap<>(2);
        Map<String, String> color = new HashMap<>(6);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            @SuppressWarnings("unchecked")
            HashMap<String, String> value = (HashMap<String, String>) entry.getValue();
            //默认只取一行
            for (Map.Entry<String, String> map : value.entrySet()) {
                //生日处理
                if ("birthday".equals(entry.getKey())) {
                    int age = age(DateUtil.parse(map.getKey()), new Date());
                    list.add(TemplateDataBuilder.builder().name("age").value(Integer.toString(age)).color(map.getValue()).build());
                    list.add(TemplateDataBuilder.builder().name("nextBirthday").value(getNextDay(DateUtil.parse(map.getKey()))).color(map.getValue()).build());
                } else if ("anniversaryDay".equals(entry.getKey())) {
                    //纪念日处理
                    list.add(TemplateDataBuilder.builder().name("nextAnniversaryDay").value(getNextDay(DateUtil.parse(map.getKey()))).color(map.getValue()).build());
                } else if ("city".equals(entry.getKey())) {
                    //市处理
                    city.put("city", map.getKey());
                    city.put("color", map.getValue());
                    list.add(TemplateDataBuilder.builder().name("city").value((map.getKey())).color(map.getValue()).build());
                } else if ("province".equals(entry.getKey())) {
                    //省处理
                    province.put("province", map.getKey());
                    province.put("color", map.getValue());
                    list.add(TemplateDataBuilder.builder().name("province").value((map.getKey())).color(map.getValue()).build());
                } else if ("weather".equals(entry.getKey())) {
                    //天气颜色处理
                    color.put("weather", map.getValue());
                } else if ("temperature".equals(entry.getKey())) {
                    //天气颜色处理
                    color.put("temperature", map.getValue());
                } else if ("winddirection".equals(entry.getKey())) {
                    //天气颜色处理
                    color.put("winddirection", map.getValue());
                } else if ("windpower".equals(entry.getKey())) {
                    //天气颜色处理
                    color.put("windpower", map.getValue());
                } else if ("humidity".equals(entry.getKey())) {
                    //天气颜色处理
                    color.put("humidity", map.getValue());
                } else {
                    list.add(TemplateDataBuilder.builder().name(entry.getKey()).value(map.getKey()).color(map.getValue()).build());
                }
                break;
            }
        }
        WeatherInfo weather = GaodeUtil.getNowWeatherInfo(getAdcCode(province.get("province"), city.get("city")));
        list.add(TemplateDataBuilder.builder().name("weather").value(weather.getWeather()).color(color.get("weather") == null ? city.get("color") : color.get("weather")).build());
        list.add(TemplateDataBuilder.builder().name("temperature").value(weather.getTemperature()).color(color.get("temperature") == null ? city.get("color") : color.get("temperature")).build());
        list.add(TemplateDataBuilder.builder().name("winddirection").value(weather.getWinddirection()).color(color.get("winddirection") == null ? city.get("color") : color.get("winddirection")).build());
        list.add(TemplateDataBuilder.builder().name("windpower").value(weather.getWindpower()).color(color.get("windpower") == null ? city.get("color") : color.get("windpower")).build());
        list.add(TemplateDataBuilder.builder().name("humidity").value(weather.getHumidity()).color(color.get("humidity") == null ? city.get("color") : color.get("humidity")).build());

        return list;
    }

    private static String getNextDay(DateTime dateTime) {
        dateTime = DateUtil.beginOfDay(dateTime);
        DateTime now = DateUtil.beginOfDay(new Date());
        dateTime.offset(DateField.YEAR, now.year() - dateTime.year());
        if (now.isAfter(dateTime)) {
            return String.valueOf(dateTime.offset(DateField.YEAR, 1).between(now, DateUnit.DAY));
        }
        return String.valueOf(dateTime.between(now, DateUnit.DAY));
    }


    static class TemplateDataBuilder {
        /**
         * 参数名
         */
        private String name;
        /**
         * 参数值
         */
        private String value;
        /**
         * 颜色
         */
        private String color;

        public static TemplateDataBuilder builder() {
            return new TemplateDataBuilder();
        }

        public TemplateDataBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TemplateDataBuilder value(String value) {
            this.value = value;
            return this;
        }

        public TemplateDataBuilder color(String color) {
            this.color = color;
            return this;
        }

        public WxMpTemplateData build() {
            if (StrUtil.hasEmpty(name, value)) {
                throw new IllegalArgumentException("参数不正确");
            }
            WxMpTemplateData data = new WxMpTemplateData();
            data.setName(name);
            data.setValue(value);
            data.setColor(color);
            return data;
        }
    }

}
