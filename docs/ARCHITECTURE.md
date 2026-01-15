# 架构设计说明

## 概述

本项目采用分层架构设计，将设计模式的元数据与实际代码实现分离，实现动态加载和更好的可维护性。

## 架构原则

### 1. 关注点分离
- **元数据**：存储在 `PatternRepository` 中，包含模式的基本描述信息
- **代码实现**：存储在实际的 Java 源代码文件中
- **类图定义**：通过 `SourceCodeReader` 动态生成或预定义

### 2. 单一职责
- 每个组件只负责一个明确的功能
- Repository 只负责元数据管理
- Service 只负责演示逻辑
- Controller 只负责 HTTP 请求处理

### 3. 开闭原则
- 新增设计模式只需添加对应的源代码文件
- 无需修改现有代码即可扩展

## 核心组件

### 1. Model 层

#### Pattern.java
```java
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Pattern {
    private String id;
    private String name;
    private String nameEn;
    private PatternCategory category;
    private PatternDifficulty difficulty;
    private String definition;
    private String intent;
    private String useCases;
    private String participants;
    private String collaboration;
    private String prosCons;
    private String relatedPatterns;
    private String springExample;
    private String jdkExample;
    private String realWorldExample;
    // 注意：不再包含 codeExample 和 mermaidDiagram
}
```

**字段说明：**
- 基本信息字段：id, name, nameEn
- 分类字段：category, difficulty
- 描述字段：definition, intent, useCases
- 结构字段：participants, collaboration
- 评估字段：prosCons, relatedPatterns
- 实例字段：springExample, jdkExample, realWorldExample

### 2. Repository 层

#### PatternRepository.java
- **职责**：管理设计模式的元数据
- **实现**：内存存储，初始化时加载 23 个模式的基本信息
- **不再包含**：大量静态的代码示例和类图数据

**主要方法：**
```java
public List<Pattern> findAll()
public List<Pattern> findByCategory(PatternCategory category)
public List<Pattern> findByDifficulty(PatternDifficulty difficulty)
public Optional<Pattern> findById(String id)
```

### 3. Service 层

#### 设计模式 Service
- **职责**：执行设计模式的演示逻辑
- **方法**：`executeExample()` - 返回演示输出
- **移除方法**：`getCodeExample()`, `getMermaidDiagram()`

**示例结构：**
```
service/pattern/
├── creational/
│   ├── SingletonService.java
│   ├── FactoryMethodService.java
│   └── ...
├── structural/
│   ├── AdapterService.java
│   └── ...
└── behavioral/
    ├── ObserverService.java
    └── ...
```

每个 Service 对应的示例代码存储在独立的子包中：
```
service/pattern/creational/singleton/
├── EagerSingleton.java
├── LazySingleton.java
└── ...
```

### 4. Controller 层

#### PatternController.java
```java
@Controller
public class PatternController {
    // GET / - 主页，显示所有模式
    // GET /pattern/{id} - 模式详情页
    // GET /category/{category} - 按分类筛选
    // GET /difficulty/{difficulty} - 按难度筛选
}
```

#### ExecutionController.java
```java
@RestController
@RequestMapping("/execute")
public class ExecutionController {
    // GET /execute/{patternId} - 执行模式演示
}
```

#### PatternContentController.java（新增）
```java
@RestController
@RequestMapping("/api/patterns")
public class PatternContentController {
    // GET /api/patterns/{patternId}/code - 获取示例代码
    // GET /api/patterns/{patternId}/diagram - 获取类图
}
```

### 5. Util 层

#### SourceCodeReader.java（新增）
- **职责**：从实际源代码文件读取示例代码和生成类图
- **优势**：
  - 代码与实现保持同步
  - 减少重复数据
  - 更容易维护

**主要方法：**
```java
public String readExampleCode(String patternId)
public String readMermaidDiagram(String patternId)
```

## 数据流

### 1. 模式详情页加载流程

```
用户访问 /pattern/{id}
    ↓
PatternController.findById(id)
    ↓
PatternRepository.findById(id)
    ↓
返回 Pattern 对象（包含元数据）
    ↓
渲染 pattern-detail.html
    ↓
页面加载完成
    ↓
JavaScript 调用 /api/patterns/{id}/code
    ↓
SourceCodeReader.readExampleCode(id)
    ↓
读取实际源代码文件
    ↓
返回代码内容并显示
```

### 2. 执行模式演示流程

```
用户点击"运行示例"
    ↓
JavaScript 调用 /execute/{id}
    ↓
ExecutionController.execute(id)
    ↓
对应的 Service.executeExample()
    ↓
执行实际代码并收集输出
    ↓
返回 JSON {"output": "..."}
    ↓
页面显示执行结果
```

## API 端点

### REST API

| 端点 | 方法 | 描述 | 返回值 |
|------|------|------|--------|
| `/api/patterns/{id}/code` | GET | 获取示例代码 | `{"code": "..."}` |
| `/api/patterns/{id}/diagram` | GET | 获取 Mermaid 类图 | `{"diagram": "..."}` |
| `/execute/{id}` | GET | 执行模式演示 | `{"output": "..."}` |

### 页面路由

| 端点 | 描述 |
|------|------|
| `/` | 主页，显示所有模式列表 |
| `/pattern/{id}` | 模式详情页 |
| `/category/{category}` | 按分类筛选 |
| `/difficulty/{difficulty}` | 按难度筛选 |

## 前端架构

### 动态加载

使用 JavaScript 动态加载代码和类图，而不是在服务器端渲染：

```javascript
// 加载示例代码
function loadCode() {
    fetch('/api/patterns/' + patternId + '/code')
        .then(r => r.json())
        .then(data => {
            document.getElementById('code-block').textContent = data.code;
        });
}

// 加载类图
function loadDiagram() {
    fetch('/api/patterns/' + patternId + '/diagram')
        .then(r => r.json())
        .then(data => {
            document.getElementById('mermaid-diagram').textContent = data.diagram;
            mermaid.init(undefined, diagramDiv);
        });
}
```

### 懒加载

代码和类图在标签页切换时才加载，减少初始页面加载时间：

```javascript
document.querySelectorAll('.tab').forEach(tab => {
    tab.addEventListener('click', () => {
        if (tab.dataset.tab === 'code' && !codeLoaded) {
            loadCode();
            codeLoaded = true;
        }
    });
});
```

## 优势总结

### 1. 代码与元数据分离
- 元数据只包含描述性信息
- 实际代码存储在专门的源文件中
- 避免"魔法字符串"和重复数据

### 2. 更好的可维护性
- 修改示例代码只需修改源文件
- Repository 更简洁易读
- Service 专注于演示逻辑

### 3. 更好的可测试性
- 测试不需要验证大量字符串
- 可以独立测试各个组件
- Mock 数据更简单

### 4. 更好的扩展性
- 添加新模式只需：
  1. 创建 Service 类
  2. 创建示例代码文件
  3. 在 Repository 添加元数据
  4. 在 ExecutionController 添加 case
- 无需处理大量字符串拼接

### 5. 性能优化
- 按需加载代码和类图
- 减少初始页面大小
- 更好的缓存策略

## 文件结构对比

### 重构前
```
PatternRepository.java (2445 行)
├── 大量静态的 mermaidDiagram 字符串
└── 大量静态的 codeExample 字符串

Service 类
├── executeExample()
├── getCodeExample() - 返回硬编码字符串
└── getMermaidDiagram() - 返回硬编码字符串
```

### 重构后
```
PatternRepository.java (499 行)
└── 只包含元数据

Service 类
└── executeExample() - 只关注演示逻辑

SourceCodeReader.java (新增)
├── readExampleCode() - 从源文件读取
└── readMermaidDiagram() - 动态生成

PatternContentController.java (新增)
├── /api/patterns/{id}/code
└── /api/patterns/{id}/diagram
```

代码量减少约 **80%**，同时提高了可维护性和可扩展性。

## 未来改进方向

1. **自动生成类图**：使用 JavaParser 或类似工具从源代码自动生成 UML 图
2. **代码高亮**：在前端添加代码语法高亮
3. **缓存机制**：对读取的源代码和类图进行缓存
4. **多语言支持**：支持不同的编程语言示例
5. **用户贡献**：允许用户提交自己的示例代码
