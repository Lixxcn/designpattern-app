package cn.lixx.designpattern_app.service.pattern.behavioral.chain;

/**
 * 具体处理者C - 处理紧急请求
 */
public class ConcreteHandlerC extends Handler {

    @Override
    public void handleRequest(String request) {
        if (request.equals("紧急请求")) {
            System.out.println("  [HandlerC] 处理紧急请求");
        } else {
            System.out.println("  [HandlerC] 无法处理该请求");
        }
    }
}
