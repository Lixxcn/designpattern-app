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

        // 初始化工厂方法模式数据
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

        // 初始化抽象工厂模式数据
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
