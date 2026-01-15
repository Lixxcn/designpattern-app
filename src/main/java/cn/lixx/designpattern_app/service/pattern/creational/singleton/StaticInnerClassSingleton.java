package cn.lixx.designpattern_app.service.pattern.creational.singleton;

/**
 * 单例模式 - 静态内部类实现（推荐方式）
 * 优点：线程安全、延迟加载、代码简洁
 */
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {
        System.out.println("StaticInnerClassSingleton实例被创建");
    }

    private static class Holder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public void doSomething() {
        System.out.println("StaticInnerClassSingleton正在执行业务逻辑");
    }
}
