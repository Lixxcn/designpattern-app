package cn.lixx.designpattern_app.service.pattern.behavioral.observer;

/**
 * 具体观察者 - 订阅者
 */
public class ConcreteObserver implements Observer {
    private final String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("  [观察者-" + name + "] 收到新闻: " + message);
    }
}
