# demo-shop 多模块示例

## 模块关系

```text
demo-shop-parent
├── demo-shop-common   公共 DTO
├── demo-shop-user     用户 DAO、Service，依赖 common
├── demo-shop-order    订单 Service，依赖 user、common
└── demo-shop-web      Spring Boot 启动类和 Controller，依赖 user、order
```

`demo-shop-order` 的 `OrderService` 通过构造器注入 `UserService`，并调用
`userService.findUserById(...)`。这是普通 Java 方法调用；Maven 子模块依赖负责
让 `demo-shop-order` 在编译和运行时能使用 `demo-shop-user` 中的类。

## 父 POM 和子 POM

- 子 POM 的 `<parent>` 继承父 POM 中的 `groupId`、`version`、编码、Java 编译配置以及测试依赖。
- 父 POM 的 `<dependencyManagement>` 统一管理 Spring Boot 和内部模块版本。它只管理版本，不会自动引入依赖；子模块仍需在 `<dependencies>` 中声明自己使用的模块。
- 父 POM 的 `<modules>` 让 Maven 在一次构建中发现各子项目，并根据依赖关系决定构建顺序。
- 只有 `demo-shop-web` 启用 Spring Boot Maven 插件并包含 Web 启动依赖。

## 运行

需要 JDK 8 或更高版本以及 Maven。在项目根目录执行：

```bash
mvn -pl demo-shop-web -am package
java -jar demo-shop-web\target\demo-shop-web-1.0-SNAPSHOT.jar
```

`-pl` 选择 web 模块，`-am` 同时构建它依赖的模块。服务启动后可试：

```text
http://localhost:8081/api-test.html
```

打开接口测试页可以调用下面列出的接口。也可直接访问：

```text
GET http://localhost:8081/demo/users
GET http://localhost:8081/demo/users/1
GET http://localhost:8081/demo/orders
GET http://localhost:8081/demo/orders/100/users/1
GET http://localhost:8081/demo/db/users
GET http://localhost:8081/demo/db/orders
```

数据库写入接口使用 JSON 请求体，单条接口返回受影响行数，批量接口返回成功写入的条数。批量写入在事务中执行：

```text
POST http://localhost:8081/demo/db/users
POST http://localhost:8081/demo/db/users/batch
POST http://localhost:8081/demo/db/orders
POST http://localhost:8081/demo/db/orders/batch
```

用户请求示例：

```json
{"name":"王五","age":25,"email":"wangwu@example.com"}
```

批量用户请求体是上述对象组成的 JSON 数组。订单请求示例：

```json
{"userId":1,"product":"键盘","amount":299.00}
```

批量订单请求体是上述对象组成的 JSON 数组。插入时数据库生成 ID；订单创建时间由数据库设置。请确保数据库中已存在对应的用户记录。

也可以运行模块间调用的单元测试：

```bash
mvn -pl demo-shop-order -am test
```
