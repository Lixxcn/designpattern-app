# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

这是一个基于 Spring Boot 的设计模式学习应用，涵盖 GoF 23 种设计模式的详细讲解、示例代码和在线演示。

## 常用命令

### 构建与运行

```bash
# 编译项目
mvn clean install

# 运行应用（默认端口 28080）
mvn spring-boot:run

# 访问应用
# http://localhost:28080/
```

### 测试

```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=PatternRepositoryTest
mvn test -Dtest=SingletonServiceTest
mvn test -Dtest=ExecutionControllerTest
```

## 核心架构

### 分层架构

项目采用分层架构，核心设计原则是将设计模式的**元数据**与**代码实现**分离：

1. **Model 层** - `Pattern` 实体类，包含模式的描述性元数据
2. **Repository 层** - `PatternRepository`，内存存储，管理 23 个模式的基本信息
3. **Service 层** - 每个设计模式对应一个 Service（如 `SingletonService`），负责演示逻辑
4. **Controller 层** - `PatternController`（页面渲染）和 `ExecutionController`（REST API）
5. **Util 层** - `CodeReaderUtil`，通过包名动态读取源代码文件

### 包结构设计

```
src/main/java/cn/lixx/designpattern_app/
├── controller/          # 控制器层
├── model/              # 数据模型（Pattern, PatternCategory, PatternDifficulty）
├── repository/         # 数据访问层（PatternRepository）
├── service/pattern/    # 服务层
│   ├── creational/     # 创建型模式服务及实现代码
│   │   ├── singleton/      # 单例模式示例代码目录
│   │   ├── factorymethod/  # 工厂方法模式示例代码目录
│   │   └── ...
│   ├── structural/     # 结构型模式
│   └── behavioral/     # 行为型模式
└── util/               # 工具类（CodeReaderUtil）
```

### CodeReaderUtil 架构

**关键设计**：通过包名自动扫描和读取代码，避免硬编码文件名。

每个 Service 负责读取自己对应包下的代码：
```java
public String getCodeExample() {
    return codeReaderUtil.readCodeFromPackage(
        "cn.lixx.designpattern_app.service.pattern.creational.singleton",
        "Client" // 排除演示类
    );
}
```

包路径约定：`cn.lixx.designpattern_app.service.pattern.{category}.{patternName}`

## 添加新设计模式

当需要添加新的设计模式时，需要修改以下几个地方：

1. **创建示例代码包和文件**
   - 在 `service/pattern/{category}/` 下创建新模式目录
   - 添加模式实现的 Java 类

2. **创建 Service 类**
   - 继承标准模式：注入 `CodeReaderUtil`
   - 实现 `executeExample()` - 执行演示逻辑
   - 实现 `getCodeExample()` - 调用 `codeReaderUtil.readCodeFromPackage()`
   - 实现 `getMermaidDiagram()` - 返回 Mermaid 类图定义

3. **更新 PatternRepository**
   - 在构造函数中添加新模式元数据（使用 `Pattern.builder()`）

4. **更新 PatternController**
   - 添加 Service 字段
   - 添加到构造函数参数
   - 在 `getCodeExample()` switch 中添加 case
   - 在 `getMermaidDiagram()` switch 中添加 case

5. **更新 ExecutionController**
   - 添加 Service 字段
   - 添加到构造函数参数
   - 在 `execute()` switch 中添加 case

6. **创建测试**（可选但推荐）
   - 在 `src/test/java/` 下创建对应的测试类

## 模式 ID 映射

ExecutionController 和 PatternController 中使用的模式 ID：

| 分类 | 模式 ID | 中文名称 |
|------|---------|----------|
| 创建型 | singleton | 单例模式 |
| 创建型 | factory-method | 工厂方法模式 |
| 创建型 | abstract-factory | 抽象工厂模式 |
| 创建型 | builder | 建造者模式 |
| 创建型 | prototype | 原型模式 |
| 结构型 | adapter | 适配器模式 |
| 结构型 | bridge | 桥接模式 |
| 结构型 | composite | 组合模式 |
| 结构型 | decorator | 装饰器模式 |
| 结构型 | facade | 外观模式 |
| 结构型 | flyweight | 享元模式 |
| 结构型 | proxy | 代理模式 |
| 行为型 | chain | 责任链模式 |
| 行为型 | command | 命令模式 |
| 行为型 | interpreter | 解释器模式 |
| 行为型 | iterator | 迭代器模式 |
| 行为型 | mediator | 中介者模式 |
| 行为型 | memento | 备忘录模式 |
| 行为型 | observer | 观察者模式 |
| 行为型 | state | 状态模式 |
| 行为型 | strategy | 策略模式 |
| 行为型 | template | 模板方法模式 |
| 行为型 | visitor | 访问者模式 |

## API 端点

### 页面路由
- `GET /` - 主页
- `GET /pattern/{id}` - 模式详情页
- `GET /category/{category}` - 按分类筛选
- `GET /difficulty/{difficulty}` - 按难度筛选

### REST API
- `GET /execute/{patternId}` - 执行模式演示，返回 `{"output": "..."}`
