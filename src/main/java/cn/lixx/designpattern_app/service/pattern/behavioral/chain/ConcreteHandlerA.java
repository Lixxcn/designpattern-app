package cn.lixx.designpattern_app.service.pattern.behavioral.chain;

/**
 * 具体处理者A - 处理普通请求
 */
public class ConcreteHandlerA extends Handler {

    @Override
    public void handleRequest(String request) {
        if (request.equals("普通请求")) {
            System.out.println("  [HandlerA] 处理普通请求");
        } else if (nextHandler != null) {
            System.out.println("  [HandlerA] 转发给下一个处理者");
            nextHandler.handleRequest(request);
        } else {
            System.out.println("  [HandlerA] 无法处理该请求");
        }
    }
}
