package cn.lixx.designpattern_app.service.pattern.structural.decorator;

/**
 * 具体装饰器 - 牛奶
 */
public class MilkDecorator extends Decorator {
    public MilkDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        component.operation();
        System.out.print(" + 牛奶");
    }
}
