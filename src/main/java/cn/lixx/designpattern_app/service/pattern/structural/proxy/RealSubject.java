package cn.lixx.designpattern_app.service.pattern.structural.proxy;

/**
 * 真实主题 - 实际执行的对象
 */
public class RealSubject implements Subject {
    private final String name;

    public RealSubject(String name) {
        this.name = name;
        // 模拟耗时初始化
        loadFromDatabase();
    }

    private void loadFromDatabase() {
        System.out.println("  [" + name + "] 从数据库加载数据...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void request() {
        System.out.println("  [" + name + "] 执行真实请求");
    }
}
