package cn.lixx.designpattern_app.service.pattern.behavioral;
import cn.lixx.designpattern_app.model.CodeFile;
import java.util.List;

import cn.lixx.designpattern_app.service.pattern.behavioral.template.AbstractClass;
import cn.lixx.designpattern_app.service.pattern.behavioral.template.ConcreteClassA;
import cn.lixx.designpattern_app.service.pattern.behavioral.template.ConcreteClassB;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    private final CodeReaderUtil codeReaderUtil;

    public TemplateService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 模板方法模式演示 ===\n\n");

        // 具体类A
        output.append("1. 使用ConcreteClassA：\n");
        AbstractClass classA = new ConcreteClassA();
        classA.templateMethod();

        output.append("\n2. 使用ConcreteClassB：\n");
        AbstractClass classB = new ConcreteClassB();
        classB.templateMethod();

        return output.toString();
    }

    /**
     * 从 template 包读取所有示例代码
     */
    public List<CodeFile> getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.behavioral.template"
        );
    }

    public String getMermaidDiagram() {
        return """
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
""";
    }
}
