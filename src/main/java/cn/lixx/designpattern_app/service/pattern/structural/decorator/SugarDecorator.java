package cn.lixx.designpattern_app.service.pattern.structural.decorator;

/**
 * 具体装饰器 - 糖
 */
public class SugarDecorator extends Decorator {
    public SugarDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        component.operation();
        System.out.print(" + 糖");
    }
}
