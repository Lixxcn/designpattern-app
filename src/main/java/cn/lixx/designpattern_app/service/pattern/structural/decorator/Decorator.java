package cn.lixx.designpattern_app.service.pattern.structural.decorator;

/**
 * 装饰器抽象类
 */
public abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
