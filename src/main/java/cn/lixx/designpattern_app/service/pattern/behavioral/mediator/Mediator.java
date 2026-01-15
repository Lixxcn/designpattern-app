package cn.lixx.designpattern_app.service.pattern.behavioral.mediator;

/**
 * 中介者接口
 */
public interface Mediator {
    void sendMessage(String message, Colleague colleague);
}
