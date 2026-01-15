package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.memento.*;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class MementoService {

    private final CodeReaderUtil codeReaderUtil;

    public MementoService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 备忘录模式演示 ===\n\n");

        // 创建发起者和管理者
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        output.append("1. 设置初始状态：\n");
        originator.setState("状态1");
        caretaker.add(originator.save());

        output.append("\n2. 修改状态：\n");
        originator.setState("状态2");
        caretaker.add(originator.save());

        output.append("\n3. 再次修改状态：\n");
        originator.setState("状态3");

        output.append("\n4. 恢复到第一个状态：\n");
        originator.restore(caretaker.get(0));

        output.append("\n5. 恢复到第二个状态：\n");
        originator.restore(caretaker.get(1));

        return output.toString();
    }

    /**
     * 从 memento 包读取所有示例代码
     */
    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.behavioral.memento"
        );
    }

    public String getMermaidDiagram() {
        return """
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
""";
    }
}
