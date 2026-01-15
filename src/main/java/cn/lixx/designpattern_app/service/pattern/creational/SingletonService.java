package cn.lixx.designpattern_app.service.pattern.creational;

import cn.lixx.designpattern_app.service.pattern.creational.singleton.Client;
import org.springframework.stereotype.Service;

@Service
public class SingletonService {

    public String executeExample() {
        return Client.demonstrate();
    }

    public String getCodeExample() {
        return """
// 单例模式 - 饿汉式实现
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }
}

// 单例模式 - 懒汉式实现（线程不安全）
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

// 单例模式 - 双重检查锁（线程安全）
public class ThreadSafeLazySingleton {
    private static volatile ThreadSafeLazySingleton instance;

    private ThreadSafeLazySingleton() {}

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
}

// 单例模式 - 静态内部类（推荐）
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {}

    private static class Holder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Singleton {
        -static Singleton instance
        -Singleton()
        +static Singleton getInstance()
        +void doSomething()
    }
    class Client {
        +void main()
    }
    Client --> Singleton : uses
    Singleton ..> Singleton : creates instance
""";
    }
}
