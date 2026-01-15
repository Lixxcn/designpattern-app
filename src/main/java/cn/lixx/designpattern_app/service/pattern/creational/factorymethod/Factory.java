package cn.lixx.designpattern_app.service.pattern.creational.factorymethod;

/**
 * 工厂接口，声明工厂方法
 */
public interface Factory {
    /**
     * 工厂方法：创建产品对象
     */
    Product createProduct();
}
