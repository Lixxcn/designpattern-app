package cn.lixx.designpattern_app.service.pattern.behavioral.chain;

/**
 * 处理者抽象类
 */
public abstract class Handler {
    protected Handler nextHandler;

    public Handler setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    public abstract void handleRequest(String request);
}
