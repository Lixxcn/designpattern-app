package cn.lixx.designpattern_app.service.pattern.creational.singleton;

/**
 * 单例模式 - 懒汉式实现（双重检查锁，线程安全）
 */
public class ThreadSafeLazySingleton {
    private static volatile ThreadSafeLazySingleton instance;

    private ThreadSafeLazySingleton() {
        System.out.println("ThreadSafeLazySingleton实例被创建");
    }

    public static ThreadSafeLazySingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeLazySingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeLazySingleton();
                }
            }
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("ThreadSafeLazySingleton正在执行业务逻辑");
    }
}
