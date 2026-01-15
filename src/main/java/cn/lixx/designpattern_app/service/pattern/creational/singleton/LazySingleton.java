package cn.lixx.designpattern_app.service.pattern.creational.singleton;

/**
 * 单例模式 - 懒汉式实现（线程不安全）
 */
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("LazySingleton实例被创建");
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("LazySingleton正在执行业务逻辑");
    }
}
