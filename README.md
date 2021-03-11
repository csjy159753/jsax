# springboot-Application

#### 项目介绍

#### springboot-Application 一个以模版系统demo。

1. ssm整合完成MybatisPlus逆向 curd。
2. ssm整合完成MybatisPlus多数据源及事务控制例子。
3. ssm+RESTful API curd + swagger。
4. aop切面日志，待优化线程池异步打印。
5. 策略路由支付方式。
6. XSS，CSRF攻击的优雅防护机制
7. redis缓存
8. rabbitMq消息队列
9. 添加请求转发功能

#### 软件架构

springboot2.0、mybatis plus 、mysql redis缓存 rabbitMq druid

#### 安装教程

1. jdk1.8以上环境
2. idea
3. maven 3.6 以上版本

#### 使用说明

1. run Application 应用程序
2. http://localhost:8084/swagger-ui/# 查看接口说明
3. 查看system系统模块功能介绍
4. http://localhost:8084/druid/index.html druid 查询 默认帐号密码 admin Mas@12345

### 系统表名

``
dictionary,file_store,file_store_type,sys_log,sys_login_count,sys_login_log,sys_organ,sys_organ_role,sys_permission,sys_permission_item,sys_region,sys_resource,sys_resource_item,sys_role,sys_user,sys_user_organ,sys_app_permission,sys_app_resource
``

## 0.0.1版本

### 初始化一个mysql版本

添加表单重复提交aop验证 DuplicateSubmitToken

## 0.0.2版本

### 修改用户组织机构关系

采用用户绑定机构 机构绑定菜单权限方式

## 0.0.3版本

### 添加baseControl

添加BaseController UserController  获取用户id 以及获取用户基本信息 取消当前请求参数获取用户id的行为
取消在开发者模式下不需要token就可以访问接口功能




