package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.mediator.*;
import org.springframework.stereotype.Service;

@Service
public class MediatorService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 中介者模式演示 ===\n\n");

        // 创建中介者
        ConcreteMediator mediator = new ConcreteMediator();

        // 创建同事
        ConcreteColleague1 colleague1 = new ConcreteColleague1(mediator);
        ConcreteColleague2 colleague2 = new ConcreteColleague2(mediator);

        // 注册同事到中介者
        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);

        output.append("1. 同事1发送消息：\n");
        colleague1.send("你好，同事2！");

        output.append("\n2. 同事2发送消息：\n");
        colleague2.send("你好，同事1！");

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 中介者接口
interface Mediator {
    void sendMessage(String message, Colleague colleague);
}

// 同事抽象类
abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive(String message);

    public void send(String message) {
        mediator.sendMessage(message, this);
    }
}

// 具体中介者
class ConcreteMediator implements Mediator {
    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;

    public void setColleague1(ConcreteColleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(ConcreteColleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    @Override
    public void sendMessage(String message, Colleague colleague) {
        if (colleague == colleague1) {
            colleague2.receive(message);
        } else {
            colleague1.receive(message);
        }
    }
}

// 具体同事
class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void receive(String message) {
        System.out.println("同事1收到: " + message);
    }
}

// 使用示例
ConcreteMediator mediator = new ConcreteMediator();
ConcreteColleague1 c1 = new ConcreteColleague1(mediator);
ConcreteColleague2 c2 = new ConcreteColleague2(mediator);

mediator.setColleague1(c1);
mediator.setColleague2(c2);

c1.send("你好");
c2.send("你好");
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Mediator {
        <<interface>>
        +sendMessage(String, Colleague)
    }
    class Colleague {
        <<abstract>>
        #Mediator mediator
        +Colleague(Mediator)
        +receive(String)
        +send(String)
    }
    class ConcreteMediator {
        -ConcreteColleague1 colleague1
        -ConcreteColleague2 colleague2
        +setColleague1(ConcreteColleague1)
        +setColleague2(ConcreteColleague2)
        +sendMessage(String, Colleague)
    }
    class ConcreteColleague1 {
        +ConcreteColleague1(Mediator)
        +receive(String)
    }
    class ConcreteColleague2 {
        +ConcreteColleague2(Mediator)
        +receive(String)
    }

    Mediator <|.. ConcreteMediator
    Colleague <|-- ConcreteColleague1
    Colleague <|-- ConcreteColleague2
    Colleague o-- Mediator : uses
    ConcreteMediator --> ConcreteColleague1 : coordinates
    ConcreteMediator --> ConcreteColleague2 : coordinates
""";
    }
}
