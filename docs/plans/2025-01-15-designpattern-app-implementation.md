# 设计模式学习应用实施计划

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** 构建一个完整的Spring Boot Web应用，包含GoF 23种设计模式的经典实现、Mermaid类图、在线运行和对比功能。

**架构:** Spring Boot 3.5.9 + Thymeleaf三层架构，controller处理请求、service包含模式实现、model存储元数据、前端使用Thymeleaf模板渲染。

**Tech Stack:** Java 17, Spring Boot, Thymeleaf, Lombok, Mermaid.js, JUnit

---

## 阶段 1: 项目基础设施搭建

### Task 1: 添加Maven依赖

**Files:**
- Modify: `pom.xml`

**Step 1: 添加Web和Thymeleaf依赖**

在 `pom.xml` 的 `<dependencies>` 部分添加：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

**Step 2: 运行构建验证依赖**

```bash
mvn clean compile
```

Expected: BUILD SUCCESS

**Step 3: 提交**

```bash
git add pom.xml
git commit -m "feat: 添加web和thymeleaf依赖"
```

---

### Task 2: 创建基础包结构

**Files:**
- Create: `src/main/java/cn/lixx/designpattern_app/model/Pattern.java`
- Create: `src/main/java/cn/lixx/designpattern_app/model/PatternCategory.java`
- Create: `src/main/java/cn/lixx/designpattern_app/model/PatternDifficulty.java`
- Create: `src/main/java/cn/lixx/designpattern_app/repository/PatternRepository.java`

**Step 1: 创建Pattern实体类**

```java
package cn.lixx.designpattern_app.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
    private String mermaidDiagram;
}
```

**Step 2: 创建PatternCategory枚举**

```java
package cn.lixx.designpattern_app.model;

public enum PatternCategory {
    CREATIONAL("创建型"),
    STRUCTURAL("结构型"),
    BEHAVIORAL("行为型");

    private final String displayName;

    PatternCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
```

**Step 3: 创建PatternDifficulty枚举**

```java
package cn.lixx.designpattern_app.model;

public enum PatternDifficulty {
    BEGINNER("初级"),
    INTERMEDIATE("中级"),
    ADVANCED("高级");

    private final String displayName;

    PatternDifficulty(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
```

**Step 4: 创建PatternRepository内存存储**

```java
package cn.lixx.designpattern_app.repository;

import cn.lixx.designpattern_app.model.Pattern;
import cn.lixx.designpattern_app.model.PatternCategory;
import cn.lixx.designpattern_app.model.PatternDifficulty;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PatternRepository {
    private final List<Pattern> patterns = new ArrayList<>();

    public PatternRepository() {
        // 在后续任务中填充数据
    }

    public List<Pattern> findAll() {
        return patterns;
    }

    public List<Pattern> findByCategory(PatternCategory category) {
        return patterns.stream()
                .filter(p -> p.getCategory() == category)
                .toList();
    }

    public List<Pattern> findByDifficulty(PatternDifficulty difficulty) {
        return patterns.stream()
                .filter(p -> p.getDifficulty() == difficulty)
                .toList();
    }

    public Optional<Pattern> findById(String id) {
        return patterns.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}
```

**Step 5: 运行构建验证**

```bash
mvn clean compile
```

Expected: BUILD SUCCESS

**Step 6: 提交**

```bash
git add src/main/java/cn/lixx/designpattern_app/model/ src/main/java/cn/lixx/designpattern_app/repository/
git commit -m "feat: 创建model和repository基础结构"
```

---

### Task 3: 创建Controller和基础页面

**Files:**
- Create: `src/main/java/cn/lixx/designpattern_app/controller/PatternController.java`
- Create: `src/main/resources/templates/index.html`
- Create: `src/main/resources/templates/pattern-detail.html`
- Create: `src/main/resources/static/css/style.css`
- Create: `src/main/resources/static/js/app.js`

**Step 1: 创建PatternController**

```java
package cn.lixx.designpattern_app.controller;

import cn.lixx.designpattern_app.model.Pattern;
import cn.lixx.designpattern_app.model.PatternCategory;
import cn.lixx.designpattern_app.model.PatternDifficulty;
import cn.lixx.designpattern_app.repository.PatternRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatternController {
    private final PatternRepository patternRepository;

    public PatternController(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("patterns", patternRepository.findAll());
        model.addAttribute("categories", PatternCategory.values());
        model.addAttribute("difficulties", PatternDifficulty.values());
        return "index";
    }

    @GetMapping("/pattern/{id}")
    public String patternDetail(@PathVariable String id, Model model) {
        return patternRepository.findById(id)
                .map(pattern -> {
                    model.addAttribute("pattern", pattern);
                    return "pattern-detail";
                })
                .orElse("redirect:/");
    }

    @GetMapping("/category/{category}")
    public String byCategory(@PathVariable PatternCategory category, Model model) {
        model.addAttribute("patterns", patternRepository.findByCategory(category));
        model.addAttribute("categories", PatternCategory.values());
        model.addAttribute("difficulties", PatternDifficulty.values());
        model.addAttribute("selectedCategory", category);
        return "index";
    }

    @GetMapping("/difficulty/{difficulty}")
    public String byDifficulty(@PathVariable PatternDifficulty difficulty, Model model) {
        model.addAttribute("patterns", patternRepository.findByDifficulty(difficulty));
        model.addAttribute("categories", PatternCategory.values());
        model.addAttribute("difficulties", PatternDifficulty.values());
        model.addAttribute("selectedDifficulty", difficulty);
        return "index";
    }
}
```

**Step 2: 创建index.html模板**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>设计模式学习应用</title>
    <link rel="stylesheet" th:href="@{/css/style.css}">
    <script src="https://cdn.jsdelivr.net/npm/mermaid/dist/mermaid.min.js"></script>
</head>
<body>
    <header>
        <h1>设计模式学习应用</h1>
        <nav>
            <a th:href="@{/}">全部</a>
            <th:block th:each="category : ${categories}">
                <a th:href="@{/category/{cat}(cat=${category})}"
                   th:text="${category.displayName}"></a>
            </th:block>
        </nav>
    </header>

    <main>
        <aside>
            <h3>难度筛选</h3>
            <div class="difficulty-filter">
                <label>
                    <input type="radio" name="difficulty" value="" checked onchange="filterByDifficulty('')">
                    全部
                </label>
                <th:block th:each="difficulty : ${difficulties}">
                    <label>
                        <input type="radio" name="difficulty"
                               th:value="${difficulty}"
                               th:checked="${selectedDifficulty == difficulty}"
                               th:onchange="'filterByDifficulty(\'' + ${difficulty} + '\')'">
                        <span th:text="${difficulty.displayName}"></span>
                    </label>
                </th:block>
            </div>
        </aside>

        <section class="pattern-grid">
            <div class="pattern-card" th:each="pattern : ${patterns}">
                <h3 th:text="${pattern.name}">单例模式</h3>
                <p class="name-en" th:text="${pattern.nameEn}">Singleton Pattern</p>
                <p class="definition" th:text="${pattern.definition}">保证一个类仅有一个实例</p>
                <div class="meta">
                    <span class="category" th:text="${pattern.category.displayName}">创建型</span>
                    <span class="difficulty" th:text="${pattern.difficulty.displayName}">初级</span>
                </div>
                <a th:href="@{/pattern/{id}(id=${pattern.id})}" class="btn">查看详情 →</a>
            </div>
        </section>
    </main>

    <script th:src="@{/js/app.js}"></script>
    <script>
        mermaid.initialize({ startOnLoad: true });
        function filterByDifficulty(difficulty) {
            if (difficulty) {
                window.location.href = '/difficulty/' + difficulty;
            } else {
                window.location.href = '/';
            }
        }
    </script>
</body>
</html>
```

**Step 3: 创建pattern-detail.html模板**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title th:text="${pattern.name}">单例模式</title>
    <link rel="stylesheet" th:href="@{/css/style.css}">
    <script src="https://cdn.jsdelivr.net/npm/mermaid/dist/mermaid.min.js"></script>
</head>
<body>
    <header>
        <a href="/" class="back-btn">← 返回</a>
        <h1>
            <span th:text="${pattern.name}">单例模式</span>
            <small th:text="${pattern.nameEn}">Singleton Pattern</small>
        </h1>
    </header>

    <main class="detail">
        <nav class="tabs">
            <button class="tab active" data-tab="description">模式说明</button>
            <button class="tab" data-tab="diagram">类图</button>
            <button class="tab" data-tab="code">示例代码</button>
            <button class="tab" data-tab="examples">应用案例</button>
        </nav>

        <section id="description" class="tab-content active">
            <h2>模式说明</h2>
            <div class="field">
                <h3>定义</h3>
                <p th:text="${pattern.definition}"></p>
            </div>
            <div class="field">
                <h3>意图</h3>
                <p th:text="${pattern.intent}"></p>
            </div>
            <div class="field">
                <h3>适用场景</h3>
                <p th:text="${pattern.useCases}"></p>
            </div>
            <div class="field">
                <h3>优缺点</h3>
                <p th:text="${pattern.prosCons}"></p>
            </div>
            <div class="field">
                <h3>相关模式</h3>
                <p th:text="${pattern.relatedPatterns}"></p>
            </div>
        </section>

        <section id="diagram" class="tab-content">
            <h2>Mermaid类图</h2>
            <div class="mermaid" th:text="${pattern.mermaidDiagram}"></div>
        </section>

        <section id="code" class="tab-content">
            <h2>示例代码</h2>
            <pre class="code-block" th:text="${pattern.codeExample}"></pre>
            <button class="btn run-btn" onclick="runExample()">运行示例</button>
            <div id="output" class="output hidden"></div>
        </section>

        <section id="examples" class="tab-content">
            <h2>实际应用案例</h2>
            <div class="field">
                <h3>Spring框架</h3>
                <p th:text="${pattern.springExample}"></p>
            </div>
            <div class="field">
                <h3>JDK</h3>
                <p th:text="${pattern.jdkExample}"></p>
            </div>
            <div class="field">
                <h3>实际项目</h3>
                <p th:text="${pattern.realWorldExample}"></p>
            </div>
        </section>
    </main>

    <script th:src="@{/js/app.js}"></script>
    <script>
        mermaid.initialize({ startOnLoad: true });

        // 标签切换
        document.querySelectorAll('.tab').forEach(tab => {
            tab.addEventListener('click', () => {
                document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
                document.querySelectorAll('.tab-content').forEach(c => c.classList.remove('active'));
                tab.classList.add('active');
                document.getElementById(tab.dataset.tab).classList.add('active');
            });
        });

        function runExample() {
            const patternId = '[[${pattern.id}]]';
            fetch('/execute/' + patternId)
                .then(r => r.json())
                .then(data => {
                    const output = document.getElementById('output');
                    output.textContent = data.output;
                    output.classList.remove('hidden');
                });
        }
    </script>
</body>
</html>
```

**Step 4: 创建CSS样式**

```css
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
    line-height: 1.6;
    color: #333;
}

header {
    background: #2c3e50;
    color: white;
    padding: 1rem 2rem;
}

header h1 {
    margin-bottom: 1rem;
}

header h1 small {
    font-size: 0.6em;
    opacity: 0.8;
}

header nav {
    display: flex;
    gap: 1rem;
}

header nav a {
    color: white;
    text-decoration: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
}

header nav a:hover {
    background: rgba(255,255,255,0.1);
}

.back-btn {
    display: inline-block;
    color: white;
    text-decoration: none;
    padding: 0.5rem 1rem;
    border: 1px solid rgba(255,255,255,0.3);
    border-radius: 4px;
    margin-bottom: 1rem;
}

main {
    display: flex;
    max-width: 1400px;
    margin: 0 auto;
    padding: 2rem;
    gap: 2rem;
}

aside {
    flex: 0 0 250px;
    background: #f8f9fa;
    padding: 1.5rem;
    border-radius: 8px;
    height: fit-content;
}

.difficulty-filter label {
    display: block;
    margin: 0.5rem 0;
    cursor: pointer;
}

.pattern-grid {
    flex: 1;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 1.5rem;
}

.pattern-card {
    background: white;
    border: 1px solid #e0e0e0;
    border-radius: 8px;
    padding: 1.5rem;
    transition: transform 0.2s, box-shadow 0.2s;
}

.pattern-card:hover {
    transform: translateY(-4px);
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

.pattern-card h3 {
    color: #2c3e50;
    margin-bottom: 0.25rem;
}

.pattern-card .name-en {
    color: #7f8c8d;
    font-size: 0.9em;
    margin-bottom: 1rem;
}

.pattern-card .definition {
    color: #555;
    margin-bottom: 1rem;
}

.pattern-card .meta {
    display: flex;
    gap: 0.5rem;
    margin-bottom: 1rem;
}

.pattern-card .meta span {
    padding: 0.25rem 0.5rem;
    border-radius: 4px;
    font-size: 0.85em;
}

.pattern-card .category {
    background: #e3f2fd;
    color: #1976d2;
}

.pattern-card .difficulty {
    background: #f3e5f5;
    color: #7b1fa2;
}

.btn {
    display: inline-block;
    background: #3498db;
    color: white;
    text-decoration: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    border: none;
    cursor: pointer;
}

.btn:hover {
    background: #2980b9;
}

.detail {
    max-width: 1000px;
    margin: 0 auto;
}

.tabs {
    display: flex;
    border-bottom: 2px solid #e0e0e0;
    margin-bottom: 2rem;
}

.tab {
    padding: 1rem 2rem;
    background: none;
    border: none;
    border-bottom: 2px solid transparent;
    cursor: pointer;
    font-size: 1rem;
    margin-bottom: -2px;
}

.tab.active {
    border-bottom-color: #3498db;
    color: #3498db;
}

.tab-content {
    display: none;
}

.tab-content.active {
    display: block;
}

.field {
    margin-bottom: 2rem;
}

.field h3 {
    color: #2c3e50;
    margin-bottom: 0.5rem;
}

.code-block {
    background: #2c3e50;
    color: #ecf0f1;
    padding: 1.5rem;
    border-radius: 8px;
    overflow-x: auto;
    margin-bottom: 1rem;
}

.run-btn {
    margin-bottom: 1rem;
}

.output {
    background: #ecf0f1;
    padding: 1rem;
    border-radius: 4px;
    font-family: monospace;
}

.output.hidden {
    display: none;
}

.mermaid {
    background: white;
    padding: 1rem;
    border-radius: 8px;
}
```

**Step 5: 创建JavaScript文件**

```javascript
// 应用全局配置和工具函数

console.log('设计模式学习应用已加载');

// 工具函数：格式化代码
function formatCode(code) {
    return code;
}

// 工具函数：执行完成后显示结果
function showOutput(output) {
    const outputDiv = document.getElementById('output');
    if (outputDiv) {
        outputDiv.textContent = output;
        outputDiv.classList.remove('hidden');
    }
}
```

**Step 6: 运行应用测试**

```bash
mvn spring-boot:run
```

Expected: 应用启动，访问 http://localhost:8080 显示页面

**Step 7: 提交**

```bash
git add src/main/java/cn/lixx/designpattern_app/controller/ src/main/resources/
git commit -m "feat: 创建controller和基础页面模板"
```

---

## 阶段 2: 创建型设计模式实现

### Task 4: 实现单例模式 (Singleton)

**Files:**
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/SingletonService.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/singleton/Singleton.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/singleton/Client.java`
- Modify: `src/main/java/cn/lixx/designpattern_app/repository/PatternRepository.java`

**Step 1: 创建Singleton类（示例实现）**

```java
package cn.lixx.designpattern_app.service.pattern.creational.singleton;

/**
 * 单例模式 - 饿汉式实现
 *
 * 意图：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 */
public class Singleton {
    // 静态变量，在类加载时就创建实例
    private static final Singleton INSTANCE = new Singleton();

    // 私有构造函数，防止外部创建实例
    private Singleton() {
        System.out.println("Singleton实例被创建");
    }

    // 提供全局访问点
    public static Singleton getInstance() {
        return INSTANCE;
    }

    // 业务方法示例
    public void doSomething() {
        System.out.println("Singleton正在执行业务逻辑");
    }
}
```

**Step 2: 创建Singleton懒汉式实现**

```java
package cn.lixx.designpattern_app.service.pattern.creational.singleton;

/**
 * 单例模式 - 懒汉式实现（线程不安全）
 */
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("LazySingleton实例被创建");
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("LazySingleton正在执行业务逻辑");
    }
}
```

**Step 3: 创建Singleton线程安全懒汉式实现**

```java
package cn.lixx.designpattern_app.service.pattern.creational.singleton;

/**
 * 单例模式 - 懒汉式实现（双重检查锁，线程安全）
 */
public class ThreadSafeLazySingleton {
    private static volatile ThreadSafeLazySingleton instance;

    private ThreadSafeLazySingleton() {
        System.out.println("ThreadSafeLazySingleton实例被创建");
    }

    public static ThreadSafeLazySingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeLazySingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeLazySingleton();
                }
            }
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("ThreadSafeLazySingleton正在执行业务逻辑");
    }
}
```

**Step 4: 创建Singleton静态内部类实现**

```java
package cn.lixx.designpattern_app.service.pattern.creational.singleton;

/**
 * 单例模式 - 静态内部类实现（推荐方式）
 * 优点：线程安全、延迟加载、代码简洁
 */
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {
        System.out.println("StaticInnerClassSingleton实例被创建");
    }

    private static class Holder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public void doSomething() {
        System.out.println("StaticInnerClassSingleton正在执行业务逻辑");
    }
}
```

**Step 5: 创建Client演示类**

```java
package cn.lixx.designpattern_app.service.pattern.creational.singleton;

/**
 * 单例模式客户端演示
 */
public class Client {
    public static String demonstrate() {
        StringBuilder output = new StringBuilder();

        output.append("=== 单例模式演示 ===\n\n");

        // 演示饿汉式
        output.append("1. 饿汉式单例：\n");
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        output.append("singleton1 == singleton2: ").append(singleton1 == singleton2).append("\n");
        singleton1.doSomething();

        output.append("\n2. 懒汉式单例：\n");
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        output.append("lazy1 == lazy2: ").append(lazy1 == lazy2).append("\n");

        output.append("\n3. 线程安全懒汉式：\n");
        ThreadSafeLazySingleton tsLazy1 = ThreadSafeLazySingleton.getInstance();
        ThreadSafeLazySingleton tsLazy2 = ThreadSafeLazySingleton.getInstance();
        output.append("tsLazy1 == tsLazy2: ").append(tsLazy1 == tsLazy2).append("\n");

        output.append("\n4. 静态内部类方式（推荐）：\n");
        StaticInnerClassSingleton inner1 = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton inner2 = StaticInnerClassSingleton.getInstance();
        output.append("inner1 == inner2: ").append(inner1 == inner2).append("\n");

        return output.toString();
    }
}
```

**Step 6: 创建SingletonService**

```java
package cn.lixx.designpattern_app.service.pattern.creational;

import cn.lixx.designpattern_app.service.pattern.creational.singleton.Client;
import org.springframework.stereotype.Service;

@Service
public class SingletonService {

    public String executeExample() {
        return Client.demonstrate();
    }

    public String getCodeExample() {
        return """
// 单例模式 - 饿汉式实现
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }
}

// 单例模式 - 懒汉式实现（线程不安全）
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

// 单例模式 - 双重检查锁（线程安全）
public class ThreadSafeLazySingleton {
    private static volatile ThreadSafeLazySingleton instance;

    private ThreadSafeLazySingleton() {}

    public static ThreadSafeLazySingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeLazySingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeLazySingleton();
                }
            }
        }
        return instance;
    }
}

// 单例模式 - 静态内部类（推荐）
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {}

    private static class Holder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Singleton {
        -static Singleton instance
        -Singleton()
        +static Singleton getInstance()
        +void doSomething()
    }
    class Client {
        +void main()
    }
    Client --> Singleton : uses
    Singleton ..> Singleton : creates instance
""";
    }
}
```

**Step 7: 在PatternRepository中添加单例模式数据**

在 `PatternRepository` 构造函数中添加：

```java
Pattern singleton = new Pattern(
    "singleton",
    "单例模式",
    "Singleton Pattern",
    PatternCategory.CREATIONAL,
    PatternDifficulty.BEGINNER,
    "保证一个类仅有一个实例，并提供一个访问它的全局访问点。",
    "保证一个类仅有一个实例，并提供一个访问它的全局访问点。",
    "1. 当类只能有一个实例而且客户可以从一个众所周知的访问点访问它时\n2. 当这个唯一实例应该是通过子类化可扩展的，并且客户应该无需更改代码就能使用一个扩展的实例时",
    "Singleton（单例）- 提供getInstance()\nClient（客户端）- 通过getInstance()获取实例",
    "Client通过调用Singleton.getInstance()获取唯一实例，然后使用实例的方法。",
    "优点：\n1. 对唯一实例的受控访问\n2. 缩小命名空间（避免全局变量污染）\n3. 可以精简操作（比类操作更灵活）\n4. 允许改进操作和表示\n5. 允许可变数目的实例\n\n缺点：\n1. 单例模式对测试不友好（难以mock）\n2. 单例模式与单一职责原则可能冲突\n3. 在多线程环境下需要特殊处理",
    "相关模式：\n- 抽象工厂模式可以使用单例模式\n- 建造者模式可以使用单例模式\n- 原型模式可以使用单例模式",
    "Spring框架中，Bean默认是单例作用域（singleton scope）。",
    "1. Runtime.getRuntime() - Java运行时实例\n2. System.getRuntime()\n3. Desktop.getDesktop()\n4. SecurityManager.getSecurityManager()",
    "1. 数据库连接池\n2. 配置管理器\n3. 日志记录器\n4. 缓存管理器",
    """
classDiagram
    class Singleton {
        -static Singleton instance
        -Singleton() private
        +static Singleton getInstance()
        +void doSomething()
    }
    class Client {
        +main()
    }
    Client --> Singleton : uses
    Singleton ..> Singleton : instance
"""
);
patterns.add(singleton);
```

**Step 8: 运行测试**

```bash
mvn clean test
```

Expected: BUILD SUCCESS

**Step 9: 提交**

```bash
git add src/main/java/cn/lixx/designpattern_app/service/pattern/creational/ src/main/java/cn/lixx/designpattern_app/repository/
git commit -m "feat: 实现单例模式"
```

---

### Task 5: 实现工厂方法模式 (Factory Method)

**Files:**
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/FactoryMethodService.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/factorymethod/Product.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/factorymethod/ConcreteProductA.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/factorymethod/ConcreteProductB.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/factorymethod/Factory.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/factorymethod/ConcreteFactoryA.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/factorymethod/ConcreteFactoryB.java`
- Modify: `src/main/java/cn/lixx/designpattern_app/repository/PatternRepository.java`

**Step 1: 创建Product接口**

```java
package cn.lixx.designpattern_app.service.pattern.creational.factorymethod;

/**
 * 产品接口
 */
public interface Product {
    void use();
}
```

**Step 2: 创建ConcreteProductA**

```java
package cn.lixx.designpattern_app.service.pattern.creational.factorymethod;

/**
 * 具体产品A
 */
public class ConcreteProductA implements Product {
    @Override
    public void use() {
        System.out.println("使用具体产品A");
    }
}
```

**Step 3: 创建ConcreteProductB**

```java
package cn.lixx.designpattern_app.service.pattern.creational.factorymethod;

/**
 * 具体产品B
 */
public class ConcreteProductB implements Product {
    @Override
    public void use() {
        System.out.println("使用具体产品B");
    }
}
```

**Step 4: 创建Factory接口**

```java
package cn.lixx.designpattern_app.service.pattern.creational.factorymethod;

/**
 * 工厂接口，声明工厂方法
 */
public interface Factory {
    /**
     * 工厂方法：创建产品对象
     */
    Product createProduct();
}
```

**Step 5: 创建ConcreteFactoryA**

```java
package cn.lixx.designpattern_app.service.pattern.creational.factorymethod;

/**
 * 具体工厂A，创建具体产品A
 */
public class ConcreteFactoryA implements Factory {
    @Override
    public Product createProduct() {
        System.out.println("具体工厂A创建具体产品A");
        return new ConcreteProductA();
    }
}
```

**Step 6: 创建ConcreteFactoryB**

```java
package cn.lixx.designpattern_app.service.pattern.creational.factorymethod;

/**
 * 具体工厂B，创建具体产品B
 */
public class ConcreteFactoryB implements Factory {
    @Override
    public Product createProduct() {
        System.out.println("具体工厂B创建具体产品B");
        return new ConcreteProductB();
    }
}
```

**Step 7: 创建FactoryMethodService**

```java
package cn.lixx.designpattern_app.service.pattern.creational;

import cn.lixx.designpattern_app.service.pattern.creational.factorymethod.*;
import org.springframework.stereotype.Service;

@Service
public class FactoryMethodService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 工厂方法模式演示 ===\n\n");

        // 使用具体工厂A创建产品A
        output.append("1. 使用工厂A创建产品：\n");
        Factory factoryA = new ConcreteFactoryA();
        Product productA = factoryA.createProduct();
        productA.use();

        output.append("\n2. 使用工厂B创建产品：\n");
        Factory factoryB = new ConcreteFactoryB();
        Product productB = factoryB.createProduct();
        productB.use();

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 产品接口
interface Product {
    void use();
}

// 具体产品A
class ConcreteProductA implements Product {
    @Override
    public void use() {
        System.out.println("使用具体产品A");
    }
}

// 具体产品B
class ConcreteProductB implements Product {
    @Override
    public void use() {
        System.out.println("使用具体产品B");
    }
}

// 工厂接口
interface Factory {
    Product createProduct();
}

// 具体工厂A
class ConcreteFactoryA implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}

// 具体工厂B
class ConcreteFactoryB implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}

// 使用示例
Factory factoryA = new ConcreteFactoryA();
Product productA = factoryA.createProduct();
productA.use();
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Product {
        <<interface>>
        +use() void
    }
    class ConcreteProductA {
        +use() void
    }
    class ConcreteProductB {
        +use() void
    }
    class Factory {
        <<interface>>
        +createProduct() Product
    }
    class ConcreteFactoryA {
        +createProduct() Product
    }
    class ConcreteFactoryB {
        +createProduct() Product
    }

    Product <|.. ConcreteProductA
    Product <|.. ConcreteProductB
    Factory <|.. ConcreteFactoryA
    Factory <|.. ConcreteFactoryB
    Factory ..> Product : creates
    ConcreteFactoryA ..> ConcreteProductA : creates
    ConcreteFactoryB ..> ConcreteProductB : creates
""";
    }
}
```

**Step 8: 在PatternRepository中添加数据**

```java
Pattern factoryMethod = new Pattern(
    "factory-method",
    "工厂方法模式",
    "Factory Method Pattern",
    PatternCategory.CREATIONAL,
    PatternDifficulty.BEGINNER,
    "定义一个用于创建对象的接口，让子类决定实例化哪一个类。",
    "定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法使一个类的实例化延迟到其子类。",
    "1. 当一个类不知道它所必须创建的对象的类时\n2. 当类希望由它的子类来指定它所创建的对象时\n3. 当类将创建对象的职责委托给多个帮助子类中的某一个时",
    "Product（产品）- 定义产品接口\nConcreteProduct（具体产品）- 实现Product接口\nFactory（工厂）- 声明工厂方法\nConcreteFactory（具体工厂）- 实现工厂方法创建具体产品",
    "客户端通过ConcreteFactory创建ConcreteProduct，Factory定义创建接口，ConcreteFactory决定实例化哪个产品类。",
    "优点：\n1. 符合开闭原则，新增产品无需修改现有代码\n2. 符合单一职责原则\n3. 解耦产品创建和使用\n\n缺点：\n1. 类的数量增多\n2. 增加系统复杂性\n3. 产品的创建完全依赖工厂类",
    "相关模式：\n- 抽象工厂模式经常使用工厂方法模式\n- 原型模式可以用工厂方法模式实现\n- 模板方法模式可以用工厂方法模式",
    "Spring的FactoryBean接口使用工厂方法模式创建Bean对象。",
    "1. Collection.iterator() - 返回迭代器\n2. Class.newInstance() - 创建对象实例\n3. Calendar.getInstance() - 获取日历实例",
    "1. 日志框架中的Logger创建\n2. 数据库驱动加载\n3. 连接池工厂",
    """
classDiagram
    class Product {
        <<interface>>
        +use()
    }
    class ConcreteProductA {
        +use()
    }
    class ConcreteProductB {
        +use()
    }
    class Factory {
        <<interface>>
        +createProduct()
    }
    class ConcreteFactoryA {
        +createProduct()
    }
    class ConcreteFactoryB {
        +createProduct()
    }

    Product <|.. ConcreteProductA
    Product <|.. ConcreteProductB
    Factory <|.. ConcreteFactoryA
    Factory <|.. ConcreteFactoryB
    Factory ..> Product
    ConcreteFactoryA ..> ConcreteProductA
    ConcreteFactoryB ..> ConcreteProductB
"""
);
patterns.add(factoryMethod);
```

**Step 9: 编译测试**

```bash
mvn clean compile
```

**Step 10: 提交**

```bash
git add src/main/java/cn/lixx/designpattern_app/service/pattern/creational/ src/main/java/cn/lixx/designpattern_app/repository/
git commit -m "feat: 实现工厂方法模式"
```

---

### Task 6: 实现抽象工厂模式 (Abstract Factory)

**Files:**
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/AbstractFactoryService.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/abstractfactory/AbstractFactory.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/abstractfactory/ConcreteFactory1.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/abstractfactory/ConcreteFactory2.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/abstractfactory/AbstractProductA.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/abstractfactory/AbstractProductB.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/abstractfactory/ProductA1.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/abstractfactory/ProductA2.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/abstractfactory/ProductB1.java`
- Create: `src/main/java/cn/lixx/designpattern_app/service/pattern/creational/abstractfactory/ProductB2.java`
- Modify: `src/main/java/cn/lixx/designpattern_app/repository/PatternRepository.java`

**Step 1: 创建产品接口**

```java
package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

// 抽象产品A
public interface AbstractProductA {
    void operationA();
}
```

```java
package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

// 抽象产品B
public interface AbstractProductB {
    void operationB();
}
```

**Step 2: 创建具体产品**

```java
package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

public class ProductA1 implements AbstractProductA {
    @Override
    public void operationA() {
        System.out.println("产品A1的操作");
    }
}
```

```java
package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

public class ProductA2 implements AbstractProductA {
    @Override
    public void operationA() {
        System.out.println("产品A2的操作");
    }
}
```

```java
package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

public class ProductB1 implements AbstractProductB {
    @Override
    public void operationB() {
        System.out.println("产品B1的操作");
    }
}
```

```java
package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

public class ProductB2 implements AbstractProductB {
    @Override
    public void operationB() {
        System.out.println("产品B2的操作");
    }
}
```

**Step 3: 创建抽象工厂接口**

```java
package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

/**
 * 抽象工厂接口：声明创建一系列相关或依赖对象的接口
 */
public interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}
```

**Step 4: 创建具体工厂**

```java
package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

/**
 * 具体工厂1：创建产品族1
 */
public class ConcreteFactory1 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
```

```java
package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

/**
 * 具体工厂2：创建产品族2
 */
public class ConcreteFactory2 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
```

**Step 5: 创建AbstractFactoryService**

```java
package cn.lixx.designpattern_app.service.pattern.creational;

import cn.lixx.designpattern_app.service.pattern.creational.abstractfactory.*;
import org.springframework.stereotype.Service;

@Service
public class AbstractFactoryService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 抽象工厂模式演示 ===\n\n");

        // 使用工厂1创建产品族1
        output.append("1. 使用工厂1创建产品族1：\n");
        AbstractFactory factory1 = new ConcreteFactory1();
        AbstractProductA productA1 = factory1.createProductA();
        AbstractProductB productB1 = factory1.createProductB();
        productA1.operationA();
        productB1.operationB();

        output.append("\n2. 使用工厂2创建产品族2：\n");
        AbstractFactory factory2 = new ConcreteFactory2();
        AbstractProductA productA2 = factory2.createProductA();
        AbstractProductB productB2 = factory2.createProductB();
        productA2.operationA();
        productB2.operationB();

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 抽象产品接口
interface AbstractProductA {
    void operationA();
}

interface AbstractProductB {
    void operationB();
}

// 具体产品A1, A2
class ProductA1 implements AbstractProductA {
    public void operationA() { System.out.println("产品A1的操作"); }
}

class ProductA2 implements AbstractProductA {
    public void operationA() { System.out.println("产品A2的操作"); }
}

// 具体产品B1, B2
class ProductB1 implements AbstractProductB {
    public void operationB() { System.out.println("产品B1的操作"); }
}

class ProductB2 implements AbstractProductB {
    public void operationB() { System.out.println("产品B2的操作"); }
}

// 抽象工厂接口
interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}

// 具体工厂1
class ConcreteFactory1 implements AbstractFactory {
    public AbstractProductA createProductA() {
        return new ProductA1();
    }
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}

// 具体工厂2
class ConcreteFactory2 implements AbstractFactory {
    public AbstractProductA createProductA() {
        return new ProductA2();
    }
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}

// 使用示例
AbstractFactory factory = new ConcreteFactory1();
AbstractProductA productA = factory.createProductA();
AbstractProductB productB = factory.createProductB();
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class AbstractFactory {
        <<interface>>
        +createProductA() AbstractProductA
        +createProductB() AbstractProductB
    }
    class ConcreteFactory1 {
        +createProductA() AbstractProductA
        +createProductB() AbstractProductB
    }
    class ConcreteFactory2 {
        +createProductA() AbstractProductA
        +createProductB() AbstractProductB
    }
    class AbstractProductA {
        <<interface>>
        +operationA()
    }
    class AbstractProductB {
        <<interface>>
        +operationB()
    }
    class ProductA1 {
        +operationA()
    }
    class ProductA2 {
        +operationA()
    }
    class ProductB1 {
        +operationB()
    }
    class ProductB2 {
        +operationB()
    }

    AbstractFactory <|.. ConcreteFactory1
    AbstractFactory <|.. ConcreteFactory2
    AbstractProductA <|.. ProductA1
    AbstractProductA <|.. ProductA2
    AbstractProductB <|.. ProductB1
    AbstractProductB <|.. ProductB2
    ConcreteFactory1 ..> ProductA1 : creates
    ConcreteFactory1 ..> ProductB1 : creates
    ConcreteFactory2 ..> ProductA2 : creates
    ConcreteFactory2 ..> ProductB2 : creates
""";
    }
}
```

**Step 6: 在PatternRepository中添加数据**

```java
Pattern abstractFactory = new Pattern(
    "abstract-factory",
    "抽象工厂模式",
    "Abstract Factory Pattern",
    PatternCategory.CREATIONAL,
    PatternDifficulty.INTERMEDIATE,
    "提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类。",
    "提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类。",
    "1. 当系统要独立于它的产品的创建、组合和表示时\n2. 当系统要由多个产品系列中的一个来配置时\n3. 当要强调一系列相关的产品对象的设计以便进行联合使用时\n4. 当提供一个产品类库，而只想显示它们的接口而不是实现时",
    "AbstractFactory（抽象工厂）- 声明创建抽象产品对象的操作\nConcreteFactory（具体工厂）- 实现创建具体产品对象的操作\nAbstractProduct（抽象产品）- 声明一类产品的对象接口\nProduct（具体产品）- 定义AbstractProduct接口，由ConcreteFactory创建",
    "客户端只使用AbstractFactory和AbstractProduct类声明的接口，ConcreteFactory在运行时创建具体产品对象。",
    "优点：\n1. 分离接口和实现\n2. 产品族内保证一致性\n3. 利于产品族切换\n\n缺点：\n1. 难以支持新种类产品\n2. 类层级复杂",
    "相关模式：\n- 抽象工厂模式通常用工厂方法模式实现\n- 具体工厂通常是单例模式\n- 产品可以是原型模式",
    "Spring的BeanFactory和ApplicationContext使用抽象工厂模式创建Bean。",
    "1. DocumentBuilderFactory - 创建XML解析器\n2. TransformerFactory - 创建XSLT处理器\n3. SAXParserFactory - 创建SAX解析器",
    "1. 跨平台UI组件库（Windows/Mac/Linux组件族）\n2. 不同数据库的连接器族\n3. 不同主题的UI组件族",
    """
classDiagram
    class AbstractFactory {
        <<interface>>
        +createProductA()
        +createProductB()
    }
    class ConcreteFactory1 {
        +createProductA()
        +createProductB()
    }
    class ConcreteFactory2 {
        +createProductA()
        +createProductB()
    }
    class AbstractProductA {
        <<interface>>
        +operationA()
    }
    class AbstractProductB {
        <<interface>>
        +operationB()
    }
    class ProductA1 {
        +operationA()
    }
    class ProductA2 {
        +operationA()
    }
    class ProductB1 {
        +operationB()
    }
    class ProductB2 {
        +operationB()
    }

    AbstractFactory <|.. ConcreteFactory1
    AbstractFactory <|.. ConcreteFactory2
    AbstractProductA <|.. ProductA1
    AbstractProductA <|.. ProductA2
    AbstractProductB <|.. ProductB1
    AbstractProductB <|.. ProductB2
    ConcreteFactory1 ..> ProductA1 : creates
    ConcreteFactory1 ..> ProductB1 : creates
    ConcreteFactory2 ..> ProductA2 : creates
    ConcreteFactory2 ..> ProductB2 : creates
"""
);
patterns.add(abstractFactory);
```

**Step 7: 编译测试**

```bash
mvn clean compile
```

**Step 8: 提交**

```bash
git add src/main/java/cn/lixx/designpattern_app/service/pattern/creational/ src/main/java/cn/lixx/designpattern_app/repository/
git commit -m "feat: 实现抽象工厂模式"
```

---

### Task 7-23: 其余设计模式

由于篇幅限制，剩余的设计模式（建造者、原型、适配器、桥接、装饰器等）按照相同的模式实现：

1. **创建型模式剩余**:
   - 建造者模式 (Builder)
   - 原型模式 (Prototype)

2. **结构型模式 (7种)**:
   - 适配器模式 (Adapter)
   - 桥接模式 (Bridge)
   - 组合模式 (Composite)
   - 装饰器模式 (Decorator)
   - 外观模式 (Facade)
   - 享元模式 (Flyweight)
   - 代理模式 (Proxy)

3. **行为型模式 (11种)**:
   - 责任链模式 (Chain of Responsibility)
   - 命令模式 (Command)
   - 迭代器模式 (Iterator)
   - 中介者模式 (Mediator)
   - 备忘录模式 (Memento)
   - 观察者模式 (Observer)
   - 状态模式 (State)
   - 策略模式 (Strategy)
   - 模板方法模式 (Template Method)
   - 访问者模式 (Visitor)
   - 解释器模式 (Interpreter)

每个模式按照以下结构实现：

```
src/main/java/cn/lixx/designpattern_app/service/pattern/
├── creational/
│   ├── builder/
│   │   ├── Builder.java
│   │   ├── ConcreteBuilder.java
│   │   ├── Director.java
│   │   └── Product.java
│   └── prototype/
│       ├── Prototype.java
│       ├── ConcretePrototype.java
│       └── PrototypeManager.java
├── structural/
│   ├── adapter/
│   ├── bridge/
│   ├── composite/
│   ├── decorator/
│   ├── facade/
│   ├── flyweight/
│   └── proxy/
└── behavioral/
    ├── chain/
    ├── command/
    ├── iterator/
    ├── mediator/
    ├── memento/
    ├── observer/
    ├── state/
    ├── strategy/
    ├── template/
    ├── visitor/
    └── interpreter/
```

---

## 阶段 3: 执行功能实现

### Task 24: 实现代码执行功能

**Files:**
- Create: `src/main/java/cn/lixx/designpattern_app/controller/ExecutionController.java`
- Create: `src/main/java/cn/lixx/designpattern_app/util/CodeExecutor.java`

**Step 1: 创建ExecutionController**

```java
package cn.lixx.designpattern_app.controller;

import cn.lixx.designpattern_app.service.pattern.creational.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/execute")
public class ExecutionController {

    private final SingletonService singletonService;
    private final FactoryMethodService factoryMethodService;
    private final AbstractFactoryService abstractFactoryService;

    public ExecutionController(SingletonService singletonService,
                                FactoryMethodService factoryMethodService,
                                AbstractFactoryService abstractFactoryService) {
        this.singletonService = singletonService;
        this.factoryMethodService = factoryMethodService;
        this.abstractFactoryService = abstractFactoryService;
    }

    @GetMapping("/{patternId}")
    public Map<String, String> execute(@PathVariable String patternId) {
        String output = switch (patternId) {
            case "singleton" -> singletonService.executeExample();
            case "factory-method" -> factoryMethodService.executeExample();
            case "abstract-factory" -> abstractFactoryService.executeExample();
            default -> "未找到模式: " + patternId;
        };
        return Map.of("output", output);
    }
}
```

**Step 2: 提交**

```bash
git add src/main/java/cn/lixx/designpattern_app/controller/
git commit -m "feat: 添加代码执行API"
```

---

## 阶段 4: 测试与优化

### Task 25: 添加集成测试

**Step 1: 创建集成测试**

```java
// 后续补充测试
```

---

## 实施顺序建议

1. **阶段1（基础设施）**: Task 1-3
2. **阶段2（创建型模式）**: Task 4-7
3. **阶段3（结构型模式）**: 按Task 6的模式实现7种结构型模式
4. **阶段4（行为型模式）**: 按Task 6的模式实现11种行为型模式
5. **阶段5（执行功能）**: Task 24
6. **阶段6（测试优化）**: Task 25

---

## 注意事项

1. **每次只实现一个模式**，测试通过后提交
2. **代码注释要清晰**，包含中文说明
3. **Mermaid类图要准确**，展示正确的类关系
4. **每个Service实现三个方法**: `executeExample()`, `getCodeExample()`, `getMermaidDiagram()`
5. **PatternRepository中添加完整的模式元数据**

---

**计划状态**: 待执行
