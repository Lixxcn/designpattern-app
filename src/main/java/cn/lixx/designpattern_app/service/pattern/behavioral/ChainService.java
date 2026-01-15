package cn.lixx.designpattern_app.service.pattern.behavioral;
import cn.lixx.designpattern_app.model.CodeFile;
import java.util.List;

import cn.lixx.designpattern_app.service.pattern.behavioral.chain.*;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class ChainService {

    private final CodeReaderUtil codeReaderUtil;

    public ChainService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

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

    /**
     * 从 chain 包读取所有示例代码
     */
    public List<CodeFile> getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.behavioral.chain"
        );
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
