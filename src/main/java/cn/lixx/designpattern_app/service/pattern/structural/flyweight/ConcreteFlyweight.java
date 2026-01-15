package cn.lixx.designpattern_app.service.pattern.structural.flyweight;

/**
 * 具体享元 - 共享的内部状态
 */
public class ConcreteFlyweight implements Flyweight {
    private final String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(String extrinsicState) {
        System.out.println("  内部状态: " + intrinsicState + ", 外部状态: " + extrinsicState);
    }
}
