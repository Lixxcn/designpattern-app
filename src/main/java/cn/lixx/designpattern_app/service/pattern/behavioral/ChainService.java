package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.chain.*;
import org.springframework.stereotype.Service;

@Service
public class ChainService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 责任链模式演示 ===\n\n");

        // 创建处理者
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        Handler handlerC = new ConcreteHandlerC();

        // 设置责任链：A -> B -> C
        output.append("1. 设置责任链: A -> B -> C\n");
        handlerA.setNext(handlerB).setNext(handlerC);

        // 测试不同请求
        output.append("\n2. 处理普通请求：\n");
        handlerA.handleRequest("普通请求");

        output.append("\n3. 处理重要请求：\n");
        handlerA.handleRequest("重要请求");

        output.append("\n4. 处理紧急请求：\n");
        handlerA.handleRequest("紧急请求");

        output.append("\n5. 处理未知请求：\n");
        handlerA.handleRequest("未知请求");

        return output.toString();
    }

    public String getCodeExample() {
        return """
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
""";
    }

    public String getMermaidDiagram() {
        return """
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
""";
    }
}
