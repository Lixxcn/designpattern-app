package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

/**
 * 具体工厂2：创建产品族2
 */
public class ConcreteFactory2 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
