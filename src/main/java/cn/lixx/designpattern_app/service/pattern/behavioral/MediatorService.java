package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.mediator.*;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class MediatorService {

    private final CodeReaderUtil codeReaderUtil;

    public MediatorService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

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

    /**
     * 从 mediator 包读取所有示例代码
     */
    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.behavioral.mediator"
        );
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
