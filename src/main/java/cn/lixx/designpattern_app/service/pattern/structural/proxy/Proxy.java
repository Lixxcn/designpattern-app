package cn.lixx.designpattern_app.service.pattern.structural.proxy;

/**
 * 代理类 - 控制对真实主题的访问
 */
public class Proxy implements Subject {
    private RealSubject realSubject;
    private final String name;

    public Proxy(String name) {
        this.name = name;
    }

    @Override
    public void request() {
        // 延迟初始化：只有在真正需要时才创建真实对象
        if (realSubject == null) {
            System.out.println("  [代理] 首次访问，创建真实对象...");
            realSubject = new RealSubject(name);
        } else {
            System.out.println("  [代理] 使用缓存的真实对象");
        }

        // 可以在调用前后添加额外功能
        System.out.println("  [代理] 请求前检查权限...");
        realSubject.request();
        System.out.println("  [代理] 请求后记录日志...");
    }
}
