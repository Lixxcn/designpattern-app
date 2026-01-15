package cn.lixx.designpattern_app.service.pattern.structural;

import cn.lixx.designpattern_app.service.pattern.structural.flyweight.Flyweight;
import cn.lixx.designpattern_app.service.pattern.structural.flyweight.FlyweightFactory;
import org.springframework.stereotype.Service;

@Service
public class FlyweightService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 享元模式演示 ===\n\n");

        // 创建享元工厂
        FlyweightFactory factory = new FlyweightFactory();

        output.append("1. 获取享元对象：\n");
        Flyweight fw1 = factory.getFlyweight("A");
        fw1.operation("状态1");

        output.append("\n2. 再次获取相同的享元对象：\n");
        Flyweight fw2 = factory.getFlyweight("A");
        fw2.operation("状态2");

        output.append("\n3. 获取不同的享元对象：\n");
        Flyweight fw3 = factory.getFlyweight("B");
        fw3.operation("状态3");

        output.append("\n4. 共享享元总数: ").append(factory.getCount());

        return output.toString();
    }

    public String getCodeExample() {
        return """
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
""";
    }

    public String getMermaidDiagram() {
        return """
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
""";
    }
}
