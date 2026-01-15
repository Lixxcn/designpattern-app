# CodeReaderUtil 架构说明

## 概述

本文档说明设计模式学习应用中，如何使用 `CodeReaderUtil` 实现动态代码读取的架构设计。

## 架构设计

### 核心原则

1. **包名驱动**：通过包名自动扫描和读取代码，而不是硬编码文件名
2. **Service 负责制**：每个 Service 负责读取自己对应包下的代码
3. **代码与展示同步**：修改源代码文件后，自动反映到应用展示中

### 核心组件

#### CodeReaderUtil

**位置**: `cn.lixx.designpattern_app.util.CodeReaderUtil`

**职责**:
- 根据包名扫描并读取该包下的所有 Java 源代码文件
- 按文件名排序，保证代码展示顺序一致
- 支持排除特定类（如演示类 Client）

**主要方法**:

```java
/**
 * 根据包名读取该包下所有 Java 文件的内容
 * @param packageName 包名，如 "cn.lixx.designpattern_app.service.pattern.creational.singleton"
 * @return 所有 Java 文件的内容合并字符串
 */
public String readCodeFromPackage(String packageName)

/**
 * 根据包名读取代码，排除指定类
 * @param packageName 包名
 * @param excludeClasses 要排除的类名（不含.java后缀）
 */
public String readCodeFromPackage(String packageName, String... excludeClasses)
```

**实现原理**:

1. 将包名转换为文件路径（`.` → `/`）
2. 构建源代码目录路径：`src/main/java/{包路径}`
3. 使用 `Files.walk()` 递归扫描目录
4. 过滤 `.java` 文件并排序
5. 读取每个文件内容并合并返回

### Service 层设计

每个 Service 类负责：

1. **executeExample()** - 执行模式演示逻辑
2. **getCodeExample()** - 从对应包读取示例代码
3. **getMermaidDiagram()** - 返回 Mermaid 类图定义

**标准模式**:

```java
@Service
public class XxxService {

    private final CodeReaderUtil codeReaderUtil;

    public XxxService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String executeExample() {
        // 执行演示逻辑
        return demonstration;
    }

    /**
     * 从 xxx 包读取所有示例代码
     */
    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.xxx.xxx"
        );
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    // Mermaid 类图定义
""";
    }
}
```

### 包结构映射

每个设计模式对应一个包，包名遵循以下约定：

```
service/pattern/
├── creational/          # 创建型模式
│   ├── singleton/       # 单例模式
│   ├── factorymethod/   # 工厂方法模式
│   ├── abstractfactory/ # 抽象工厂模式
│   ├── builder/         # 建造者模式
│   └── prototype/       # 原型模式
├── structural/          # 结构型模式
│   ├── adapter/         # 适配器模式
│   ├── bridge/          # 桥接模式
│   ├── composite/       # 组合模式
│   ├── decorator/       # 装饰器模式
│   ├── facade/          # 外观模式
│   ├── flyweight/       # 享元模式
│   └── proxy/           # 代理模式
└── behavioral/          # 行为型模式
    ├── chain/           # 责任链模式
    ├── command/         # 命令模式
    ├── interpreter/     # 解释器模式
    ├── iterator/        # 迭代器模式
    ├── mediator/        # 中介者模式
    ├── memento/         # 备忘录模式
    ├── observer/        # 观察者模式
    ├── state/           # 状态模式
    ├── strategy/        # 策略模式
    ├── template/        # 模板方法模式
    └── visitor/         # 访问者模式
```

### Controller 层

**PatternController** 负责协调：

```java
@Controller
public class PatternController {

    // 注入所有 23 个 Service
    private final SingletonService singletonService;
    private final FactoryMethodService factoryMethodService;
    // ... 其他 Service

    @GetMapping("/pattern/{id}")
    public String patternDetail(@PathVariable String id, Model model) {
        return patternRepository.findById(id)
                .map(pattern -> {
                    // 动态获取代码示例和类图
                    pattern.setCodeExample(getCodeExample(id));
                    pattern.setMermaidDiagram(getMermaidDiagram(id));
                    model.addAttribute("pattern", pattern);
                    return "pattern-detail";
                })
                .orElse("redirect:/");
    }

    private String getCodeExample(String patternId) {
        return switch (patternId) {
            case "singleton" -> singletonService.getCodeExample();
            case "factory-method" -> factoryMethodService.getCodeExample();
            // ... 其他模式
        };
    }
}
```

## 数据流

### 代码示例获取流程

```
用户访问 /pattern/singleton
    ↓
PatternController.patternDetail("singleton")
    ↓
PatternRepository.findById("singleton")
    ↓
返回 Pattern 对象（元数据）
    ↓
PatternController.getCodeExample("singleton")
    ↓
SingletonService.getCodeExample()
    ↓
CodeReaderUtil.readCodeFromPackage("cn.lixx.designpattern_app.service.pattern.creational.singleton")
    ↓
扫描 src/main/java/cn/lixx/designpattern_app/service/pattern/creational/singleton/ 目录
    ↓
读取所有 .java 文件（EagerSingleton.java, LazySingleton.java 等）
    ↓
合并文件内容并返回
    ↓
设置到 pattern.codeExample
    ↓
模板渲染，th:text="${pattern.codeExample}" 显示代码
```

## 优势

### 1. 不硬编码文件名

之前：需要手动列出每个文件
```java
private String readSingletonCode() {
    List<String> files = List.of(
        "EagerSingleton.java",
        "LazySingleton.java",
        "ThreadSafeLazySingleton.java",
        "StaticInnerClassSingleton.java",
        "Client.java"
    );
    return readFiles(files);
}
```

现在：通过包名自动扫描
```java
public String getCodeExample() {
    return codeReaderUtil.readCodeFromPackage(
        "cn.lixx.designpattern_app.service.pattern.creational.singleton",
        "Client" // 排除演示类
    );
}
```

### 2. 代码与展示自动同步

- 修改 `EagerSingleton.java` 源代码
- 无需修改 Service 类
- 刷新页面即可看到最新代码

### 3. 添加新模式更简单

只需：
1. 创建新的 Service 类
2. 创建对应的包和示例代码文件
3. 在 PatternRepository 添加元数据
4. 在 PatternController 添加 switch case

### 4. 包名相对固定

包名不会轻易改变，比文件名列表更稳定：
- 文件名可能增删
- 包结构相对稳定

## 示例代码文件组织

每个模式的示例代码按照标准组织：

```
singleton/
├── EagerSingleton.java        # 饿汉式单例
├── LazySingleton.java          # 懒汉式单例
├── ThreadSafeLazySingleton.java # 线程安全懒汉式
├── StaticInnerClassSingleton.java # 静态内部类
└── Client.java                  # 演示类（通常被排除）
```

`CodeReaderUtil` 会：
1. 扫描整个目录
2. 排除 `Client.java`
3. 按字母顺序读取其他文件
4. 合并为一个字符串返回

## 错误处理

### 包不存在

如果包对应的目录不存在，`CodeReaderUtil` 返回：
```
// 包不存在: cn.lixx.designpattern_app.service.pattern.xxx.xxx
```

### 包为空

如果包下没有 Java 文件：
```
// 该包下没有 Java 文件
```

### 文件读取失败

如果某个文件无法读取：
```
// 无法读取文件: Xxx.java
```

这些错误消息会直接显示在代码示例区域，便于调试。

## 扩展性

### 添加新的设计模式

1. **创建包结构**
```
src/main/java/cn/lixx/designpattern_app/service/pattern/creational/newpattern/
├── Product.java
├── ConcreteProduct.java
└── Client.java
```

2. **创建 Service**
```java
@Service
public class NewPatternService {
    private final CodeReaderUtil codeReaderUtil;

    public NewPatternService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.creational.newpattern",
            "Client"
        );
    }

    public String getMermaidDiagram() {
        return "classDiagram\n    ...";
    }
}
```

3. **更新 Controller**
```java
// 添加字段
private final NewPatternService newPatternService;

// 添加到构造函数参数

// 添加到 switch
case "newpattern" -> newPatternService.getCodeExample();
```

## 总结

使用 `CodeReaderUtil` 的架构设计实现了：

- ✅ 不硬编码文件名
- ✅ 包名驱动，更稳定
- ✅ 代码与展示自动同步
- ✅ Service 负责自己包的代码读取
- ✅ 易于扩展和维护
