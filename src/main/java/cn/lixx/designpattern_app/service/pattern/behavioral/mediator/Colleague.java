package cn.lixx.designpattern_app.service.pattern.behavioral.mediator;

/**
 * 同事抽象类
 */
public abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive(String message);

    public void send(String message) {
        mediator.sendMessage(message, this);
    }
}
