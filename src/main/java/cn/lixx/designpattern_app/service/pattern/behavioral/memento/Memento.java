package cn.lixx.designpattern_app.service.pattern.behavioral.memento;

/**
 * 备忘录类 - 存储状态
 */
public class Memento {
    private final String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
