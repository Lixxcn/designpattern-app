package cn.lixx.designpattern_app.service.pattern.creational.abstractfactory;

/**
 * 抽象工厂接口：声明创建一系列相关或依赖对象的接口
 */
public interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}
