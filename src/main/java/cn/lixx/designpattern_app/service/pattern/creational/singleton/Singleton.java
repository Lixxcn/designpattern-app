package cn.lixx.designpattern_app.service.pattern.creational.singleton;

/**
 * 单例模式 - 饿汉式实现
 *
 * 意图：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 */
public class Singleton {
    // 静态变量，在类加载时就创建实例
    private static final Singleton INSTANCE = new Singleton();

    // 私有构造函数，防止外部创建实例
    private Singleton() {
        System.out.println("Singleton实例被创建");
    }

    // 提供全局访问点
    public static Singleton getInstance() {
        return INSTANCE;
    }

    // 业务方法示例
    public void doSomething() {
        System.out.println("Singleton正在执行业务逻辑");
    }
}
