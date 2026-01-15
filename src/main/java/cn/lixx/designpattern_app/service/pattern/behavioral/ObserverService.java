package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.observer.ConcreteObserver;
import cn.lixx.designpattern_app.service.pattern.behavioral.observer.ConcreteSubject;
import cn.lixx.designpattern_app.service.pattern.behavioral.observer.Observer;
import org.springframework.stereotype.Service;

@Service
public class ObserverService {

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

    public String getCodeExample() {
        return """
// 观察者接口
interface Observer {
    void update(String message);
}

// 主题抽象类
abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// 具体主题
class ConcreteSubject extends Subject {
    private String state;

    public void setState(String state) {
        this.state = state;
        notifyObservers(state);
    }
}

// 具体观察者
class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " 收到: " + message);
    }
}

// 使用示例
ConcreteSubject subject = new ConcreteSubject();
Observer observer1 = new ConcreteObserver("张三");
Observer observer2 = new ConcreteObserver("李四");

subject.attach(observer1);
subject.attach(observer2);
subject.setState("新消息");
""";
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
