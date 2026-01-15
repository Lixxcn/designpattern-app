package cn.lixx.designpattern_app.service.pattern.structural.decorator;

/**
 * 具体装饰器 - 奶泡
 */
public class WhipDecorator extends Decorator {
    public WhipDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        component.operation();
        System.out.print(" + 奶泡");
    }
}
