package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

/**
 * 具体工厂1：创建产品族1
 */
public class ConcreteFactory1 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
