package cn.lixx.designpattern_app.service.pattern.structural;
import cn.lixx.designpattern_app.model.CodeFile;
import java.util.List;

import cn.lixx.designpattern_app.service.pattern.structural.proxy.Proxy;
import cn.lixx.designpattern_app.service.pattern.structural.proxy.Subject;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class ProxyService {

    private final CodeReaderUtil codeReaderUtil;

    public ProxyService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

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

    /**
     * 从 proxy 包读取所有示例代码
     */
    public List<CodeFile> getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.structural.proxy"
        );
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
