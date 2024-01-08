# 手机商城系统

目录：

[TOC]

<!-- vscode-markdown-toc -->
* 1. [一 项目需求分析](#一-项目需求分析)
  * 1.1. [一.1 用户](#一.1-用户)
    * 1.1.1. [一.1.1 顾客](#一.1.1-顾客)
    * 1.1.2. [一.1.2 供应商](#一.1.2-供应商)
  * 1.2. [一.2 商品](#一.2-商品)
  * 1.3. [一.3 购物车](#一.3-购物车)
  * 1.4. [一.4 商品订单](#一.4-商品订单)
* 2. [二 实体类分析](#二-实体类分析)
  * 2.1. [二.1 用户 Account](#二.1-用户-account)
    * 2.1.1. [二.1.1 顾客 User](#二.1.1-顾客-user)
    * 2.1.2. [二.1.2 供应商 Supporter](#二.1.2-供应商-supporter)
  * 2.2. [二.2 商品 Production](#二.2-商品-production)
    * 2.2.1. [二.2.1 推荐对象 Recommendation（Supporter Only）](#二.2.1-推荐对象-recommendation（supporter-only）)
  * 2.3. [二.3 购物车 Cart](#二.3-购物车-cart)
  * 2.4. [二.4 商品订单 Order](#二.4-商品订单-order)
* 3. [三 接口文档](#三-接口文档)
  * 3.1. [三.1 Account](#三.1-account)
    * 3.1.1. [三.1.1 登录验证](#三.1.1-登录验证)
    * 3.1.2. [三.1.2 用户注册](#三.1.2-用户注册)
    * 3.1.3. [三.1.3 用户登录](#三.1.3-用户登录)
  * 3.2. [三.2 User 顾客](#三.2-user-顾客)
    * 3.2.1. [三.2.1 设置个人信息](#三.2.1-设置个人信息)
    * 3.2.2. [三.2.2 更改个人邮箱](#三.2.2-更改个人邮箱)
    * 3.2.3. [三.2.1 SendInfo 寄送地址](#三.2.1-sendinfo-寄送地址)
  * 3.3. [三.3 Supporter 供应商](#三.3-supporter-供应商)
    * 3.3.1. [三.3.1 启用供应商账户权限（注册）](#三.3.1-启用供应商账户权限（注册）)
    * 3.3.2. [三.3.2 供应商账户登录](#三.3.2-供应商账户登录)
    * 3.3.3. [三.3.3 更新资料](#三.3.3-更新资料)
    * 3.3.4. [三.3.4 设置推荐](#三.3.4-设置推荐)
    * 3.3.5. [三.3.5 禁用/启用推荐](#三.3.5-禁用/启用推荐)
    * 3.3.6. [三.3.6 新建商品](#三.3.6-新建商品)
  * 3.4. [三.4 购物车](#三.4-购物车)
    * 3.4.1. [三.4.1 创建购物车](#三.4.1-创建购物车)
    * 3.4.2. [三.4.2 添加商品](#三.4.2-添加商品)
    * 3.4.3. [三.4.3 删除商品](#三.4.3-删除商品)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

为便于用户在多个供应商间选择最适合自己的设备而产生的该系统的开发需求。该系统应能满足用户针对自身需求的快速查询，也可为供应商提供快速简便的商品管理服务。

## 1. <a name='一-项目需求分析'></a>一 项目需求分析

### 1.1. <a name='一.1-用户'></a>一.1 用户

基本用户类型，包含顾客和供应商两种角色的区别。

#### 1.1.1. <a name='一.1.1-顾客'></a>一.1.1 顾客

标准顾客类，对商品进行访问，并可对属于自身的购物车进行管理和结算。

##### 一.1.1.1 顾客个人账户信息

用户的一些基本个人信息，用于顾客个人信息的校验、密码找回等操作。

##### 一.1.1.2 寄送信息

用户寄送地址信息，仅用户个人可进行对应的访问，其他人访问应确认个人信息。

#### 1.1.2. <a name='一.1.2-供应商'></a>一.1.2 供应商

标准供应商类型，对商品及所属属性进行管理（包括但不限于新建与删除等操作）

##### 一.1.2.1 供应商账户信息

供应商的账户信息，仅对应的供应商可进行访问。（需将账号密码单独进行储存。）

### 1.2. <a name='一.2-商品'></a>一.2 商品

标准商品类，对商品的所有信息进行描述，包括但不限于颜色、系统、内存信息等

### 1.3. <a name='一.3-购物车'></a>一.3 购物车

购物车类，仅顾客身份可对购物车进行操作（除供应商删除商品的特殊情况）

### 1.4. <a name='一.4-商品订单'></a>一.4 商品订单

订单类，对顾客的购物信息进行了相关记录。

## 2. <a name='二-实体类分析'></a>二 实体类分析

### 2.1. <a name='二.1-用户-account'></a>二.1 用户 Account

共有对象：BaseAccountLoginInfo

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | Integer | 账户id（Primary Key） | T |
| password | String | 账户密码（特征值加密算法加密后密码） | T |

为保证安全，须通过外键查找后进行访问。

共有对象：AccountLoginInfo

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | Integer | 账户id（Primary Key） | T |
| token | String | 用户登录信息缓存 | F |

账户类型映射（理应自动构建）

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | Integer | 账户id（Primary Key） | T |
| isSupporter | Boolean | 是否启用供应商账户 | T |

#### 2.1.1. <a name='二.1.1-顾客-user'></a>二.1.1 顾客 User

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| name | String | 用户名称（仅自己可见） | T |
| nickname | String | 昵称（若为空则默认以name加随机尾缀作为值） | T |
| info | String | 个人简介 | T(nullable) |
| sex | boolean | 性别（男性为true，女性为false） | T |
| email | String | 个人邮箱（用于验证和密码重置） | T |

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
| defaultSelected | Boolean | 默认地址（一个用户组下仅能有一个为true） | T |

#### 2.1.2. <a name='二.1.2-供应商-supporter'></a>二.1.2 供应商 Supporter

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| name | String | 商家名称 | T |
| logo | String\|File | logo文件 | T(Nullable) |
| info | String | 供应商简介 | T(nullable) |
| email | String | 办公邮箱（用于验证和密码重置） | T |
| unitName | String | 单位名称 | T(nullable) |
| unitAddress | String | 单位地址 | T(nullable) |

### 2.2. <a name='二.2-商品-production'></a>二.2 商品 Production

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

Brand

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Short | 品牌独立id，应与供应商匹配 | T |
| name | String | 品牌名称 | T |

0b0|000 0000 0000 0000

留白|品牌有效id

Color

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Integer | 颜色id，与品牌和产品的独立id相互绑定，校验时仅检查颜色位 | T |
| name | String | 颜色名称 | T |
| image | String\|File | 对应图片 | T |

0b|0000 0000 0000 000|0 0000|0000 0000 0000

品牌信息|颜色信息|单个产品独立id

Type

```java
public enum Type {
    /**
     * 官方机
     */
    OfficialMachine("官方机", (short) 0b1),
    /**
     * 官翻机
     */
    OfficialFlipMachine("官翻机", (short) 0b10),
    /**
     * 品质二手
     */
    OfficialSecondHand("品质二手", (short) 0b100);

    @Id
    private short typeCode;
    private String type;

    private Type(String type, short typeCode) {
        this.type = type;
        this.typeCode = typeCode;
    }

    public String getType() {
        return type;
    }

    public short getTypeCode() {
        return typeCode;
    }

}
```

MemoryAndDisk

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Short | 内存和硬盘大小信息id | T |
| name | String | 展示用名称（应自动生成） | T |
| memory | Short | 内存大小，基本扩展大小为2（预操作：(+1)*2，单位：GB），理论范围：2~32GB | T |
| disk | Short | 磁盘大小，基本扩展大小为16（预操作：(+1)*16，单位：GB），16GB~8TB | T |

0b0000 00|00 0000 0000
0b1111 11|11 1111 1111表示其他。

内存|硬盘

#### 2.2.1. <a name='二.2.1-推荐对象-recommendation（supporter-only）'></a>二.2.1 推荐对象 Recommendation（Supporter Only）

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Integer | id | T |
| supporterId | Integer | 供应商ID | T |
| productionId | Long | 产品对应id | T |
| logo | File/URL(String) | 对应展示文件 | T |
| enable | Boolean | 是否启用 | T |

### 2.3. <a name='二.3-购物车-cart'></a>二.3 购物车 Cart

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | String(UUID) | 购物车独立id | T |
| userId | Integer | 用户ID(ManyToOne) | T |
| items | Map<Long,Short> | 购物车中物品信息 | T |

### 2.4. <a name='二.4-商品订单-order'></a>二.4 商品订单 Order

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | String(UUID) | 商品订单ID | T |
| accountId | Integer | 账户ID | T |
| items | Map<Long,Short> | 订单内容 | T |
| sendInfoID | String | 寄送地址信息ID | T |

## 3. <a name='三-接口文档'></a>三 接口文档

### 3.1. <a name='三.1-account'></a>三.1 Account

baseURL: /account

#### 3.1.1. <a name='三.1.1-登录验证'></a>三.1.1 登录验证

GET /check

Cookies：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| token | String | 前端传递Token | T |

确认登录：

状态码：200

```json
{
    "data":"Account already Login in."
}
```

用户未登录或Token为空：

状态码：502

```json
{
    "data":"Account info not exist !"
}
```

#### 3.1.2. <a name='三.1.2-用户注册'></a>三.1.2 用户注册

POST /register

RequestBody：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| name | String | 用户名 | T |
| password | String | 用户密码 | T |
| email | String | 用户邮箱 | T |

请求成功：

状态码：200

```json
{
    "data":"Already Sent a confirm email to your email address."
}
```

GET /confirm

RequestParam：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | String | 生成账户ID | T |
| passwd | String | 账户验证信息，存于Redis中 | T |

验证成功：

状态码：200

```json
{
    "id":"生成的ID"
}
```

#### 3.1.3. <a name='三.1.3-用户登录'></a>三.1.3 用户登录

GET /login

RequestBody：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | String | 用户ID | F |
| name | String | 用户名(三者至少存在一个有效数据) | F |
| email | String | 用户邮箱 | F |
| password | String | 密码 | T |

~~什么？怎么判断是哪个？你TM用正则啊！这TM是你前端就能直接解决的问题！~~

登录成功：

状态码：200

```json
{
    "token":"生成的token或前端传回token(若存在)，会将登录记录添加进Redis作为缓存"
}
```

登录失败：

状态码：404

```json
{
    "data":"No Available info found."
}
```

### 3.2. <a name='三.2-user-顾客'></a>三.2 User 顾客

baseURL：/user

#### 3.2.1. <a name='三.2.1-设置个人信息'></a>三.2.1 设置个人信息

POST /info

RequestBody：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| nickname | String | 昵称 | F |
| info | String | 个人简介 | F |
| sex | String | 性别（true/false） | T |

不提供邮箱和token的修改，邮箱必须经过验证才可进行修改。

更改成功：

状态码：200

```json
{
    "name":"",
    "nickname":"",
    "info":"",
    "sex":"",
    "email":""
}
```

#### 3.2.2. <a name='三.2.2-更改个人邮箱'></a>三.2.2 更改个人邮箱

~~有一说一，这TM应该是业务流程最麻烦的一个了~~

POST /emailUpdate

RequestParam：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | Integer | 账户Id | T |

会发送一个验证码至用户邮箱，需要用户确认后才可进行更改

方法设计：将用户Id和随机生成的验证码作为键值对存入Redis（验证码为键，用户ID作为值）

GET /emailUpdate/confirm

RequestBody：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | String | 账户id | T |
| code | String | 验证码 | T |
| newEmail | String | 新邮箱地址 | T |

用户id和验证码验证成功：

状态码：200

```json
{
    "check-code":"随机生成一个特征值，并将特征值通过邮件一并发送，若特征值不符合则禁止修改行为（特征值不符则表明更改邮箱行为可能为被诱导行为或异常获取）"
}
```

向用户新邮箱发送一封验证邮件，验证成功后移除

POST /emailUpdate/confirm

RequestBody：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | String | 账户id | T |
| checkCode | String | 传递特征值（页面接收数据，可隐藏传递） | T |
| code | String | 新邮箱验证码 | T |

验证通过：

状态码：200

```json
{
    "accountId":"",
    "name":"",
    "nickname":"",
    "info":"",
    "sex":"",
    "email":""
}
```

#### 3.2.3. <a name='三.2.1-sendinfo-寄送地址'></a>三.2.1 SendInfo 寄送地址

baseURL：/sendInfo

##### 三.2.1.1 创建新地址信息

POST /create

RequestBody：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | String | 用户ID（我TM知道可以用token从Redis中读，但是能直接传过来我TM为啥要再去增加一次缓存的负载？而且我TM还得验证登录用户信息，这玩意还能起到一个欺诈作用） | T |
| name | String | 收件人姓名 | T |
| phone | String | 收件人电话号 | T |
| local | String | 收件人所在城市 | T |
| address | String | 具体地址信息 | T |
| tag | String | 标签 | T |

创建的时候没必要让用户选是否为默认，否则更改会很麻烦。

添加成功：

状态码：200

```json
{
    "data":"Success!"
}
```

添加失败：500

##### 三.2.1.2 查询所有地址信息

GET /getInfo

RequestBody：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | Integer | 用户ID | T |

这玩意还得验证登录用户和查询用户ID是否相同，否则不返回

~~或者我直接从token读，只要token正常的话……~~

正常查询：

状态码：200

```json
{
    "sendInfo":[
        ...
    ]
}
```

##### 三.2.1.3 删除地址信息

DELETE /delete

RequestBody：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | String | 地址信息Id | T |

需检查所属Id是否正确，否则无法删除。

删除成功：

状态码：200

```json
{
    // 被删除的地址信息，也可以直接忽略。
}
```

删除失败：

状态码：404

```json
{
    "data":"Fail to Delete!"
}
```

### 3.3. <a name='三.3-supporter-供应商'></a>三.3 Supporter 供应商

baseURL: /supporter

#### 3.3.1. <a name='三.3.1-启用供应商账户权限（注册）'></a>三.3.1 启用供应商账户权限（注册）

POST /enable

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | Integer | 账户id（Primary Key） | T |
| logo | URL/File | logo文件 | T(Nullable) |
| info | String | 供应商简介 | T(nullable) |
| email | String | 办公邮箱（用于验证和密码重置） | T |
| unitName | String | 单位名称 | T(nullable) |
| unitAddress | String | 单位地址 | T(nullable) |
| confirmFile | URL/File | 营业资格认证文件 | T |

注：文件过大应单独上传后通过认证码读取文件

#### 3.3.2. <a name='三.3.2-供应商账户登录'></a>三.3.2 供应商账户登录

~~按理来说，供应商账户登录需要用邮箱或其他方式来认证，但是我觉得作为一个实训项目来说比较麻烦而且应该没人乐意去写，顺便我想偷个懒。就是这样。~~

GET /supporterCheck

Cookies:

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| token | String | 账户token | T |

验证成功：200

```json
```

验证失败：502

```json
{
    "data":"Your Account is not enable supporter access!"
}
```

#### 3.3.3. <a name='三.3.3-更新资料'></a>三.3.3 更新资料

POST /update

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | Integer | 账户id（Primary Key） | T |
| logo | URL/File | logo文件 | T(Nullable) |
| info | String | 供应商简介 | T(nullable) |
| email | String | 办公邮箱（用于验证和密码重置） | T |
| unitName | String | 单位名称 | T(nullable) |
| unitAddress | String | 单位地址 | T(nullable) |

#### 3.3.4. <a name='三.3.4-设置推荐'></a>三.3.4 设置推荐

POST /setRecommendations

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Integer | id | T |
| productionIds | List<Long> | 产品对应id | T |
| logo | File/URL(String) | 对应展示文件 | T(Nullable) |

#### 3.3.5. <a name='三.3.5-禁用/启用推荐'></a>三.3.5 禁用/启用推荐

POST /changeRecommendationStatus

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Integer | id | T |
| enable | Boolean | 是否启用 | T |

#### 3.3.6. <a name='三.3.6-新建商品'></a>三.3.6 新建商品

需要先新建对应的一些参数信息如颜色

POST /createProduction

Body:

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | int | 该供应商提供的产品的个数（对应id，提供接口进行查询） | T |
| name | String | 产品名 | T |
| brand | int | 供应商对应品牌/公司id | T |
| color | int | 构建的对应颜色的id | T |
| system | int | 系统类型对应id | T |
| type | int | 提供类型的对应id | T |
| enable | boolean | 是否启用 | T |
| memoryAndDisk | int | 内存及存储大小对应id | T |
| price | double | 价格 | T |

##### 三.3.6.1 为产品创建颜色信息

POST /createColorInfo

Params:

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| name | String | 颜色名称 | T |
| image | String\|File | 展示图片,若没有可为空（但是得有对应的空的图片处理方案） | T(Nullable) |

Cookies:

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| token | String | 对应Supporter登录token | T |

创建成功：200

```json
{
    "id":,
    "name":"",
    "image":""
}
```

##### 三.3.6.2 获取内存及存储信息id

内存及存储是可以静态全包含的，所以传数据后返回匹配的id即可

GET /memoryAndDiskIdInfo

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| memory | String(Integer) | 内存大小（memory<2或memory>32时认为为other） | T |
| disk | String | 磁盘大小(disk<16或disk>8192GB时认为是其他) | T |

正常响应：200

```json
{
    "id":,
    "name":"自动生成",
    "memory":,
    "disk":
}
```

### 3.4. <a name='三.4-购物车'></a>三.4 购物车

baseURL: /cart

#### 3.4.1. <a name='三.4.1-创建购物车'></a>三.4.1 创建购物车

POST /createCart

Cookies：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| token | String(UUID) | 商品订单ID | T |

创建成功：200

```json
{
    "id":""
}
```

创建失败：404

```json
{
    "message":"用户未登录！"
}
```

#### 3.4.2. <a name='三.4.2-添加商品'></a>三.4.2 添加商品

POST /addProductions

Params:

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | String(UUID) | 购物车Id | T |
| itemId | Long | 指定产品Id | T |
| count | short | 产品个数(我不认为会有人一单打算购买>32767个手机……) | T |

添加成功：200

#### 3.4.3. <a name='三.4.3-删除商品'></a>三.4.3 删除商品

POST,DELETE(Supported) /deleteProduction

Params:

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | String(UUID) | 购物车Id | T |
| itemId | Long | 指定产品Id | T |

删除成功：200

删除失败（未找到）:404
