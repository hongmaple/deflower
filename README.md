# deflower

## 介绍
该项目是前后端分离的卖花网站，有一个后端工程deflower，使用iade进行开发，基于jdk1.8，有两个前端工程，mall4uni，vue-admin-master，此项目采用单体架构，以使用最基础的技术来实现一个购物流程，使想初步接触电商的小伙伴能过快速理解这种类型的产品思想，实现流程，功能模块有，发布商品，花语解说，教学视频，管理员管理，前台用户管理，客服功能（简单的聊天），分类管理，个人中心等

## 软件架构
### 后端架构

1. 技术栈
| 技术 | 说明 | 官网 |
|spring|----|----|
|springMvc|    |    |
|mybatis|    |    |
|mybatisPlus|    |    |
|springBoot|    |    |
|websocket|    |    |
|pagehelper|    |    |
|Swagger2|    |    |
|lombok|    |    |
|modelmapper|    |    |
|fastjson|    |    |

2. 数据库用的是mysql8.0，使用Navicat进行管理
2.系统环境，windows ，jdk1.8
### 前端项目
#### mall4uni
感谢[mall4uni ](https://gitee.com/gz-yami/mall4uni)
介绍： 
一个基于springboot的开源商城 uniapp端
uniapp版本，做啥都不愁。即使不跨端，uni-app也是更好的小程序开发框架、更好的App跨平台框架、更方便的H5开发框架。不管领导安排什么样的项目，你都可以快速交付，不需要转换开发思维、不需要更改开发习惯。

![输入图片说明](https://images.gitee.com/uploads/images/2021/0330/105908_f19c45bb_8893195.png "142445_a8976d9e_5094767.png")
uni-app 是一个使用 Vue.js 开发所有前端应用的框架，开发者编写一套代码，可发布到iOS、Android、Web（响应式）、以及各种小程序（微信/支付宝/百度/头条/QQ/钉钉/淘宝）、快应用等多个平台。 ![输入图片说明](https://images.gitee.com/uploads/images/2021/0330/105942_fe5888a7_8893195.png "140954_a32581f8_5094767.png")

##### 开发环境启动
请使用 uniapp官方推荐的开发工具 HBuilderX 导入项目进行开发

编辑 utils/config.js 里的接口域名配置

点击工具栏的运行 -> 运行到内置浏览器 即可开发调试~
#### vue-admin-master
#### 基于vue3.0实现后台管理模板

> 感谢[vue-admin](https://github.com/MrZHLF/vue-admin)

> 使用vue最新脚手架搭建环境，编写界面，使用axios请求接口，渲染界面,实现页面登录注册，数据的增删改查,数据部分存储到easy-mock数据中。

##### 项目结构
```
  |——— client  #vue项目入口文件
	|——— config  #秘钥配置文件
	|——— node_modules #一些常用安装的依赖
	|——— models #接口模型
	|——— router       #接口文档
	|——— package.json #项目配置文件
	|___ README.md    #项目的说明文档，markdown 格式
```
##### 相关技术
	1. vuejs2.0：一套构建用户界面的渐进式框架，易用、灵活、高效。
	2. vue-router：官方的路由组件，配合vue.js创建单页应用（SPA）非常简单。
	3. axios: 基于 Promise 的 HTTP 请求客户端，可同时在浏览器和 node.js 中使用。
	
##### 功能介绍
	- Element-ui
	- 请求拦截和响应拦截
	- 富文本编辑器
	- Markdown编辑器
	- Echarts
	- tab数据切换
	- token本地存储
	- 表单
	- 高德地图引入
	- 登录注册
	- 路由守卫
	- vuex存储
	- 数据分页和查询
	- Excel表格导出
	- 递归组件
	- 主题更换
	- 404

##### 项目编译和运行
  + 可以直接在git上下载项目源码。把github下载到本地，
		git clone [](https://github.com/MrZHLF/vue-admin.git)
	+ 进入node-api-element当前页面初始化
		cnpm install
	+ 进入client项目目录中
		cnpm install
  + 所有依赖安装成功后执行启动命令在当前目录启动,不要在client进行启动，已经配置好前后端联调
	  npm run dev 
	  如果显示一下内容说明安装成功
	  I  Your application is running here: http://localhost:8080
#### 安装教程

1.  安装mysql，导入deflower\sql\deflower.sql创建deflower数据库
2.  打开iade导入deflower项目工程，使用maven下载依赖
3.  配置项目：
在src/resources/application.yml中
配置数据库连接
```
  datasource:
    url: jdbc:mysql://localhost:3306/deflower? 
    useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
```
配置文件上传

```
ruoyi:
  # 名称
  name: deflower
  # 版本
  version: 1.0.0
  # 版权年份
  copyrightYear: 2021
  # 文件路径 示例（ Windows配置D:/ruoyi/uploadPath，Linux配置 /home/ruoyi/uploadPath）
  profile: E:/GitWarehouse/deflower/uploadPath
```



#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)


