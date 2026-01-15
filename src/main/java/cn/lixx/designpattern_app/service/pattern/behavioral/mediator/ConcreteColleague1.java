package cn.lixx.designpattern_app.service.pattern.behavioral.mediator;

/**
 * 具体同事1
 */
public class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void receive(String message) {
        System.out.println("  [同事1] 收到消息: " + message);
    }
}
