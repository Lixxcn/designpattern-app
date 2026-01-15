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
        // 初始化单例模式数据
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
