package cn.lixx.designpattern_app.service.pattern.creational.singleton;

/**
 * 单例模式客户端演示
 */
public class Client {
    public static String demonstrate() {
        StringBuilder output = new StringBuilder();

        output.append("=== 单例模式演示 ===\n\n");

        // 演示饿汉式
        output.append("1. 饿汉式单例：\n");
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        output.append("singleton1 == singleton2: ").append(singleton1 == singleton2).append("\n");
        singleton1.doSomething();

        output.append("\n2. 懒汉式单例：\n");
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        output.append("lazy1 == lazy2: ").append(lazy1 == lazy2).append("\n");

        output.append("\n3. 线程安全懒汉式：\n");
        ThreadSafeLazySingleton tsLazy1 = ThreadSafeLazySingleton.getInstance();
        ThreadSafeLazySingleton tsLazy2 = ThreadSafeLazySingleton.getInstance();
        output.append("tsLazy1 == tsLazy2: ").append(tsLazy1 == tsLazy2).append("\n");

        output.append("\n4. 静态内部类方式（推荐）：\n");
        StaticInnerClassSingleton inner1 = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton inner2 = StaticInnerClassSingleton.getInstance();
        output.append("inner1 == inner2: ").append(inner1 == inner2).append("\n");

        return output.toString();
    }
}
