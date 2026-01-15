package cn.lixx.designpattern_app.service.pattern.structural;

import cn.lixx.designpattern_app.service.pattern.structural.proxy.Proxy;
import cn.lixx.designpattern_app.service.pattern.structural.proxy.Subject;
import org.springframework.stereotype.Service;

@Service
public class ProxyService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 代理模式演示 ===\n\n");

        // 创建代理对象
        Subject proxy = new Proxy("大文件");

        output.append("1. 第一次请求（会创建真实对象）：\n");
        proxy.request();

        output.append("\n2. 第二次请求（使用缓存对象）：\n");
        proxy.request();

        output.append("\n3. 第三次请求（使用缓存对象）：\n");
        proxy.request();

        return output.toString();
    }

    public String getCodeExample() {
        return """
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
""";
    }

    public String getMermaidDiagram() {
        return """
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
""";
    }
}
