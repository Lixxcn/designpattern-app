package cn.lixx.designpattern_app.service.pattern.behavioral.mediator;

/**
 * 具体同事2
 */
public class ConcreteColleague2 extends Colleague {
    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void receive(String message) {
        System.out.println("  [同事2] 收到消息: " + message);
    }
}
