package cn.lixx.designpattern_app.service.pattern.creational.factorymethod;

/**
 * 具体工厂A，创建具体产品A
 */
public class ConcreteFactoryA implements Factory {
    @Override
    public Product createProduct() {
        System.out.println("具体工厂A创建具体产品A");
        return new ConcreteProductA();
    }
}
