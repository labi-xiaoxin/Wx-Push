<p align="center">
	<strong>微信推送平台-测试号定制推送</strong>
</p>


----------

## 🌆 简介

微信推送平台-测试号定制推送，是基于`微信公众平台-公众平台测试账号`进行消息推送，目前可基于模版进行部分定制推送。

----------

## 📄 使用手册

### 1、 进入`微信公众平台-公众平台测试账号`

> [点我直达](https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index) https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index

### 2、 进入 测试号管理 页面

![图片](http://mmbiz.qpic.cn/mmbiz_png/BMkwvicD8UticefKMO8dFGPX4PicKvEqf6ZfuQSkw3ZxDMxibYv45ua0e4K9QdBSEPK7G3U5EQbjFMskm7eJG8GiaeA/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

**记录appID、appsecret**



### 3、 新增测试模版

![图片](http://mmbiz.qpic.cn/mmbiz_png/BMkwvicD8UticefKMO8dFGPX4PicKvEqf6ZuEv7vq4qt1picywwPBV4icsOvhsIGyldotEYFTSFRqiaQbQjyOkuPovAQ/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

![图片](http://mmbiz.qpic.cn/mmbiz_png/BMkwvicD8UticefKMO8dFGPX4PicKvEqf6ZbbG4qzd3hC3qfEyH4SXNkfvST3ibeQKZRHolSn4UTqnve3XqaOiaTVBQ/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

![图片](https://mmbiz.qpic.cn/mmbiz_png/BMkwvicD8Utibz8b8RIbuP0YLXUVjibDdB08BUzSv4FQqYJGWLXUdVO0fBQdjfbdDibPlOypaiaEibOzWwjUFfkZ3cww/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)



**这里模版标题将对应推送的内容上方标题，模版内容对应推送的内容！！！**

以下是我的模版内容，可根据需要自行修改。

```json
早上好，{{name.DATA}} 
今年{{age.DATA}}岁啦 
距离下一次生日:{{nextBirthday.DATA}}天 
距离我们的下一次纪念日:{{nextAnniversaryDay.DATA}}天 
现在:{{province.DATA}}{{city.DATA}} 
当前天气:{{weather.DATA}} 
当前气温:{{temperature.DATA}} 
风力描述:{{winddirection.DATA}} 
风力级别:{{windpower.DATA}} 
空气湿度:{{humidity.DATA}} 
{{content.DATA}}
```



### 4、 添加TA为测试号

![图片](https://mmbiz.qpic.cn/mmbiz_png/BMkwvicD8UticefKMO8dFGPX4PicKvEqf6Z8qHstcYvryKVP8mOYXZdLx66ONfLP1Zh2ibeAFco8ecXdsNHKNhbnfA/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

**记录这里的微信号**



### 5、 编辑推送模版内容

根据添加的测试模版，编辑推送模版内容

我的模版内容为：

```json
{  
  'name': {    '迷茫的21世纪的新轻年': '#ffcc33'  },  
  'birthday':{    '1998-04-04': '#A52A2A'  },  
  'anniversaryDay': {    '2022-08-25': '#C247C2'  },  
  'province':{   '福建省': '#00FFFF'  },  
  'city': {    '厦门市':'#00FFFF'  },  
  'content': {  '越来越有钱！！！': '#0a5f5f'  },
  'weather': { 'color' : '#8A2E2E'},
  'temperature': { 'color' : '#7AFFFF'},
  'winddirection': { 'color' : '#E00070'},
  'windpower': { 'color' : '#A3A300'},
  'humidity': { 'color' : '#E00070'}
}
```



### 6、 测试推送

微信关注公众号：`迷茫的21世纪的新青年`，右侧➡️`小功能`，`推送服务`，点击`阅读原文`。

- 输入1中的appID、appsecret
- 输入4中的微信号
- 输入3中新增的测试模版的模版ID
- 输入5中的推送模版内容

![image-20220907133513830](/Users/wangyiping/Library/Application Support/typora-user-images/image-20220907133513830.png)



- 查询数据：输入appId、app secret、微信号ID、模版ID 即可查询是否已有数据。
- 测试推送：输入完整表单，能够发送一次推送。
- 添加定时推送：输入完整表单，发送一次推送，推送成功即可加入定时推送（每天早上8点）。
- 删除推送：输入appId、app secret、微信号ID、模版ID 即可删除已有定时推送任务。

## 📖 推送数据处理

```json
{
  '模版字段1': { '模版内容': '字体颜色' }，
  '模版字段2': { '模版内容': '字体颜色' }
}
```



|     描述     |    推送参数    |      处理参数      |
| :----------: | :------------: | :----------------: |
|  下一个生日  |    birthday    |    nextBirthday    |
| 下一个纪念日 | anniversaryDay | nextAnniversaryDay |
|     天气     | province、city |      weather       |
|     气温     | province、city |    temperature     |
|   风力描述   | province、city |   winddirection    |
|   风力级别   | province、city |     windpower      |
|   空气湿度   | province、city |      humidity      |
|     ...      |      ...       |        ...         |













