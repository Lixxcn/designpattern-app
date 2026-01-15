package cn.lixx.designpattern_app.service.pattern.structural;

import cn.lixx.designpattern_app.service.pattern.structural.adapter.Adapter;
import cn.lixx.designpattern_app.service.pattern.structural.adapter.Adaptee;
import cn.lixx.designpattern_app.service.pattern.structural.adapter.Target;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class AdapterService {

    private final CodeReaderUtil codeReaderUtil;

    public AdapterService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

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

    /**
     * 从 adapter 包读取所有示例代码
     */
    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.structural.adapter"
        );
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
