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
GET http://localhost:8080/demo/users
GET http://localhost:8080/demo/users/1
GET http://localhost:8080/demo/orders
GET http://localhost:8080/demo/orders/100/users/1
```

也可以运行模块间调用的单元测试：

```bash
mvn -pl demo-shop-order -am test
```
