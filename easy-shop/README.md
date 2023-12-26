# 手机商城系统

***该项目为单应用模式，仅为了便于展示而开发，并非该项目的正式版本***

为便于用户在多个供应商间选择最适合自己的设备而产生的该系统的开发需求。该系统应能满足用户针对自身需求的快速查询，也可为供应商提供快速简便的商品管理服务。

## 一 项目需求分析

### 一.1 用户

基本用户类型，包含顾客和供应商两种角色的区别。

#### 一.1.1 顾客

标准顾客类，对商品进行访问，并可对属于自身的购物车进行管理和结算。

##### 一.1.1.1 顾客个人账户信息

用户的一些基本个人信息，用于顾客个人信息的校验、密码找回等操作。

##### 一.1.1.2 寄送信息

用户寄送地址信息，仅用户个人可进行对应的访问，其他人访问应确认个人信息。

#### 一.1.2 供应商

标准供应商类型，对商品及所属属性进行管理（包括但不限于新建与删除等操作）

##### 一.1.2.1 供应商账户信息

供应商的账户信息，仅对应的供应商可进行访问。（需将账号密码单独进行储存。）

### 一.2 商品

标准商品类，对商品的所有信息进行描述，包括但不限于颜色、系统、内存信息等

### 一.3 购物车

购物车类，仅顾客身份可对购物车进行操作（除供应商删除商品的特殊情况）

### 一.4 商品订单

订单类，对顾客的购物信息进行了相关记录。

## 二 实体类分析

### 二.1 用户 Account

共有对象：BaseAccountLoginInfo

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | Integer | 账户id（Primary Key） | T |
| password | String | 账户密码（特征值加密算法加密后密码） | T |

为保证安全，须通过外键查找后进行访问。

#### 二.1.1 顾客 User

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| name | String | 用户名称（仅自己可见） | T |
| nickname | String | 昵称（若为空则默认以name加随机尾缀作为值） | T |
| info | String | 个人简介 | T(nullable) |
| sex | boolean | 性别（男性为true，女性为false） | T |
| email | String | 个人邮箱（用于验证和密码重置） | T |
| token | String | Redis存储验证token确认登录身份 | F(nullable) |

##### 二.1.1.1 寄送地址(ManyToOne) SendInfo

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | String(UUID) | 地址独立id（Primary Key） | T |
| accountId | String | 对应账户id(Mapps Key) | T |
| name | String | 收件人姓名 | T |
| phone | String | 收件人电话号 | T |
| local | String | 收件人所在城市 | T |
| address | String | 住址详细 | T |
| tag | String | 标签 | T |
| default | Boolean | 默认地址（一个用户组下仅能有一个为true） | T |

#### 二.1.2 供应商 Supporter

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| name | String | 商家名称 | T |
| logo | String\|File | 文件 |  |
| info | String | 供应商简介 | T(nullable) |
| email | String | 办公邮箱（用于验证和密码重置） | T |
| token | String | Redis存储验证token确认登录身份 | F(nullable) |
| unitName | String | 单位名称 | T(nullable) |
| unitAddress | String | 单位地址 | T(nullable) |

### 二.2 商品 Production

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Long | 商品的绝对id，应可以通过该id反向得出该商品的属性信息（Primary Key） | T |
| name | String | 产品名 | T |
| brand | Brand | 品牌/公司名称 | T |
| color | Color | 颜色信息 | T |
| system | SystemType | 系统类型 | T |
| type | Type | 类型 | T |
| enable | Boolean | 是否启用 | T |
| subId | Short | 所属品牌下独立id | T |
| memoryAndDisk | Short | 内存及存储大小 | T |
| price | Double | 价格 | T |
| time | Long | 创建时间（System.currentTime()） | T |

0b000|0 0000 0000 0000 00|00 0000 0000 0000 00|00 000|0 0000|0000 |000|0|0000 0000 0000

保留位|品牌编号|内存及存储|颜色信息|系统|类型|是否启用|单个产品独立id

Color

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Integer | 颜色id，与品牌和产品的独立id相互绑定，校验时仅检查颜色位 | T |
| name | String | 颜色名称 | T |
| image | String\|File | 对应图片 | T |

0b|0000 0000 0000 000|0 0000|0000 0000 0000

品牌信息|颜色信息|单个产品独立id

Brand

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Short | 品牌独立id，应与供应商匹配 | T |
| name | String | 品牌名称 | T |

0b0|000 0000 0000 0000

留白|品牌有效id

MemoryAndDisk

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Short | 内存和硬盘大小信息id | T |
| name | String | 展示用名称（应自动生成） | T |
| memory | Short | 内存大小，基本扩展大小为2（预操作：(+1)*2，单位：GB），理论范围：2~64GB | T |
| disk | Short | 磁盘大小，基本扩展大小为16（预操作：(+1)*16，单位：GB），16GB~16TB | T |

0b0000 00|00 0000 0000

内存|硬盘

### 二.3 购物车 Cart

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | String(UUID) | 购物车独立id | T |
| userId | Integer | 用户ID(ManyToOne) | T |
| items | Map<Long,Short> | 购物车中物品信息 | T |

### 二.4 商品订单 Order

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | String(UUID) | 商品订单ID | T |
| accountId | Integer | 账户ID | T |
| sendInfoID | String | 寄送地址信息ID | T |
