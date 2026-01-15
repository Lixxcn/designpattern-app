package cn.lixx.designpattern_app.service.pattern.creational.factorymethod;

/**
 * 具体产品A
 */
public class ConcreteProductA implements Product {
    @Override
    public void use() {
        System.out.println("使用具体产品A");
    }
}
