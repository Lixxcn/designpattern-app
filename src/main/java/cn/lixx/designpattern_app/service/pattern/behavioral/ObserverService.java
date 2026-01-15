package cn.lixx.designpattern_app.service.pattern.behavioral;
import cn.lixx.designpattern_app.model.CodeFile;
import java.util.List;

import cn.lixx.designpattern_app.service.pattern.behavioral.observer.ConcreteObserver;
import cn.lixx.designpattern_app.service.pattern.behavioral.observer.ConcreteSubject;
import cn.lixx.designpattern_app.service.pattern.behavioral.observer.Observer;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class ObserverService {

    private final CodeReaderUtil codeReaderUtil;

    public ObserverService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 观察者模式演示 ===\n\n");

        // 创建主题
        ConcreteSubject newsPublisher = new ConcreteSubject();

        // 创建观察者
        Observer subscriber1 = new ConcreteObserver("张三");
        Observer subscriber2 = new ConcreteObserver("李四");
        Observer subscriber3 = new ConcreteObserver("王五");

        // 订阅主题
        output.append("1. 添加订阅者：\n");
        newsPublisher.attach(subscriber1);
        newsPublisher.attach(subscriber2);
        newsPublisher.attach(subscriber3);

        // 发布消息
        output.append("\n2. 发布第一条新闻：\n");
        newsPublisher.setState("Spring Boot 3.5发布！");

        // 取消订阅
        output.append("\n3. 李四取消订阅：\n");
        newsPublisher.detach(subscriber2);

        // 再次发布消息
        output.append("\n4. 发布第二条新闻：\n");
        newsPublisher.setState("Java 22正式发布！");

        return output.toString();
    }

    /**
     * 从 observer 包读取所有示例代码
     */
    public List<CodeFile> getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.behavioral.observer"
        );
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Observer {
        <<interface>>
        +update(String) void
    }
    class ConcreteObserver {
        -String name
        +ConcreteObserver(String)
        +update(String) void
    }
    class Subject {
        <<abstract>>
        #List~Observer~ observers
        +attach(Observer)
        +detach(Observer)
        #notifyObservers(String)
    }
    class ConcreteSubject {
        -String state
        +setState(String)
        +getState() String
    }

    Observer <|.. ConcreteObserver
    Subject <|.. ConcreteSubject
    Subject o-- Observer : notifies
""";
    }
}
