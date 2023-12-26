# 手机商城系统

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

#### 二.1.1 顾客 User

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

#### 二.1.2 供应商 Supporter

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| name | String | 商家名称 | T |
| logo | String\|File | logo文件 | T(Nullable) |
| info | String | 供应商简介 | T(nullable) |
| email | String | 办公邮箱（用于验证和密码重置） | T |
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
| items | Map<Long,Short> | 订单内容 | T |
| sendInfoID | String | 寄送地址信息ID | T |

## 三 接口文档

### 三.1 Account

baseURL: /account

#### 三.1.1 登录验证

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

#### 三.1.2 用户注册

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

#### 三.1.3 用户登录

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

### 三.2 User 顾客

baseURL：/user

#### 三.2.1 设置个人信息

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

#### 三.2.2 更改个人邮箱

~~有一说一，这TM应该是业务流程最麻烦的一个了~~

POST /emailUpdate

RequestParam：

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | String | 账户Id | T |

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

#### 三.2.1 SendInfo 寄送地址

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
| accountId | String | 用户ID | T |

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

### 三.3 Supporter 供应商

baseURL: /supporter

#### 三.3.1 启用供应商账户权限（注册）

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

#### 三.3.2 更新资料

POST /update

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| accountId | Integer | 账户id（Primary Key） | T |
| logo | URL/File | logo文件 | T(Nullable) |
| info | String | 供应商简介 | T(nullable) |
| email | String | 办公邮箱（用于验证和密码重置） | T |
| unitName | String | 单位名称 | T(nullable) |
| unitAddress | String | 单位地址 | T(nullable) |

#### 三.3.3 设置推荐

POST /setRecommendations

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Integer | id | T |
| productionIds | List<Long> | 产品对应id | T |
| logo | File/URL(String) | 对应展示文件 | T |

Recommendation

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Integer | id | T |
| supporterId | Integer | 供应商ID | T |
| productionId | Long | 产品对应id | T |
| logo | File/URL(String) | 对应展示文件 | T |
| enable | Boolean | 是否启用 | T |

#### 三.3.4 禁用/启用推荐

POST /changeRecommendationStatus

| 属性名 | 属性类型 | 描述 | 是否必须 |
| -- | -- | -- | -- |
| id | Integer | id | T |
| enable | Boolean | 是否启用 | T |
