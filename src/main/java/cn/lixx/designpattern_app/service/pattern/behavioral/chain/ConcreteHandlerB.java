package cn.lixx.designpattern_app.service.pattern.behavioral.chain;

/**
 * 具体处理者B - 处理重要请求
 */
public class ConcreteHandlerB extends Handler {

    @Override
    public void handleRequest(String request) {
        if (request.equals("重要请求")) {
            System.out.println("  [HandlerB] 处理重要请求");
        } else if (nextHandler != null) {
            System.out.println("  [HandlerB] 转发给下一个处理者");
            nextHandler.handleRequest(request);
        } else {
            System.out.println("  [HandlerB] 无法处理该请求");
        }
    }
}
