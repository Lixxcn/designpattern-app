package cn.lixx.designpattern_app.service.pattern.behavioral.observer;

/**
 * 具体主题 - 新闻发布者
 */
public class ConcreteSubject extends Subject {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        System.out.println("\n[主题] 发布新闻: " + state);
        notifyObservers(state);
    }
}
