package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.memento.*;
import org.springframework.stereotype.Service;

@Service
public class MementoService {

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

    public String getCodeExample() {
        return """
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
""";
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
