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

        // 初始化建造者模式数据
        Pattern builder = new Pattern(
            "builder",
            "建造者模式",
            "Builder Pattern",
            PatternCategory.CREATIONAL,
            PatternDifficulty.INTERMEDIATE,
            "将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。",
            "将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。",
            "1. 当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时\n2. 当构造过程必须允许被构造的对象有不同的表示时",
            "Builder（建造者）- 为创建产品对象的各个部件指定抽象接口\nConcreteBuilder（具体建造者）- 实现Builder接口以构造和装配各个部件\nDirector（指挥者）- 构建一个使用Builder接口的对象\nProduct（产品）- 被构造的复杂对象",
            "Director创建ConcreteBuilder对象，调用Builder的构建方法，最后返回Product。",
            "优点：\n1. 分步创建对象，流程清晰\n2. 可以控制对象创建细节\n3. 相同构建流程可创建不同表示\n\n缺点：\n1. 产品必须有共同点\n2. 产品内部结构复杂会增加Builder类",
            "相关模式：\n- 抽象工厂模式与建造者模式相似\n- 组合模式通常用建造者模式构建",
            "Spring的StringBuilder、UriComponentsBuilder使用建造者模式。",
            "1. StringBuilder - 构建字符串\n2. DocumentBuilder - 构建XML文档\n3. Locale.Builder - 构建Locale对象",
            "1. SQL查询构建器\n2. HTTP请求构建器\n3. 配置对象构建器",
            """
classDiagram
    class Product {
        -String cpu
        -String ram
        -String storage
        +Product()
        +getCpu() String
        +getRam() String
        +getStorage() String
    }
    class Product$Builder {
        -String cpu
        -String ram
        -String storage
        +cpu(String) Builder
        +ram(String) Builder
        +storage(String) Builder
        +build() Product
    }
    class Director {
        -Builder builder
        +buildHighPerformance() Product
        +buildOffice() Product
        +buildGaming() Product
    }
    class Client {
        +main()
    }

    Product --> Product$Builder : creates
    Director --> Product$Builder : uses
    Client --> Product$Builder : uses
    Client --> Director : uses
    Product$Builder ..> Product : builds
"""
        );
        patterns.add(builder);

        // 初始化原型模式数据
        Pattern prototype = new Pattern(
            "prototype",
            "原型模式",
            "Prototype Pattern",
            PatternCategory.CREATIONAL,
            PatternDifficulty.BEGINNER,
            "用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。",
            "用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。",
            "1. 当一个系统应该独立于它的产品创建、构成和表示时\n2. 当要实例化的类是在运行时刻指定时\n3. 为了避免创建一个与产品类层次平行的工厂类层次时\n4. 当一个类的实例只能有几个不同状态组合中的一种时",
            "Prototype（原型）- 声明克隆自己的接口\nConcretePrototype（具体原型）- 实现克隆方法\nClient（客户端）- 通过原型克隆创建新对象",
            "Client通过调用Prototype的clone()方法创建新对象，无需知道具体创建细节。",
            "优点：\n1. 性能优良，直接拷贝内存\n2. 逃避构造函数约束\n3. 简化对象创建\n\n缺点：\n1. 配合克隆方法需要注意\n2. 深拷贝与浅拷贝问题",
            "相关模式：\n- 抽象工厂模式可以用原型模式存储和克隆产品\n- 组合模式可以用原型模式克隆复杂结构",
            "Spring的Bean作用域prototype（原型）每次获取都创建新实例。",
            "1. Object.clone() - 克隆对象\n2. Cloneable接口 - 标记可克隆\n3. ArrayList.clone() - 克隆列表",
            "1. 文档模板克隆\n2. 图形对象复制\n3. 数据库记录克隆",
            """
classDiagram
    class Prototype {
        <<interface>>
        +clone() Prototype
    }
    class ConcretePrototype {
        -String title
        -String content
        -String author
        +clone() Prototype
        +getTitle() String
        +setTitle(String)
        +getContent() String
        +setContent(String)
    }
    class PrototypeManager {
        -Map~String,Prototype~ prototypes
        +registerPrototype(String, Prototype)
        +create(String) Prototype
    }
    class Client {
        +main()
    }

    Prototype <|.. ConcretePrototype
    Client --> Prototype : uses
    Client --> PrototypeManager : uses
    ConcretePrototype ..> ConcretePrototype : clones
"""
        );
        patterns.add(prototype);

        // 初始化适配器模式数据
        Pattern adapter = new Pattern(
            "adapter",
            "适配器模式",
            "Adapter Pattern",
            PatternCategory.STRUCTURAL,
            PatternDifficulty.BEGINNER,
            "将一个类的接口转换成客户希望的另一个接口，使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。",
            "将一个类的接口转换成客户希望的另一个接口，使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。",
            "1. 想使用一个已经存在的类，而它的接口不符合你的需求\n2. 想创建一个可以复用的类，该类可以与其他不相关的类或不可预见的类协同工作\n3. 想使用几个现有的子类，但通过对每个子类进行子类化来调整它们的接口是不现实的",
            "Target（目标）- 定义Client使用的与特定领域相关的接口\nAdapter（适配器）- 将Adaptee接口转换成Target接口\nAdaptee（被适配者）- 需要适配的现有接口\nClient（客户端）- 符合Target接口的对象协同工作",
            "Adapter通过包装Adaptee对象，将Adaptee的接口转换成Target接口。",
            "优点：\n1. 提高类的复用性\n2. 增加类的透明性\n3. 灵活性好\n\n缺点：\n1. 过多使用适配器会让系统非常零乱\n2. 仅限对象适配",
            "相关模式：\n- 桥接模式与适配器模式相似\n- 装饰器模式与适配器模式相似",
            "Spring的HandlerAdapter、MethodBeforeAdviceAdapter使用适配器模式。",
            "1. Arrays.asList() - 数组转列表\n2. InputStreamReader - 字节流转字符流\n3. JDBC驱动适配器",
            "1. 日志框架适配器\n2. 支付接口适配\n3. 第三方API集成",
            """
classDiagram
    class Target {
        <<interface>>
        +request() void
    }
    class Adapter {
        -Adaptee adaptee
        +Adapter(Adaptee)
        +request() void
    }
    class Adaptee {
        +specificRequest() void
    }
    class Client {
        +main()
    }

    Target <|.. Adapter
    Adapter --> Adaptee : wraps
    Client --> Target : uses
"""
        );
        patterns.add(adapter);

        // 初始化装饰器模式数据
        Pattern decorator = new Pattern(
            "decorator",
            "装饰器模式",
            "Decorator Pattern",
            PatternCategory.STRUCTURAL,
            PatternDifficulty.INTERMEDIATE,
            "动态地给一个对象添加一些额外的职责，就增加功能来说，装饰器模式比生成子类更为灵活。",
            "动态地给一个对象添加一些额外的职责，就增加功能来说，装饰器模式比生成子类更为灵活。",
            "1. 在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责\n2. 当不能采用继承的方式对系统进行扩展时",
            "Component（组件）- 定义对象接口\nConcreteComponent（具体组件）- 定义具体对象\nDecorator（装饰器）- 维持Component引用\nConcreteDecorator（具体装饰器）- 具体装饰功能",
            "Decorator继承Component并包含Component引用，ConcreteDecorator添加具体功能。",
            "优点：\n1. 比继承更灵活\n2. 避免类层次爆炸\n3. 动态添加职责\n\n缺点：\n1. 产生更多小对象\n2. 装饰层次复杂",
            "相关模式：\n- 适配器模式改变对象接口\n- 组合模式与装饰器模式相似\n- 装饰器模式不同于策略模式",
            "Spring的HttpRequestDecorator、BufferedReader使用装饰器模式。",
            "1. java.io包中的InputStream/OutputStream\n2. Collections.unmodifiableList()\n3. Collections.synchronizedList()",
            "1. UI组件装饰\n2. 缓存装饰器\n3. 压缩/解压流",
            """
classDiagram
    class Component {
        <<interface>>
        +operation() void
    }
    class ConcreteComponent {
        +operation() void
    }
    class Decorator {
        <<abstract>>
        #Component component
        +Decorator(Component)
        +operation() void
    }
    class MilkDecorator {
        +MilkDecorator(Component)
        +operation() void
    }
    class SugarDecorator {
        +SugarDecorator(Component)
        +operation() void
    }

    Component <|.. ConcreteComponent
    Component <|.. Decorator
    Decorator <|-- MilkDecorator
    Decorator <|-- SugarDecorator
    Decorator o-- Component : decorates
"""
        );
        patterns.add(decorator);

        // 初始化代理模式数据
        Pattern proxy = new Pattern(
            "proxy",
            "代理模式",
            "Proxy Pattern",
            PatternCategory.STRUCTURAL,
            PatternDifficulty.INTERMEDIATE,
            "为其他对象提供一种代理以控制对这个对象的访问。",
            "为其他对象提供一种代理以控制对这个对象的访问。",
            "1. 当需要为一个对象在不同地址空间提供局部代表时\n2. 当需要创建开销非常大的对象时\n3. 当需要控制对原始对象的访问时\n4. 当需要为多个对象提供统一访问接口时",
            "Proxy（代理）- 持有RealSubject引用\nRealSubject（真实主题）- 定义真实对象\nSubject（主题）- 定义RealSubject和Proxy的公共接口",
            "Proxy控制对RealSubject的访问，可以延迟初始化、权限控制、日志记录等。",
            "优点：\n1. 职责清晰\n2. 高扩展性\n3. 智能化\n\n缺点：\n1. 请求处理速度变慢\n2. 实现复杂",
            "相关模式：\n- 适配器模式改变对象接口\n- 装饰器模式添加行为\n- 代理模式控制访问",
            "Spring的AOP代理、事务代理使用代理模式。",
            "1. java.lang.reflect.Proxy - 动态代理\n2. RMI Stub/Skeleton - 远程代理\n3. java.rmi.Remote",
            "1. 服务调用代理\n2. 缓存代理\n3. 权限控制代理",
            """
classDiagram
    class Subject {
        <<interface>>
        +request() void
    }
    class RealSubject {
        -String name
        +RealSubject(String)
        +request() void
        -loadFromDatabase() void
    }
    class Proxy {
        -RealSubject realSubject
        -String name
        +Proxy(String)
        +request() void
    }
    class Client {
        +main()
    }

    Subject <|.. RealSubject
    Subject <|.. Proxy
    Proxy o-- RealSubject : controls
    Client --> Subject : uses
"""
        );
        patterns.add(proxy);

        // 初始化观察者模式数据
        Pattern observer = new Pattern(
            "observer",
            "观察者模式",
            "Observer Pattern",
            PatternCategory.BEHAVIORAL,
            PatternDifficulty.BEGINNER,
            "定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。",
            "定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。",
            "1. 当一个抽象模型有两个方面，其中一个方面依赖于另一方面时\n2. 当对一个对象的改变需要同时改变其他对象，而不知道具体有多少对象有待改变时\n3. 当一个对象必须通知其他对象，而它又不能假定其他对象是谁时",
            "Subject（主题）- 知道观察者，提供注册/删除接口\nObserver（观察者）- 定义更新接口\nConcreteSubject（具体主题）- 存储状态\nConcreteObserver（具体观察者）- 维护Subject引用",
            "Subject维护观察者列表，状态改变时通知所有观察者。",
            "优点：\n1. 符合开闭原则\n2. 广播通信\n3. 解耦\n\n缺点：\n1. 通知顺序不确定\n2. 可能导致性能问题",
            "相关模式：\n- 中介者模式封装对象间的交互\n- 观察者模式用于广播通信",
            "Spring的ApplicationEvent、ApplicationListener使用观察者模式。",
            "1. java.util.Observer - 观察者接口\n2. PropertyChangeEvent - 属性变化事件\n3. Swing事件模型",
            "1. 消息订阅系统\n2. DOM事件监听\n3. MVVM框架数据绑定",
            """
classDiagram
    class Observer {
        <<interface>>
        +update(String) void
    }
    class ConcreteObserver {
        -String name
        +ConcreteObserver(String)
        +update(String) void
    }
    class Subject {
        <<abstract>>
        #List~Observer~ observers
        +attach(Observer)
        +detach(Observer)
        #notifyObservers(String)
    }
    class ConcreteSubject {
        -String state
        +setState(String)
        +getState() String
    }

    Observer <|.. ConcreteObserver
    Subject <|.. ConcreteSubject
    Subject o-- Observer : notifies
"""
        );
        patterns.add(observer);

        // 初始化策略模式数据
        Pattern strategy = new Pattern(
            "strategy",
            "策略模式",
            "Strategy Pattern",
            PatternCategory.BEHAVIORAL,
            PatternDifficulty.BEGINNER,
            "定义一系列算法，把它们一个个封装起来，并且使它们可相互替换。本模式使得算法可独立于使用它的客户而变化。",
            "定义一系列算法，把它们一个个封装起来，并且使它们可相互替换。本模式使得算法可独立于使用它的客户而变化。",
            "1. 许多相关的类仅仅是行为有不同时\n2. 需要使用一个算法的不同变体时\n3. 算法使用多个条件语句时\n4. 客户不需要知道算法的具体实现时",
            "Strategy（策略）- 定义算法接口\nConcreteStrategy（具体策略）- 实现具体算法\nContext（上下文）- 使用Strategy",
            "Context包含Strategy引用，可以在运行时切换具体策略。",
            "优点：\n1. 算法可自由切换\n2. 避免多重条件\n3. 扩展性好\n\n缺点：\n1. 策略类增多\n2. 客户需知道策略",
            "相关模式：\n- 状态模式与策略模式相似\n- 策略模式偏算法，状态模式偏状态",
            "Spring的Resource、AuthenticationProvider使用策略模式。",
            "1. Comparator - 比较策略\n2. ThreadPoolExecutor - 拒绝策略\n3. LayoutManager - 布局策略",
            "1. 支付方式选择\n2. 排序算法选择\n3. 压缩算法选择",
            """
classDiagram
    class Strategy {
        <<interface>>
        +execute(int, int) int
    }
    class AddStrategy {
        +execute(int, int) int
    }
    class SubtractStrategy {
        +execute(int, int) int
    }
    class MultiplyStrategy {
        +execute(int, int) int
    }
    class Context {
        -Strategy strategy
        +Context(Strategy)
        +setStrategy(Strategy)
        +executeStrategy(int, int) int
    }
    class Client {
        +main()
    }

    Strategy <|.. AddStrategy
    Strategy <|.. SubtractStrategy
    Strategy <|.. MultiplyStrategy
    Context o-- Strategy : uses
    Client --> Context : uses
"""
        );
        patterns.add(strategy);

        // 初始化责任链模式数据
        Pattern chain = new Pattern(
            "chain",
            "责任链模式",
            "Chain of Responsibility Pattern",
            PatternCategory.BEHAVIORAL,
            PatternDifficulty.INTERMEDIATE,
            "为解除请求发送者和接收者之间的耦合，使多个对象都有机会处理请求。",
            "为解除请求发送者和接收者之间的耦合，使多个对象都有机会处理请求。将这些对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止。",
            "1. 有多个对象可以处理请求，但不知道哪个对象处理时\n2. 想在不指定接收者的情况下向多个对象提交请求时\n3. 处理请求的对象集合需要动态指定时",
            "Handler（处理者）- 定义处理请求接口\nConcreteHandler（具体处理者）- 处理它所负责的请求\nClient（客户端）- 向链提交请求",
            "Client创建Handler链，请求沿链传递直到被处理。",
            "优点：\n1. 降低耦合度\n2. 简化对象\n3. 增强灵活性\n\n缺点：\n1. 不能保证请求被处理\n2. 调试困难",
            "相关模式：\n- 责任链模式与组合模式相似\n- 责任链模式常与组合模式结合使用",
            "Spring的FilterChain、InterceptorChain使用责任链模式。",
            "1. java.util.logging.Logger - 日志处理链\n2. Exception处理链\n3. Servlet过滤器链",
            "1. 审批流程\n2. 异常处理链\n3. 请求过滤器",
            """
classDiagram
    class Handler {
        <<abstract>>
        #Handler nextHandler
        +setNext(Handler) Handler
        +handleRequest(String)
    }
    class ConcreteHandlerA {
        +handleRequest(String)
    }
    class ConcreteHandlerB {
        +handleRequest(String)
    }
    class ConcreteHandlerC {
        +handleRequest(String)
    }
    class Client {
        +main()
    }

    Handler <|-- ConcreteHandlerA
    Handler <|-- ConcreteHandlerB
    Handler <|-- ConcreteHandlerC
    Handler o-- Handler : next
    Client --> Handler : uses
"""
        );
        patterns.add(chain);

        // 初始化模板方法模式数据
        Pattern template = new Pattern(
            "template",
            "模板方法模式",
            "Template Method Pattern",
            PatternCategory.BEHAVIORAL,
            PatternDifficulty.BEGINNER,
            "定义一个操作中的算法骨架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。",
            "定义一个操作中的算法骨架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。",
            "1. 一次性实现一个算法的不变部分，并将可变的行为留给子类来实现\n2. 各子类中公共的行为应被提取出来并集中到一个公共父类中以避免代码重复\n3. 控制子类扩展时",
            "AbstractClass（抽象类）- 定义抽象原语操作\nConcreteClass（具体类）- 实现原语操作",
            "AbstractClass定义模板方法，ConcreteClass实现具体步骤。",
            "优点：\n1. 代码复用\n2. 扩展性好\n3. 符合开闭原则\n\n缺点：\n1. 增加类数量\n2. 继承关系",
            "相关模式：\n- 模板方法模式使用继承\n- 策略模式使用组合\n- 工厂方法是模板方法的一种特殊形式",
            "Spring的JdbcTemplate、RestTemplate使用模板方法模式。",
            "1. java.io.InputStream - read()模板方法\n2. AbstractList - addAll()\n3. AbstractList - get()抽象方法",
            "1. 数据库访问模板\n2. 算法框架\n3. 业务流程模板",
            """
classDiagram
    class AbstractClass {
        <<abstract>>
        +templateMethod() void
        #primitiveOperation1() void
        #primitiveOperation2() void
        #primitiveOperation3() void
    }
    class ConcreteClassA {
        +primitiveOperation1() void
        +primitiveOperation2() void
        +primitiveOperation3() void
    }
    class ConcreteClassB {
        +primitiveOperation1() void
        +primitiveOperation2() void
        +primitiveOperation3() void
    }
    class Client {
        +main()
    }

    AbstractClass <|-- ConcreteClassA
    AbstractClass <|-- ConcreteClassB
    Client --> AbstractClass : uses
"""
        );
        patterns.add(template);
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
