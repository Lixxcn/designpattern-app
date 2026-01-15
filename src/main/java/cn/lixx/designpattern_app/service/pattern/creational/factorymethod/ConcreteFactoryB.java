package cn.lixx.designpattern_app.service.pattern.creational.factorymethod;

/**
 * 具体工厂B，创建具体产品B
 */
public class ConcreteFactoryB implements Factory {
    @Override
    public Product createProduct() {
        System.out.println("具体工厂B创建具体产品B");
        return new ConcreteProductB();
    }
}
