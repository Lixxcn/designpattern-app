# 设计模式学习应用

一个基于 Spring Boot 的交互式设计模式学习平台，涵盖 GoF 23 种设计模式的详细讲解、示例代码和在线演示。

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.9-brightgreen)
![Tests](https://img.shields.io/badge/tests-36%20passing-brightgreen)

## 项目简介

本项目是一个用于学习和理解 GoF（Gang of Four）23 种设计模式的 Web 应用。通过直观的界面和可运行的代码示例，帮助开发者掌握每种设计模式的定义、结构、应用场景和实际用法。

### 主要特性

- **23 种设计模式完整覆盖** - 包含所有 GoF 设计模式
- **三种分类展示** - 按创建型、结构型、行为型分类浏览
- **难度等级筛选** - 初级、中级、高级三个难度级别
- **详细的模式说明** - 每个模式包含定义、意图、适用场景、优缺点
- **Mermaid 类图** - 可视化展示模式结构
- **完整代码示例** - 每个模式都有清晰的 Java 实现代码
- **在线演示功能** - 一键运行模式演示代码
- **实际应用案例** - Spring 框架和 JDK 中的应用实例
- **丰富的测试覆盖** - 36 个单元测试和集成测试

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Java | 17 | 编程语言 |
| Spring Boot | 3.5.9 | Web 框架 |
| Thymeleaf | 3.1 | 模板引擎 |
| Lombok | - | 简化代码 |
| JUnit 5 | 5.10 | 测试框架 |
| AssertJ | - | 断言库 |
| Mermaid.js | - | 图表渲染 |

## 快速开始

### 前置要求

- JDK 17 或更高版本
- Maven 3.6 或更高版本

### 安装运行

1. **克隆项目**
```bash
git clone https://github.com/yourusername/designpattern-app.git
cd designpattern-app
```

2. **编译项目**
```bash
mvn clean install
```

3. **运行应用**
```bash
mvn spring-boot:run
```

4. **访问应用**
```
http://localhost:28080/
```

### 运行测试

```bash
mvn test
```

## 项目结构

```
designpattern-app/
├── src/
│   ├── main/
│   │   ├── java/cn/lixx/designpattern_app/
│   │   │   ├── controller/           # 控制器层
│   │   │   │   ├── PatternController.java
│   │   │   │   └── ExecutionController.java
│   │   │   ├── service/
│   │   │   │   └── pattern/          # 服务层
│   │   │   │       ├── creational/   # 创建型模式
│   │   │   │       ├── structural/   # 结构型模式
│   │   │   │       └── behavioral/   # 行为型模式
│   │   │   ├── repository/           # 数据访问层
│   │   │   │   └── PatternRepository.java
│   │   │   └── model/                # 数据模型
│   │   │       ├── Pattern.java
│   │   │       ├── PatternCategory.java
│   │   │       └── PatternDifficulty.java
│   │   └── resources/
│   │       ├── templates/            # Thymeleaf 模板
│   │       │   ├── index.html
│   │       │   └── pattern-detail.html
│   │       └── static/               # 静态资源
│   │           ├── css/style.css
│   │           └── js/app.js
│   └── test/                         # 测试代码
│       └── java/cn/lixx/designpattern_app/
│           ├── controller/           # Controller 测试
│           ├── repository/           # Repository 测试
│           └── service/              # Service 测试
└── pom.xml
```

## 设计模式覆盖

### 创建型模式 (Creational Patterns)

这些模式处理对象的创建机制，旨在解耦对象的创建和使用。

| 模式 | 难度 | 描述 |
|------|------|------|
| [单例模式](#) | 初级 | 保证一个类仅有一个实例 |
| [工厂方法模式](#) | 初级 | 定义创建对象的接口，让子类决定实例化哪个类 |
| [抽象工厂模式](#) | 中级 | 创建相关对象家族 |
| [建造者模式](#) | 中级 | 分步构建复杂对象 |
| [原型模式](#) | 初级 | 通过克隆创建新对象 |

### 结构型模式 (Structural Patterns)

这些模式关注类和对象的组合，构建更大的结构。

| 模式 | 难度 | 描述 |
|------|------|------|
| [适配器模式](#) | 初级 | 将一个类的接口转换成客户希望的另一个接口 |
| [桥接模式](#) | 中级 | 将抽象部分与实现部分分离 |
| [组合模式](#) | 中级 | 将对象组合成树形结构 |
| [装饰器模式](#) | 中级 | 动态地给对象添加额外职责 |
| [外观模式](#) | 初级 | 为子系统提供统一接口 |
| [享元模式](#) | 高级 | 运用共享技术有效支持大量细粒度对象 |
| [代理模式](#) | 中级 | 为其他对象提供代理以控制访问 |

### 行为型模式 (Behavioral Patterns)

这些模式关注对象之间的通信和职责分配。

| 模式 | 难度 | 描述 |
|------|------|------|
| [责任链模式](#) | 中级 | 将请求沿处理者链传递 |
| [命令模式](#) | 中级 | 将请求封装为对象 |
| [解释器模式](#) | 高级 | 定义语言的文法表示 |
| [迭代器模式](#) | 初级 | 提供顺序访问聚合对象元素的方法 |
| [中介者模式](#) | 中级 | 用中介对象封装一系列对象交互 |
| [备忘录模式](#) | 中级 | 在不破坏封装性的前提下捕获对象状态 |
| [观察者模式](#) | 初级 | 定义对象间一对多的依赖关系 |
| [状态模式](#) | 中级 | 允许对象在内部状态改变时改变行为 |
| [策略模式](#) | 初级 | 定义算法族并使它们可互相替换 |
| [模板方法模式](#) | 初级 | 定义算法骨架，将某些步骤延迟到子类 |
| [访问者模式](#) | 高级 | 在不改变类结构的前提下定义新操作 |

## 功能说明

### 主页

- 展示所有 23 种设计模式卡片
- 按创建型、结构型、行为型分类筛选
- 按初级、中级、高级难度筛选
- 点击卡片查看模式详情

### 模式详情页

每个设计模式详情页包含以下标签页：

1. **模式说明**
   - 定义：模式的精确定义
   - 意图：模式的设计目标
   - 适用场景：何时使用该模式
   - 优缺点：使用该模式的利弊
   - 相关模式：与其他模式的关系

2. **类图**
   - Mermaid 格式的 UML 类图
   - 自动渲染的可视化图表

3. **示例代码**
   - 完整的 Java 实现代码
   - 代码语法高亮显示
   - "运行示例"按钮在线执行

4. **应用案例**
   - Spring 框架中的应用
   - JDK 标准库中的应用
   - 实际项目中的使用案例

## API 接口

### REST API

#### 执行模式演示

```http
GET /execute/{patternId}
```

**路径参数：**
- `patternId`: 模式 ID（如 `singleton`、`factory-method` 等）

**响应示例：**
```json
{
  "output": "=== 单例模式演示 ===\n\n饿汉式单例实例已创建\n..."
}
```

**状态码：**
- `200 OK`: 成功执行
- 其他 ID 返回默认消息

### 支持的模式 ID 列表

```
singleton, factory-method, abstract-factory, builder, prototype,
adapter, bridge, composite, decorator, facade, flyweight, proxy,
chain, command, interpreter, iterator, mediator, memento,
observer, state, strategy, template, visitor
```

## 测试

项目包含 36 个单元测试和集成测试，覆盖：

- Controller 层：Web 请求处理和页面渲染
- Repository 层：数据查询和筛选
- Service 层：设计模式演示逻辑

```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=PatternRepositoryTest

# 查看测试报告
open target/surefire-reports/index.html
```

## 配置说明

### 应用配置

```yaml
# application.yml
server:
  port: 28080

spring:
  application:
    name: designpattern-app
```

### 自定义配置

可以通过修改以下配置项自定义应用：

- `server.port`: 服务端口（默认 28080）

## 学习资源

### 推荐阅读

- 《设计模式：可复用面向对象软件的基础》- GoF
- 《Head First 设计模式》- Freeman
- 《重构：改善既有代码的设计》- Fowler

### 在线资源

- [Refactoring.Guru - 设计模式](https://refactoring.guru/design-patterns)
- [Spring IoC 容器](https://docs.spring.io/spring-framework/reference/core/beans.html)

## 贡献指南

欢迎贡献代码、报告问题或提出改进建议！

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

### 代码风格

- 遵循 Google Java Style Guide
- 使用 Lombok 减少样板代码
- 添加适当的 Javadoc 注释
- 为新功能编写测试

## 许可证

本项目采用 MIT 许可证 - 详见 LICENSE 文件

## 作者

- [Your Name](https://github.com/yourusername)

## 致谢

- [Spring Boot](https://spring.io/projects/spring-boot) - 强大的 Java Web 框架
- [Thymeleaf](https://www.thymeleaf.org/) - 现代 Java 模板引擎
- [Mermaid](https://mermaid.js.org/) - 图表和图表生成工具
- [Lombok](https://projectlombok.org/) - Java 标注库

---

**学习设计模式，编写更优雅的代码！**
