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
        Pattern singleton = Pattern.builder()
            .id("singleton")
            .name("单例模式")
            .nameEn("Singleton Pattern")
            .category(PatternCategory.CREATIONAL)
            .difficulty(PatternDifficulty.BEGINNER)
            .definition("保证一个类仅有一个实例，并提供一个访问它的全局访问点。")
            .intent("保证一个类仅有一个实例，并提供一个访问它的全局访问点。")
            .useCases("1. 当类只能有一个实例而且客户可以从一个众所周知的访问点访问它时\n2. 当这个唯一实例应该是通过子类化可扩展的，并且客户应该无需更改代码就能使用一个扩展的实例时")
            .participants("Singleton（单例）- 提供getInstance()\nClient（客户端）- 通过getInstance()获取实例")
            .collaboration("Client通过调用Singleton.getInstance()获取唯一实例，然后使用实例的方法。")
            .prosCons("优点：\n1. 对唯一实例的受控访问\n2. 缩小命名空间（避免全局变量污染）\n3. 可以精简操作（比类操作更灵活）\n4. 允许改进操作和表示\n5. 允许可变数目的实例\n\n缺点：\n1. 单例模式对测试不友好（难以mock）\n2. 单例模式与单一职责原则可能冲突\n3. 在多线程环境下需要特殊处理")
            .relatedPatterns("相关模式：\n- 抽象工厂模式可以使用单例模式\n- 建造者模式可以使用单例模式\n- 原型模式可以使用单例模式")
            .springExample("Spring框架中，Bean默认是单例作用域（singleton scope）。")
            .jdkExample("1. Runtime.getRuntime() - Java运行时实例\n2. System.getRuntime()\n3. Desktop.getDesktop()\n4. SecurityManager.getSecurityManager()")
            .realWorldExample("1. 数据库连接池\n2. 配置管理器\n3. 日志记录器\n4. 缓存管理器")
            .mermaidDiagram("""
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
""")
            .codeExample("""
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
""")
            .build();
        patterns.add(singleton);

        // 初始化工厂方法模式数据
        Pattern factoryMethod = Pattern.builder()
            .id("factory-method")
            .name("工厂方法模式")
            .nameEn("Factory Method Pattern")
            .category(PatternCategory.CREATIONAL)
            .difficulty(PatternDifficulty.BEGINNER)
            .definition("定义一个用于创建对象的接口，让子类决定实例化哪一个类。")
            .intent("定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法使一个类的实例化延迟到其子类。")
            .useCases("1. 当一个类不知道它所必须创建的对象的类时\n2. 当类希望由它的子类来指定它所创建的对象时\n3. 当类将创建对象的职责委托给多个帮助子类中的某一个时")
            .participants("Product（产品）- 定义产品接口\nConcreteProduct（具体产品）- 实现Product接口\nFactory（工厂）- 声明工厂方法\nConcreteFactory（具体工厂）- 实现工厂方法创建具体产品")
            .collaboration("客户端通过ConcreteFactory创建ConcreteProduct，Factory定义创建接口，ConcreteFactory决定实例化哪个产品类。")
            .prosCons("优点：\n1. 符合开闭原则，新增产品无需修改现有代码\n2. 符合单一职责原则\n3. 解耦产品创建和使用\n\n缺点：\n1. 类的数量增多\n2. 增加系统复杂性\n3. 产品的创建完全依赖工厂类")
            .relatedPatterns("相关模式：\n- 抽象工厂模式经常使用工厂方法模式\n- 原型模式可以用工厂方法模式实现\n- 模板方法模式可以用工厂方法模式")
            .springExample("Spring的FactoryBean接口使用工厂方法模式创建Bean对象。")
            .jdkExample("1. Collection.iterator() - 返回迭代器\n2. Class.newInstance() - 创建对象实例\n3. Calendar.getInstance() - 获取日历实例")
            .realWorldExample("1. 日志框架中的Logger创建\n2. 数据库驱动加载\n3. 连接池工厂")
            .mermaidDiagram("""
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
""")
            .codeExample("""
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
""")
            .build();
        patterns.add(factoryMethod);

        // 初始化抽象工厂模式数据
        Pattern abstractFactory = Pattern.builder()
            .id("abstract-factory")
            .name("抽象工厂模式")
            .nameEn("Abstract Factory Pattern")
            .category(PatternCategory.CREATIONAL)
            .difficulty(PatternDifficulty.INTERMEDIATE)
            .definition("提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类。")
            .intent("提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类。")
            .useCases("1. 当系统要独立于它的产品的创建、组合和表示时\n2. 当系统要由多个产品系列中的一个来配置时\n3. 当要强调一系列相关的产品对象的设计以便进行联合使用时\n4. 当提供一个产品类库，而只想显示它们的接口而不是实现时")
            .participants("AbstractFactory（抽象工厂）- 声明创建抽象产品对象的操作\nConcreteFactory（具体工厂）- 实现创建具体产品对象的操作\nAbstractProduct（抽象产品）- 声明一类产品的对象接口\nProduct（具体产品）- 定义AbstractProduct接口，由ConcreteFactory创建")
            .collaboration("客户端只使用AbstractFactory和AbstractProduct类声明的接口，ConcreteFactory在运行时创建具体产品对象。")
            .prosCons("优点：\n1. 分离接口和实现\n2. 产品族内保证一致性\n3. 利于产品族切换\n\n缺点：\n1. 难以支持新种类产品\n2. 类层级复杂")
            .relatedPatterns("相关模式：\n- 抽象工厂模式通常用工厂方法模式实现\n- 具体工厂通常是单例模式\n- 产品可以是原型模式")
            .springExample("Spring的BeanFactory和ApplicationContext使用抽象工厂模式创建Bean。")
            .jdkExample("1. DocumentBuilderFactory - 创建XML解析器\n2. TransformerFactory - 创建XSLT处理器\n3. SAXParserFactory - 创建SAX解析器")
            .realWorldExample("1. 跨平台UI组件库（Windows/Mac/Linux组件族）\n2. 不同数据库的连接器族\n3. 不同主题的UI组件族")
            .mermaidDiagram("""
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
""")
            .codeExample("""
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
""")
            .build();
        patterns.add(abstractFactory);

        // 初始化建造者模式数据
        Pattern builder = Pattern.builder()
            .id("builder")
            .name("建造者模式")
            .nameEn("Builder Pattern")
            .category(PatternCategory.CREATIONAL)
            .difficulty(PatternDifficulty.INTERMEDIATE)
            .definition("将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。")
            .intent("将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。")
            .useCases("1. 当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时\n2. 当构造过程必须允许被构造的对象有不同的表示时")
            .participants("Builder（建造者）- 为创建产品对象的各个部件指定抽象接口\nConcreteBuilder（具体建造者）- 实现Builder接口以构造和装配各个部件\nDirector（指挥者）- 构建一个使用Builder接口的对象\nProduct（产品）- 被构造的复杂对象")
            .collaboration("Director创建ConcreteBuilder对象，调用Builder的构建方法，最后返回Product。")
            .prosCons("优点：\n1. 分步创建对象，流程清晰\n2. 可以控制对象创建细节\n3. 相同构建流程可创建不同表示\n\n缺点：\n1. 产品必须有共同点\n2. 产品内部结构复杂会增加Builder类")
            .relatedPatterns("相关模式：\n- 抽象工厂模式与建造者模式相似\n- 组合模式通常用建造者模式构建")
            .springExample("Spring的StringBuilder、UriComponentsBuilder使用建造者模式。")
            .jdkExample("1. StringBuilder - 构建字符串\n2. DocumentBuilder - 构建XML文档\n3. Locale.Builder - 构建Locale对象")
            .realWorldExample("1. SQL查询构建器\n2. HTTP请求构建器\n3. 配置对象构建器")
            .mermaidDiagram("""
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
""")
            .codeExample("""
// 产品类
public class Product {
    private String cpu;
    private String ram;
    private String storage;
    // ...

    private Product(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        // ...
    }

    public static class Builder {
        private String cpu = "默认CPU";
        private String ram = "8GB";
        private String storage = "512GB SSD";

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}

// 使用示例
Product product = new Product.Builder()
    .cpu("Intel i9")
    .ram("64GB")
    .storage("2TB SSD")
    .build();
""")
            .build();
        patterns.add(builder);

        // 初始化原型模式数据
        Pattern prototype = Pattern.builder()
            .id("prototype")
            .name("原型模式")
            .nameEn("Prototype Pattern")
            .category(PatternCategory.CREATIONAL)
            .difficulty(PatternDifficulty.BEGINNER)
            .definition("用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。")
            .intent("用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。")
            .useCases("1. 当一个系统应该独立于它的产品创建、构成和表示时\n2. 当要实例化的类是在运行时刻指定时\n3. 为了避免创建一个与产品类层次平行的工厂类层次时\n4. 当一个类的实例只能有几个不同状态组合中的一种时")
            .participants("Prototype（原型）- 声明克隆自己的接口\nConcretePrototype（具体原型）- 实现克隆方法\nClient（客户端）- 通过原型克隆创建新对象")
            .collaboration("Client通过调用Prototype的clone()方法创建新对象，无需知道具体创建细节。")
            .prosCons("优点：\n1. 性能优良，直接拷贝内存\n2. 逃避构造函数约束\n3. 简化对象创建\n\n缺点：\n1. 配合克隆方法需要注意\n2. 深拷贝与浅拷贝问题")
            .relatedPatterns("相关模式：\n- 抽象工厂模式可以用原型模式存储和克隆产品\n- 组合模式可以用原型模式克隆复杂结构")
            .springExample("Spring的Bean作用域prototype（原型）每次获取都创建新实例。")
            .jdkExample("1. Object.clone() - 克隆对象\n2. Cloneable接口 - 标记可克隆\n3. ArrayList.clone() - 克隆列表")
            .realWorldExample("1. 文档模板克隆\n2. 图形对象复制\n3. 数据库记录克隆")
            .mermaidDiagram("""
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
""")
            .codeExample("""
// 原型接口
public interface Prototype {
    Prototype clone();
}

// 具体原型类
public class ConcretePrototype implements Prototype {
    private String title;
    private String content;
    private String author;

    public ConcretePrototype(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype(this.title, this.content, this.author);
    }

    // Getters and Setters...
}

// 使用示例
ConcretePrototype original = new ConcretePrototype("标题", "内容", "作者");
ConcretePrototype cloned = (ConcretePrototype) original.clone();
cloned.setTitle("新标题");
""")
            .build();
        patterns.add(prototype);

        // 初始化适配器模式数据
        Pattern adapter = Pattern.builder()
            .id("adapter")
            .name("适配器模式")
            .nameEn("Adapter Pattern")
            .category(PatternCategory.STRUCTURAL)
            .difficulty(PatternDifficulty.BEGINNER)
            .definition("将一个类的接口转换成客户希望的另一个接口，使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。")
            .intent("将一个类的接口转换成客户希望的另一个接口，使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。")
            .useCases("1. 想使用一个已经存在的类，而它的接口不符合你的需求\n2. 想创建一个可以复用的类，该类可以与其他不相关的类或不可预见的类协同工作\n3. 想使用几个现有的子类，但通过对每个子类进行子类化来调整它们的接口是不现实的")
            .participants("Target（目标）- 定义Client使用的与特定领域相关的接口\nAdapter（适配器）- 将Adaptee接口转换成Target接口\nAdaptee（被适配者）- 需要适配的现有接口\nClient（客户端）- 符合Target接口的对象协同工作")
            .collaboration("Adapter通过包装Adaptee对象，将Adaptee的接口转换成Target接口。")
            .prosCons("优点：\n1. 提高类的复用性\n2. 增加类的透明性\n3. 灵活性好\n\n缺点：\n1. 过多使用适配器会让系统非常零乱\n2. 仅限对象适配")
            .relatedPatterns("相关模式：\n- 桥接模式与适配器模式相似\n- 装饰器模式与适配器模式相似")
            .springExample("Spring的HandlerAdapter、MethodBeforeAdviceAdapter使用适配器模式。")
            .jdkExample("1. Arrays.asList() - 数组转列表\n2. InputStreamReader - 字节流转字符流\n3. JDBC驱动适配器")
            .realWorldExample("1. 日志框架适配器\n2. 支付接口适配\n3. 第三方API集成")
            .mermaidDiagram("""
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
""")
            .codeExample("""
// 目标接口
interface Target {
    void request();
}

// 被适配者类
class Adaptee {
    public void specificRequest() {
        System.out.println("被适配者的特殊请求");
    }
}

// 适配器类
class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

// 使用示例
Adaptee adaptee = new Adaptee();
Target target = new Adapter(adaptee);
target.request();
""")
            .build();
        patterns.add(adapter);

        // 初始化装饰器模式数据
        Pattern decorator = Pattern.builder()
            .id("decorator")
            .name("装饰器模式")
            .nameEn("Decorator Pattern")
            .category(PatternCategory.STRUCTURAL)
            .difficulty(PatternDifficulty.INTERMEDIATE)
            .definition("动态地给一个对象添加一些额外的职责，就增加功能来说，装饰器模式比生成子类更为灵活。")
            .intent("动态地给一个对象添加一些额外的职责，就增加功能来说，装饰器模式比生成子类更为灵活。")
            .useCases("1. 在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责\n2. 当不能采用继承的方式对系统进行扩展时")
            .participants("Component（组件）- 定义对象接口\nConcreteComponent（具体组件）- 定义具体对象\nDecorator（装饰器）- 维持Component引用\nConcreteDecorator（具体装饰器）- 具体装饰功能")
            .collaboration("Decorator继承Component并包含Component引用，ConcreteDecorator添加具体功能。")
            .prosCons("优点：\n1. 比继承更灵活\n2. 避免类层次爆炸\n3. 动态添加职责\n\n缺点：\n1. 产生更多小对象\n2. 装饰层次复杂")
            .relatedPatterns("相关模式：\n- 适配器模式改变对象接口\n- 组合模式与装饰器模式相似\n- 装饰器模式不同于策略模式")
            .springExample("Spring的HttpRequestDecorator、BufferedReader使用装饰器模式。")
            .jdkExample("1. java.io包中的InputStream/OutputStream\n2. Collections.unmodifiableList()\n3. Collections.synchronizedList()")
            .realWorldExample("1. UI组件装饰\n2. 缓存装饰器\n3. 压缩/解压流")
            .mermaidDiagram("""
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
    class WhipDecorator {
        +WhipDecorator(Component)
        +operation() void
    }

    Component <|.. ConcreteComponent
    Component <|.. Decorator
    Decorator <|-- MilkDecorator
    Decorator <|-- SugarDecorator
    Decorator <|-- WhipDecorator
    Decorator o-- Component : decorates
""")
            .codeExample("""
// 组件接口
interface Component {
    void operation();
}

// 具体组件
class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.print("基础组件");
    }
}

// 装饰器抽象类
abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

// 具体装饰器
class MilkDecorator extends Decorator {
    public MilkDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        component.operation();
        System.out.print(" + 牛奶");
    }
}

// 使用示例
Component coffee = new ConcreteComponent();
Component coffeeWithMilk = new MilkDecorator(coffee);
coffeeWithMilk.operation();
""")
            .build();
        patterns.add(decorator);

        // 初始化代理模式数据
        Pattern proxy = Pattern.builder()
            .id("proxy")
            .name("代理模式")
            .nameEn("Proxy Pattern")
            .category(PatternCategory.STRUCTURAL)
            .difficulty(PatternDifficulty.INTERMEDIATE)
            .definition("为其他对象提供一种代理以控制对这个对象的访问。")
            .intent("为其他对象提供一种代理以控制对这个对象的访问。")
            .useCases("1. 当需要为一个对象在不同地址空间提供局部代表时\n2. 当需要创建开销非常大的对象时\n3. 当需要控制对原始对象的访问时\n4. 当需要为多个对象提供统一访问接口时")
            .participants("Proxy（代理）- 持有RealSubject引用\nRealSubject（真实主题）- 定义真实对象\nSubject（主题）- 定义RealSubject和Proxy的公共接口")
            .collaboration("Proxy控制对RealSubject的访问，可以延迟初始化、权限控制、日志记录等。")
            .prosCons("优点：\n1. 职责清晰\n2. 高扩展性\n3. 智能化\n\n缺点：\n1. 请求处理速度变慢\n2. 实现复杂")
            .relatedPatterns("相关模式：\n- 适配器模式改变对象接口\n- 装饰器模式添加行为\n- 代理模式控制访问")
            .springExample("Spring的AOP代理、事务代理使用代理模式。")
            .jdkExample("1. java.lang.reflect.Proxy - 动态代理\n2. RMI Stub/Skeleton - 远程代理\n3. java.rmi.Remote")
            .realWorldExample("1. 服务调用代理\n2. 缓存代理\n3. 权限控制代理")
            .mermaidDiagram("""
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
""")
            .codeExample("""
// 主题接口
interface Subject {
    void request();
}

// 真实主题
class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("执行真实请求");
    }
}

// 代理类
class Proxy implements Subject {
    private RealSubject realSubject;

    @Override
    public void request() {
        // 延迟初始化
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        // 添加额外功能
        System.out.println("请求前...");
        realSubject.request();
        System.out.println("请求后...");
    }
}

// 使用示例
Subject proxy = new Proxy();
proxy.request();
""")
            .build();
        patterns.add(proxy);

        // 初始化观察者模式数据
        Pattern observer = Pattern.builder()
            .id("observer")
            .name("观察者模式")
            .nameEn("Observer Pattern")
            .category(PatternCategory.BEHAVIORAL)
            .difficulty(PatternDifficulty.BEGINNER)
            .definition("定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。")
            .intent("定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。")
            .useCases("1. 当一个抽象模型有两个方面，其中一个方面依赖于另一方面时\n2. 当对一个对象的改变需要同时改变其他对象，而不知道具体有多少对象有待改变时\n3. 当一个对象必须通知其他对象，而它又不能假定其他对象是谁时")
            .participants("Subject（主题）- 知道观察者，提供注册/删除接口\nObserver（观察者）- 定义更新接口\nConcreteSubject（具体主题）- 存储状态\nConcreteObserver（具体观察者）- 维护Subject引用")
            .collaboration("Subject维护观察者列表，状态改变时通知所有观察者。")
            .prosCons("优点：\n1. 符合开闭原则\n2. 广播通信\n3. 解耦\n\n缺点：\n1. 通知顺序不确定\n2. 可能导致性能问题")
            .relatedPatterns("相关模式：\n- 中介者模式封装对象间的交互\n- 观察者模式用于广播通信")
            .springExample("Spring的ApplicationEvent、ApplicationListener使用观察者模式。")
            .jdkExample("1. java.util.Observer - 观察者接口\n2. PropertyChangeEvent - 属性变化事件\n3. Swing事件模型")
            .realWorldExample("1. 消息订阅系统\n2. DOM事件监听\n3. MVVM框架数据绑定")
            .mermaidDiagram("""
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
""")
            .codeExample("""
// 观察者接口
interface Observer {
    void update(String message);
}

// 主题抽象类
abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// 具体主题
class ConcreteSubject extends Subject {
    private String state;

    public void setState(String state) {
        this.state = state;
        notifyObservers(state);
    }
}

// 具体观察者
class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " 收到: " + message);
    }
}

// 使用示例
ConcreteSubject subject = new ConcreteSubject();
Observer observer1 = new ConcreteObserver("张三");
Observer observer2 = new ConcreteObserver("李四");

subject.attach(observer1);
subject.attach(observer2);
subject.setState("新消息");
""")
            .build();
        patterns.add(observer);

        // 初始化策略模式数据
        Pattern strategy = Pattern.builder()
            .id("strategy")
            .name("策略模式")
            .nameEn("Strategy Pattern")
            .category(PatternCategory.BEHAVIORAL)
            .difficulty(PatternDifficulty.BEGINNER)
            .definition("定义一系列算法，把它们一个个封装起来，并且使它们可相互替换。本模式使得算法可独立于使用它的客户而变化。")
            .intent("定义一系列算法，把它们一个个封装起来，并且使它们可相互替换。本模式使得算法可独立于使用它的客户而变化。")
            .useCases("1. 许多相关的类仅仅是行为有不同时\n2. 需要使用一个算法的不同变体时\n3. 算法使用多个条件语句时\n4. 客户不需要知道算法的具体实现时")
            .participants("Strategy（策略）- 定义算法接口\nConcreteStrategy（具体策略）- 实现具体算法\nContext（上下文）- 使用Strategy")
            .collaboration("Context包含Strategy引用，可以在运行时切换具体策略。")
            .prosCons("优点：\n1. 算法可自由切换\n2. 避免多重条件\n3. 扩展性好\n\n缺点：\n1. 策略类增多\n2. 客户需知道策略")
            .relatedPatterns("相关模式：\n- 状态模式与策略模式相似\n- 策略模式偏算法，状态模式偏状态")
            .springExample("Spring的Resource、AuthenticationProvider使用策略模式。")
            .jdkExample("1. Comparator - 比较策略\n2. ThreadPoolExecutor - 拒绝策略\n3. LayoutManager - 布局策略")
            .realWorldExample("1. 支付方式选择\n2. 排序算法选择\n3. 压缩算法选择")
            .mermaidDiagram("""
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
""")
            .codeExample("""
// 策略接口
interface Strategy {
    int execute(int a, int b);
}

// 具体策略 - 加法
class AddStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a + b;
    }
}

// 具体策略 - 减法
class SubtractStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a - b;
    }
}

// 上下文类
class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}

// 使用示例
Context context = new Context(new AddStrategy());
int result = context.executeStrategy(10, 5);  // 15

context.setStrategy(new SubtractStrategy());
result = context.executeStrategy(10, 5);  // 5
""")
            .build();
        patterns.add(strategy);

        // 初始化责任链模式数据
        Pattern chain = Pattern.builder()
            .id("chain")
            .name("责任链模式")
            .nameEn("Chain of Responsibility Pattern")
            .category(PatternCategory.BEHAVIORAL)
            .difficulty(PatternDifficulty.INTERMEDIATE)
            .definition("为解除请求发送者和接收者之间的耦合，使多个对象都有机会处理请求。")
            .intent("为解除请求发送者和接收者之间的耦合，使多个对象都有机会处理请求。将这些对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止。")
            .useCases("1. 有多个对象可以处理请求，但不知道哪个对象处理时\n2. 想在不指定接收者的情况下向多个对象提交请求时\n3. 处理请求的对象集合需要动态指定时")
            .participants("Handler（处理者）- 定义处理请求接口\nConcreteHandler（具体处理者）- 处理它所负责的请求\nClient（客户端）- 向链提交请求")
            .collaboration("Client创建Handler链，请求沿链传递直到被处理。")
            .prosCons("优点：\n1. 降低耦合度\n2. 简化对象\n3. 增强灵活性\n\n缺点：\n1. 不能保证请求被处理\n2. 调试困难")
            .relatedPatterns("相关模式：\n- 责任链模式与组合模式相似\n- 责任链模式常与组合模式结合使用")
            .springExample("Spring的FilterChain、InterceptorChain使用责任链模式。")
            .jdkExample("1. java.util.logging.Logger - 日志处理链\n2. Exception处理链\n3. Servlet过滤器链")
            .realWorldExample("1. 审批流程\n2. 异常处理链\n3. 请求过滤器")
            .mermaidDiagram("""
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
""")
            .codeExample("""
// 处理者抽象类
abstract class Handler {
    protected Handler nextHandler;

    public Handler setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    public abstract void handleRequest(String request);
}

// 具体处理者A
class ConcreteHandlerA extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("普通请求")) {
            System.out.println("HandlerA 处理");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// 具体处理者B
class ConcreteHandlerB extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("重要请求")) {
            System.out.println("HandlerB 处理");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// 使用示例
Handler handlerA = new ConcreteHandlerA();
Handler handlerB = new ConcreteHandlerB();

handlerA.setNext(handlerB);
handlerA.handleRequest("重要请求");
""")
            .build();
        patterns.add(chain);

        // 初始化模板方法模式数据
        Pattern template = Pattern.builder()
            .id("template")
            .name("模板方法模式")
            .nameEn("Template Method Pattern")
            .category(PatternCategory.BEHAVIORAL)
            .difficulty(PatternDifficulty.BEGINNER)
            .definition("定义一个操作中的算法骨架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。")
            .intent("定义一个操作中的算法骨架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。")
            .useCases("1. 一次性实现一个算法的不变部分，并将可变的行为留给子类来实现\n2. 各子类中公共的行为应被提取出来并集中到一个公共父类中以避免代码重复\n3. 控制子类扩展时")
            .participants("AbstractClass（抽象类）- 定义抽象原语操作\nConcreteClass（具体类）- 实现原语操作")
            .collaboration("AbstractClass定义模板方法，ConcreteClass实现具体步骤。")
            .prosCons("优点：\n1. 代码复用\n2. 扩展性好\n3. 符合开闭原则\n\n缺点：\n1. 增加类数量\n2. 继承关系")
            .relatedPatterns("相关模式：\n- 模板方法模式使用继承\n- 策略模式使用组合\n- 工厂方法是模板方法的一种特殊形式")
            .springExample("Spring的JdbcTemplate、RestTemplate使用模板方法模式。")
            .jdkExample("1. java.io.InputStream - read()模板方法\n2. AbstractList - addAll()\n3. AbstractList - get()抽象方法")
            .realWorldExample("1. 数据库访问模板\n2. 算法框架\n3. 业务流程模板")
            .mermaidDiagram("""
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
""")
            .codeExample("""
// 抽象类
abstract class AbstractClass {
    // 模板方法 - 定义算法骨架
    public final void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
        primitiveOperation3();
    }

    // 基本方法 - 由子类实现
    protected abstract void primitiveOperation1();
    protected abstract void primitiveOperation2();
    protected abstract void primitiveOperation3();
}

// 具体类A
class ConcreteClassA extends AbstractClass {
    @Override
    protected void primitiveOperation1() {
        System.out.println("步骤1");
    }

    @Override
    protected void primitiveOperation2() {
        System.out.println("步骤2");
    }

    @Override
    protected void primitiveOperation3() {
        System.out.println("步骤3");
    }
}

// 使用示例
AbstractClass obj = new ConcreteClassA();
obj.templateMethod();
""")
            .build();
        patterns.add(template);

        // 初始化桥接模式数据
        Pattern bridge = Pattern.builder()
            .id("bridge")
            .name("桥接模式")
            .nameEn("Bridge Pattern")
            .category(PatternCategory.STRUCTURAL)
            .difficulty(PatternDifficulty.INTERMEDIATE)
            .definition("将抽象部分与实现部分分离，使它们都可以独立地变化。")
            .intent("将抽象部分与实现部分分离，使它们都可以独立地变化。")
            .useCases("1. 当不希望在抽象和实现之间有固定的绑定关系时\n2. 当类的抽象和实现可以通过生成子类来加以扩充时")
            .participants("Abstraction（抽象化）- 定义抽象类接口\nImplementor（实现化）- 定义实现类接口\nConcreteImplementor（具体实现化）- 实现Implementor")
            .collaboration("Abstraction包含Implementor引用，具体操作委托给Implementor。")
            .prosCons("优点：分离接口和实现\n缺点：增加系统复杂性")
            .relatedPatterns("相关模式：桥接模式与适配器模式相似")
            .springExample("Spring的DataSource使用桥接模式。")
            .jdkExample("1. java.sql.Driver - 数据库驱动\n2. java.util.logging.Handler")
            .realWorldExample("1. 不同数据库驱动\n2. 不同支付方式")
            .mermaidDiagram("""
classDiagram
    class Color {
        <<interface>>
        +apply() String
    }
    class RedColor {
        +apply() String
    }
    class BlueColor {
        +apply() String
    }
    class GreenColor {
        +apply() String
    }
    class Shape {
        <<abstract>>
        #Color color
        +Shape(Color)
        +draw() void
    }
    class Circle {
        +Circle(Color)
        +draw() void
    }
    class Square {
        +Square(Color)
        +draw() void
    }

    Color <|.. RedColor
    Color <|.. BlueColor
    Color <|.. GreenColor
    Shape <|-- Circle
    Shape <|-- Square
    Shape o-- Color : uses
""")
            .codeExample("""
// 实现化接口 - 颜色
interface Color {
    String apply();
}

// 具体实现化
class RedColor implements Color {
    @Override
    public String apply() {
        return "红色";
    }
}

class BlueColor implements Color {
    @Override
    public String apply() {
        return "蓝色";
    }
}

// 抽象化类 - 形状
abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}

// 扩展抽象化
class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("绘制" + color.apply() + "的圆形");
    }
}

// 使用示例
Color red = new RedColor();
Shape redCircle = new Circle(red);
redCircle.draw();
""")
            .build();
        patterns.add(bridge);

        // 初始化组合模式数据
        Pattern composite = Pattern.builder()
            .id("composite")
            .name("组合模式")
            .nameEn("Composite Pattern")
            .category(PatternCategory.STRUCTURAL)
            .difficulty(PatternDifficulty.INTERMEDIATE)
            .definition("将对象组合成树形结构以表示'部分-整体'的层次结构。")
            .intent("将对象组合成树形结构以表示'部分-整体'的层次结构。")
            .useCases("1. 表示对象的部分-整体层次结构时\n2. 使用者忽略组合对象与单个对象的不同时")
            .participants("Component（组件）- 声明组合接口\nLeaf（叶子）- 定义组件行为\nComposite（组合）- 存储子组件")
            .collaboration("Leaf实现Component，Composite存储Component子对象。")
            .prosCons("优点：简化客户端代码\n缺点：设计复杂")
            .relatedPatterns("相关模式：组合模式与装饰器模式相似")
            .springExample("Spring的HttpServletRequest使用组合模式。")
            .jdkExample("1. java.awt.Container - 容器组件\n2. javax.swing.JComponent")
            .realWorldExample("1. 文件系统\n2. UI组件树")
            .mermaidDiagram("""
classDiagram
    class Component {
        <<interface>>
        +operation() void
        +add(Component)
        +remove(Component)
        +getChild(int) Component
    }
    class Leaf {
        -String name
        +Leaf(String)
        +operation() void
        +add(Component)
        +remove(Component)
        +getChild(int) Component
    }
    class Composite {
        -String name
        -List~Component~ children
        +Composite(String)
        +operation() void
        +add(Component)
        +remove(Component)
        +getChild(int) Component
    }
    class Client {
        +main()
    }

    Component <|.. Leaf
    Component <|.. Composite
    Composite o-- Component : contains
    Client --> Component : uses
""")
            .codeExample("""
// 组件接口
interface Component {
    void operation();
    void add(Component component);
    void remove(Component component);
    Component getChild(int i);
}

// 叶子节点
class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("叶子: " + name);
    }

    @Override
    public void add(Component component) {
        // 叶子不能添加子节点
    }

    // ...其他方法
}

// 组合节点
class Composite implements Component {
    private List<Component> children = new ArrayList<>();

    @Override
    public void operation() {
        for (Component child : children) {
            child.operation();
        }
    }

    @Override
    public void add(Component component) {
        children.add(component);
    }

    // ...其他方法
}

// 使用示例
Composite root = new Composite();
root.add(new Leaf("文件1"));
root.add(new Leaf("文件2"));
root.operation();
""")
            .build();
        patterns.add(composite);

        // 初始化外观模式数据
        Pattern facade = Pattern.builder()
            .id("facade")
            .name("外观模式")
            .nameEn("Facade Pattern")
            .category(PatternCategory.STRUCTURAL)
            .difficulty(PatternDifficulty.BEGINNER)
            .definition("为子系统中的一组接口提供一个一致的界面。")
            .intent("为子系统中的一组接口提供一个一致的界面。")
            .useCases("1. 当需要为复杂的子系统提供简单接口时\n2. 客户程序与抽象类存在很大依赖时")
            .participants("Facade（外观）- 知道哪些子系统负责处理请求\nSubsystem（子系统）- 实现子系统功能")
            .collaboration("Facade调用子系统，客户端只与Facade交互。")
            .prosCons("优点：降低耦合度、简化接口\n缺点：不符合开闭原则")
            .relatedPatterns("相关模式：外观模式与中介者模式相似")
            .springExample("Spring的JdbcTemplate使用外观模式。")
            .jdkExample("1. java.net.URL - 统一资源定位\n2. javax.faces.context.FacesContext")
            .realWorldExample("1. 库存管理系统\n2. API网关")
            .mermaidDiagram("""
classDiagram
    class CPU {
        +start() void
        +execute() void
        +shutdown() void
    }
    class Memory {
        +load() void
        +unload() void
    }
    class HardDrive {
        +read() void
        +write() void
    }
    class ComputerFacade {
        -CPU cpu
        -Memory memory
        -HardDrive hardDrive
        +ComputerFacade()
        +startComputer() void
        +shutdownComputer() void
    }
    class Client {
        +main()
    }

    ComputerFacade --> CPU : uses
    ComputerFacade --> Memory : uses
    ComputerFacade --> HardDrive : uses
    Client --> ComputerFacade : uses
""")
            .codeExample("""
// 子系统类
class CPU {
    public void start() { System.out.println("CPU启动"); }
    public void shutdown() { System.out.println("CPU关闭"); }
}

class Memory {
    public void load() { System.out.println("内存加载"); }
    public void unload() { System.out.println("内存释放"); }
}

class HardDrive {
    public void read() { System.out.println("硬盘读取"); }
    public void write() { System.out.println("硬盘写入"); }
}

// 外观类
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void startComputer() {
        hardDrive.read();
        memory.load();
        cpu.start();
        System.out.println("电脑启动完成");
    }

    public void shutdownComputer() {
        cpu.shutdown();
        memory.unload();
        hardDrive.write();
        System.out.println("电脑已关闭");
    }
}

// 使用示例
ComputerFacade computer = new ComputerFacade();
computer.startComputer();
computer.shutdownComputer();
""")
            .build();
        patterns.add(facade);

        // 初始化享元模式数据
        Pattern flyweight = Pattern.builder()
            .id("flyweight")
            .name("享元模式")
            .nameEn("Flyweight Pattern")
            .category(PatternCategory.STRUCTURAL)
            .difficulty(PatternDifficulty.ADVANCED)
            .definition("运用共享技术有效地支持大量细粒度的对象。")
            .intent("运用共享技术有效地支持大量细粒度的对象。")
            .useCases("1. 应用程序使用了大量对象时\n2. 对象的大部分状态可以外部环境时")
            .participants("Flyweight（享元）- 定义接口\nConcreteFlyweight（具体享元）- 实现接口\nFlyweightFactory（享元工厂）- 管理享元")
            .collaboration("FlyweightFactory创建和管理Flyweight。")
            .prosCons("优点：减少对象数量、节省内存\n缺点：增加运行时间")
            .relatedPatterns("相关模式：享元模式与组合模式可以结合使用")
            .springExample("Spring的Bean作用域singleton使用享元模式思想。")
            .jdkExample("1. String.intern() - 字符串常量池\n2. Integer.valueOf() - 缓存")
            .realWorldExample("1. 文本编辑器字符\n2. 游戏对象池")
            .mermaidDiagram("""
classDiagram
    class Flyweight {
        <<interface>>
        +operation(String) void
    }
    class ConcreteFlyweight {
        -String intrinsicState
        +ConcreteFlyweight(String)
        +operation(String) void
    }
    class FlyweightFactory {
        -Map~String,Flyweight~ flyweights
        +getFlyweight(String) Flyweight
        +getCount() int
    }
    class Client {
        +main()
    }

    Flyweight <|.. ConcreteFlyweight
    FlyweightFactory --> Flyweight : creates/manages
    Client --> FlyweightFactory : uses
""")
            .codeExample("""
// 享元接口
interface Flyweight {
    void operation(String extrinsicState);
}

// 具体享元
class ConcreteFlyweight implements Flyweight {
    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(String extrinsicState) {
        System.out.println("内部: " + intrinsicState + ", 外部: " + extrinsicState);
    }
}

// 享元工厂
class FlyweightFactory {
    private Map<String, Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(String key) {
        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new ConcreteFlyweight(key));
        }
        return flyweights.get(key);
    }
}

// 使用示例
FlyweightFactory factory = new FlyweightFactory();
Flyweight fw1 = factory.getFlyweight("A");
Flyweight fw2 = factory.getFlyweight("A"); // 复用
fw1.operation("状态1");
fw2.operation("状态2");
""")
            .build();
        patterns.add(flyweight);

        // 初始化命令模式数据
        Pattern command = Pattern.builder()
            .id("command")
            .name("命令模式")
            .nameEn("Command Pattern")
            .category(PatternCategory.BEHAVIORAL)
            .difficulty(PatternDifficulty.INTERMEDIATE)
            .definition("将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化。")
            .intent("将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化。")
            .useCases("1. 需要抽象出待执行的动作时\n2. 需要在不同时刻指定、排列和执行请求时\n3. 需要支持取消操作时")
            .participants("Command（命令）- 声明执行接口\nConcreteCommand（具体命令）- 实现命令\nReceiver（接收者）- 执行操作")
            .collaboration("Invoker调用Command，Command操作Receiver。")
            .prosCons("优点：降低耦合度、易于扩展、支持撤销\n缺点：类数量增多")
            .relatedPatterns("相关模式：命令模式与组合模式可以结合使用")
            .springExample("Spring的CommandRunner使用命令模式。")
            .jdkExample("1. java.lang.Runnable - 命令接口\n2. javax.swing.Action")
            .realWorldExample("1. GUI按钮操作\n2. 任务调度")
            .mermaidDiagram("""
classDiagram
    class Command {
        <<interface>>
        +execute() void
        +undo() void
    }
    class LightOnCommand {
        -Light light
        +LightOnCommand(Light)
        +execute() void
        +undo() void
    }
    class LightOffCommand {
        -Light light
        +LightOffCommand(Light)
        +execute() void
        +undo() void
    }
    class Light {
        +on() void
        +off() void
    }
    class RemoteControl {
        -Command command
        +setCommand(Command)
        +pressButton() void
        +pressUndo() void
    }
    class Client {
        +main()
    }

    Command <|.. LightOnCommand
    Command <|.. LightOffCommand
    LightOnCommand --> Light : operates on
    LightOffCommand --> Light : operates on
    RemoteControl o-- Command : uses
    Client --> RemoteControl : uses
""")
            .codeExample("""
// 命令接口
interface Command {
    void execute();
    void undo();
}

// 接收者
class Light {
    public void on() { System.out.println("开灯"); }
    public void off() { System.out.println("关灯"); }
}

// 具体命令
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

// 调用者
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}

// 使用示例
Light light = new Light();
Command lightOn = new LightOnCommand(light);

RemoteControl remote = new RemoteControl();
remote.setCommand(lightOn);
remote.pressButton();
remote.pressUndo();
""")
            .build();
        patterns.add(command);

        // 初始化迭代器模式数据
        Pattern iterator = Pattern.builder()
            .id("iterator")
            .name("迭代器模式")
            .nameEn("Iterator Pattern")
            .category(PatternCategory.BEHAVIORAL)
            .difficulty(PatternDifficulty.BEGINNER)
            .definition("提供一种方法顺序访问一个聚合对象中各个元素。")
            .intent("提供一种方法顺序访问一个聚合对象中各个元素。")
            .useCases("1. 需要访问聚合对象内容而不暴露内部表示时\n2. 需要为聚合对象提供多种遍历方式时")
            .participants("Iterator（迭代器）- 定义访问接口\nAggregate（聚合）- 定义创建迭代器接口")
            .collaboration("Aggregate创建Iterator，Iterator遍历Aggregate。")
            .prosCons("优点：符合单一职责、简化聚合接口\n缺点：增加类数量")
            .relatedPatterns("相关模式：迭代器模式与组合模式一起使用")
            .springExample("Spring的Iterator、Stream使用迭代器模式。")
            .jdkExample("1. java.util.Iterator - 迭代器接口\n2. java.util.Collection")
            .realWorldExample("1. 集合遍历\n2. 树形结构遍历")
            .mermaidDiagram("""
classDiagram
    class Iterator {
        <<interface>>
        +hasNext() boolean
        +next() Object
    }
    class Aggregate {
        <<interface>>
        +createIterator() Iterator
    }
    class NameCollection {
        -String[] names
        +NameCollection(String[])
        +createIterator() Iterator
    }
    class NameIterator {
        -String[] names
        -int position
        +NameIterator(String[])
        +hasNext() boolean
        +next() Object
    }
    class Client {
        +main()
    }

    Iterator <|.. NameIterator
    Aggregate <|.. NameCollection
    NameCollection --> NameIterator : creates
    Client --> Aggregate : uses
    Client --> Iterator : uses
""")
            .codeExample("""
// 迭代器接口
interface Iterator {
    boolean hasNext();
    Object next();
}

// 聚合接口
interface Aggregate {
    Iterator createIterator();
}

// 具体聚合
class NameCollection implements Aggregate {
    private String[] names;

    public NameCollection(String[] names) {
        this.names = names;
    }

    @Override
    public Iterator createIterator() {
        return new NameIterator(names);
    }
}

// 具体迭代器
class NameIterator implements Iterator {
    private String[] names;
    private int position = 0;

    public NameIterator(String[] names) {
        this.names = names;
    }

    @Override
    public boolean hasNext() {
        return position < names.length;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return names[position++];
        }
        return null;
    }
}

// 使用示例
String[] names = {"张三", "李四", "王五"};
Aggregate collection = new NameCollection(names);
Iterator iterator = collection.createIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
""")
            .build();
        patterns.add(iterator);

        // 初始化中介者模式数据
        Pattern mediator = Pattern.builder()
            .id("mediator")
            .name("中介者模式")
            .nameEn("Mediator Pattern")
            .category(PatternCategory.BEHAVIORAL)
            .difficulty(PatternDifficulty.INTERMEDIATE)
            .definition("用一个中介对象来封装一系列的对象交互。")
            .intent("用一个中介对象来封装一系列的对象交互。")
            .useCases("1. 一组对象以定义良好但是复杂的方式进行通信时\n2. 对象难以引用其他对象时")
            .participants("Mediator（中介者）- 定义交互接口\nColleague（同事）- 持有Mediator引用")
            .collaboration("Colleague通过Mediator交互。")
            .prosCons("优点：降低耦合度、集中控制交互\n缺点：中介者变得复杂")
            .relatedPatterns("相关模式：中介者模式与观察者模式相似")
            .springExample("Spring的ApplicationContext使用中介者模式。")
            .jdkExample("1. java.util.Timer - 定时器调度")
            .realWorldExample("1. 聊天室\n2. 航空管制系统")
            .mermaidDiagram("""
classDiagram
    class Mediator {
        <<interface>>
        +sendMessage(String, Colleague)
    }
    class Colleague {
        <<abstract>>
        #Mediator mediator
        +Colleague(Mediator)
        +receive(String)
        +send(String)
    }
    class ConcreteMediator {
        -ConcreteColleague1 colleague1
        -ConcreteColleague2 colleague2
        +setColleague1(ConcreteColleague1)
        +setColleague2(ConcreteColleague2)
        +sendMessage(String, Colleague)
    }
    class ConcreteColleague1 {
        +ConcreteColleague1(Mediator)
        +receive(String)
    }
    class ConcreteColleague2 {
        +ConcreteColleague2(Mediator)
        +receive(String)
    }

    Mediator <|.. ConcreteMediator
    Colleague <|-- ConcreteColleague1
    Colleague <|-- ConcreteColleague2
    Colleague o-- Mediator : uses
    ConcreteMediator --> ConcreteColleague1 : coordinates
    ConcreteMediator --> ConcreteColleague2 : coordinates
""")
            .codeExample("""
// 中介者接口
interface Mediator {
    void sendMessage(String message, Colleague colleague);
}

// 同事抽象类
abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive(String message);

    public void send(String message) {
        mediator.sendMessage(message, this);
    }
}

// 具体中介者
class ConcreteMediator implements Mediator {
    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;

    public void setColleague1(ConcreteColleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(ConcreteColleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    @Override
    public void sendMessage(String message, Colleague colleague) {
        if (colleague == colleague1) {
            colleague2.receive(message);
        } else {
            colleague1.receive(message);
        }
    }
}

// 具体同事
class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void receive(String message) {
        System.out.println("同事1收到: " + message);
    }
}

// 使用示例
ConcreteMediator mediator = new ConcreteMediator();
ConcreteColleague1 c1 = new ConcreteColleague1(mediator);
ConcreteColleague2 c2 = new ConcreteColleague2(mediator);

mediator.setColleague1(c1);
mediator.setColleague2(c2);

c1.send("你好");
c2.send("你好");
""")
            .build();
        patterns.add(mediator);

        // 初始化备忘录模式数据
        Pattern memento = Pattern.builder()
            .id("memento")
            .name("备忘录模式")
            .nameEn("Memento Pattern")
            .category(PatternCategory.BEHAVIORAL)
            .difficulty(PatternDifficulty.INTERMEDIATE)
            .definition("在不破坏封装性的前提下，捕获一个对象的内部状态。")
            .intent("在不破坏封装性的前提下，捕获一个对象的内部状态。")
            .useCases("1. 需要保存对象的状态时\n2. 不能通过接口暴露内部状态时")
            .participants("Memento（备忘录）- 存储状态\nOriginator（发起者）- 创建和恢复备忘录")
            .collaboration("Originator创建Memento，Caretaker管理Memento。")
            .prosCons("优点：保持封装、简化Originator\n缺点：消耗资源")
            .relatedPatterns("相关模式：备忘录模式与命令模式可以结合使用")
            .springExample("Spring的@SessionAttribute使用备忘录模式。")
            .jdkExample("1. java.io.Serializable - 序列化接口")
            .realWorldExample("1. 文本编辑器撤销\n2. 游戏存档")
            .mermaidDiagram("""
classDiagram
    class Memento {
        -String state
        +Memento(String)
        +getState() String
    }
    class Originator {
        -String state
        +setState(String)
        +getState() String
        +save() Memento
        +restore(Memento)
    }
    class Caretaker {
        -List~Memento~ mementos
        +add(Memento)
        +get(int) Memento
    }
    class Client {
        +main()
    }

    Originator ..> Memento : creates
    Originator ..> Memento : restores
    Caretaker o-- Memento : stores
    Client --> Originator : uses
    Client --> Caretaker : uses
""")
            .codeExample("""
// 备忘录类
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

// 发起人类
class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento save() {
        return new Memento(state);
    }

    public void restore(Memento memento) {
        this.state = memento.getState();
    }
}

// 管理者类
class Caretaker {
    private List<Memento> mementos = new ArrayList<>();

    public void add(Memento memento) {
        mementos.add(memento);
    }

    public Memento get(int index) {
        return mementos.get(index);
    }
}

// 使用示例
Originator originator = new Originator();
Caretaker caretaker = new Caretaker();

originator.setState("状态1");
caretaker.add(originator.save());

originator.setState("状态2");
originator.restore(caretaker.get(0));
""")
            .build();
        patterns.add(memento);

        // 初始化状态模式数据
        Pattern state = Pattern.builder()
            .id("state")
            .name("状态模式")
            .nameEn("State Pattern")
            .category(PatternCategory.BEHAVIORAL)
            .difficulty(PatternDifficulty.INTERMEDIATE)
            .definition("允许一个对象在其内部状态改变时改变它的行为。")
            .intent("允许一个对象在其内部状态改变时改变它的行为。")
            .useCases("1. 对象的行为取决于它的状态时\n2. 需要在运行时根据状态改变行为时")
            .participants("State（状态）- 定义接口\nConcreteState（具体状态）- 实现状态行为\nContext（上下文）- 持有状态")
            .collaboration("Context持有State，根据情况切换ConcreteState。")
            .prosCons("优点：符合开闭原则、避免条件语句\n缺点：类数量增加")
            .relatedPatterns("相关模式：状态模式与策略模式相似")
            .springExample("Spring的StateMachine使用状态模式。")
            .jdkExample("1. java.lang.Thread.State - 线程状态")
            .realWorldExample("1. 订单状态流转\n2. 游戏角色状态")
            .mermaidDiagram("""
classDiagram
    class State {
        <<interface>>
        +insertCoin() void
        +ejectCoin() void
        +turnCrank() void
    }
    class NoCoinState {
        +insertCoin() void
        +ejectCoin() void
        +turnCrank() void
    }
    class HasCoinState {
        +insertCoin() void
        +ejectCoin() void
        +turnCrank() void
    }
    class SoldOutState {
        +insertCoin() void
        +ejectCoin() void
        +turnCrank() void
    }
    class VendingMachine {
        -State noCoinState
        -State hasCoinState
        -State soldOutState
        -State currentState
        +VendingMachine()
        +setState(State)
        +insertCoin() void
        +ejectCoin() void
        +turnCrank() void
    }

    State <|.. NoCoinState
    State <|.. HasCoinState
    State <|.. SoldOutState
    VendingMachine o-- State : uses
""")
            .codeExample("""
// 状态接口
interface State {
    void insertCoin();
    void ejectCoin();
    void turnCrank();
}

// 具体状态 - 没有硬币
class NoCoinState implements State {
    @Override
    public void insertCoin() {
        System.out.println("已投币");
    }

    @Override
    public void ejectCoin() {
        System.out.println("没有硬币，无法退币");
    }

    @Override
    public void turnCrank() {
        System.out.println("请先投币");
    }
}

// 具体状态 - 有硬币
class HasCoinState implements State {
    @Override
    public void insertCoin() {
        System.out.println("已有硬币");
    }

    @Override
    public void ejectCoin() {
        System.out.println("退币成功");
    }

    @Override
    public void turnCrank() {
        System.out.println("售货中");
    }
}

// 上下文类
class VendingMachine {
    private State noCoinState;
    private State hasCoinState;
    private State currentState;

    public VendingMachine() {
        noCoinState = new NoCoinState();
        hasCoinState = new HasCoinState();
        currentState = noCoinState;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void insertCoin() {
        currentState.insertCoin();
        if (currentState == noCoinState) {
            setState(hasCoinState);
        }
    }

    // ...其他方法
}

// 使用示例
VendingMachine machine = new VendingMachine();
machine.insertCoin();
machine.turnCrank();
""")
            .build();
        patterns.add(state);

        // 初始化访问者模式数据
        Pattern visitor = Pattern.builder()
            .id("visitor")
            .name("访问者模式")
            .nameEn("Visitor Pattern")
            .category(PatternCategory.BEHAVIORAL)
            .difficulty(PatternDifficulty.ADVANCED)
            .definition("表示一个作用于某对象结构中的各元素的操作。")
            .intent("表示一个作用于某对象结构中的各元素的操作。")
            .useCases("1. 对象结构包含很多类对象时\n2. 需要对对象进行不同且不相关的操作时")
            .participants("Visitor（访问者）- 声明操作接口\nElement（元素）- 定义accept方法")
            .collaboration("Element.accept(Visitor)，Visitor.visit(Element)。")
            .prosCons("优点：符合单一职责、易于扩展\n缺点：增加难度、元素难扩展")
            .relatedPatterns("相关模式：访问者模式与迭代器模式可以结合使用")
            .springExample("Spring的BeanDefinitionVisitor使用访问者模式。")
            .jdkExample("1. javax.lang.model.element.ElementVisitor")
            .realWorldExample("1. 编译器语法树\n2. 文档结构处理")
            .mermaidDiagram("""
classDiagram
    class Visitor {
        <<interface>>
        +visit(Book)
        +visit(Fruit)
    }
    class ShoppingCartVisitor {
        +visit(Book)
        +visit(Fruit)
    }
    class ItemElement {
        <<interface>>
        +accept(Visitor)
    }
    class Book {
        -String name
        -int price
        +Book(String, int)
        +accept(Visitor)
        +getName() String
        +getPrice() int
    }
    class Fruit {
        -String name
        -int price
        +Fruit(String, int)
        +accept(Visitor)
        +getName() String
        +getPrice() int
    }

    Visitor <|.. ShoppingCartVisitor
    ItemElement <|.. Book
    ItemElement <|.. Fruit
    Book --> Visitor : accepts
    Fruit --> Visitor : accepts
""")
            .codeExample("""
// 访问者接口
interface Visitor {
    void visit(Book book);
    void visit(Fruit fruit);
}

// 元素接口
interface ItemElement {
    void accept(Visitor visitor);
}

// 具体元素 - 书
class Book implements ItemElement {
    private String name;
    private int price;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// 具体元素 - 水果
class Fruit implements ItemElement {
    private String name;
    private int price;

    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// 具体访问者
class ShoppingCartVisitor implements Visitor {
    @Override
    public void visit(Book book) {
        System.out.println("书: " + book.getName() + ", ¥" + book.getPrice());
    }

    @Override
    public void visit(Fruit fruit) {
        System.out.println("水果: " + fruit.getName() + ", ¥" + fruit.getPrice());
    }
}

// 使用示例
ItemElement[] items = {
    new Book("设计模式", 89),
    new Fruit("苹果", 10)
};

Visitor visitor = new ShoppingCartVisitor();
for (ItemElement item : items) {
    item.accept(visitor);
}
""")
            .build();
        patterns.add(visitor);

        // 初始化解释器模式数据
        Pattern interpreter = Pattern.builder()
            .id("interpreter")
            .name("解释器模式")
            .nameEn("Interpreter Pattern")
            .category(PatternCategory.BEHAVIORAL)
            .difficulty(PatternDifficulty.ADVANCED)
            .definition("给定一个语言，定义它的文法的一种表示。")
            .intent("给定一个语言，定义它的文法的一种表示。")
            .useCases("1. 语言的文法比较简单时\n2. 效率不是关键问题时")
            .participants("Expression（表达式）- 声明解释接口\nTerminalExpression（终结符）- 实现解释\nContext（上下文）- 包含全局信息")
            .collaboration("Context解析表达式，Expression递归解释。")
            .prosCons("优点：易于实现、易于扩展\n缺点：效率较低、类数量多")
            .relatedPatterns("相关模式：解释器模式与组合模式相似")
            .springExample("Spring的SpEL使用解释器模式。")
            .jdkExample("1. java.text.SimpleDateFormat - 日期格式\n2. java.util.regex.Pattern")
            .realWorldExample("1. SQL解析器\n2. 正则表达式")
            .mermaidDiagram("""
classDiagram
    class Expression {
        <<interface>>
        +interpret() int
    }
    class NumberExpression {
        -int number
        +NumberExpression(int)
        +interpret() int
    }
    class AddExpression {
        -Expression left
        -Expression right
        +AddExpression(Expression, Expression)
        +interpret() int
    }
    class SubtractExpression {
        -Expression left
        -Expression right
        +SubtractExpression(Expression, Expression)
        +interpret() int
    }
    class Context {
        -Expression expression
        +parse(String)
        +calculate() int
    }
    class Client {
        +main()
    }

    Expression <|.. NumberExpression
    Expression <|.. AddExpression
    Expression <|.. SubtractExpression
    Context o-- Expression : interprets
    Client --> Context : uses
""")
            .codeExample("""
// 表达式接口
interface Expression {
    int interpret();
}

// 终结符表达式 - 数字
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

// 非终结符表达式 - 加法
class AddExpression implements Expression {
    private Expression left;
    private Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

// 非终结符表达式 - 减法
class SubtractExpression implements Expression {
    private Expression left;
    private Expression right;

    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() - right.interpret();
    }
}

// 上下文类
class Context {
    private Expression expression;

    public void parse(String formula) {
        // 解析表达式
        // 简化实现：解析 "5 + 3 - 2"
        String[] parts = formula.split(" ");
        Expression expr = new NumberExpression(Integer.parseInt(parts[0]));

        for (int i = 1; i < parts.length; i += 2) {
            String op = parts[i];
            int num = Integer.parseInt(parts[i + 1]);
            if (op.equals("+")) {
                expr = new AddExpression(expr, new NumberExpression(num));
            } else if (op.equals("-")) {
                expr = new SubtractExpression(expr, new NumberExpression(num));
            }
        }
        this.expression = expr;
    }

    public int calculate() {
        return expression.interpret();
    }
}

// 使用示例
Context context = new Context();
context.parse("5 + 3 - 2");
int result = context.calculate();
System.out.println("结果: " + result);
""")
            .build();
        patterns.add(interpreter);
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
