package cn.lixx.designpattern_app.service.pattern.structural.adapter;

/**
 * 适配器类 - 将Adaptee适配到Target接口
 */
public class Adapter implements Target {
    private final Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        System.out.println("适配器转换调用：");
        adaptee.specificRequest();
    }
}
