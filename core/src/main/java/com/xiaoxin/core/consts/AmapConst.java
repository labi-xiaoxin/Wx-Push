package com.xiaoxin.core.consts;

/**
 * 高德地图静态常量
 * @author wangyp
 * @date 2022/08/25
 **/
public class AmapConst {

    /**
     * 查询城市编码
     */
    private static final String GEO_API = "https://restapi.amap.com/v3/geocode/geo?key=%s&address=%s&city=%s";

    /**
     * 查询天气
     */
    private static final String WEATHER_API = "https://restapi.amap.com/v3/weather/weatherInfo?key=%s&city=%d&extensions=%s";
}
