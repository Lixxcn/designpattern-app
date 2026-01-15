package cn.lixx.designpattern_app.service.pattern.structural;

import cn.lixx.designpattern_app.service.pattern.structural.adapter.Adapter;
import cn.lixx.designpattern_app.service.pattern.structural.adapter.Adaptee;
import cn.lixx.designpattern_app.service.pattern.structural.adapter.Target;
import org.springframework.stereotype.Service;

@Service
public class AdapterService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 适配器模式演示 ===\n\n");

        // 创建被适配者对象
        Adaptee adaptee = new Adaptee();

        // 创建适配器，将被适配者包装
        Target target = new Adapter(adaptee);

        // 通过目标接口调用
        output.append("通过适配器调用被适配者的方法：\n");
        target.request();

        return output.toString();
    }

    public String getCodeExample() {
        return """
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
""";
    }

    public String getMermaidDiagram() {
        return """
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
""";
    }
}
