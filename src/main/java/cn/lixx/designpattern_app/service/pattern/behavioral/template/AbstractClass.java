package cn.lixx.designpattern_app.service.pattern.behavioral.template;

/**
 * 抽象类 - 定义模板方法
 */
public abstract class AbstractClass {
    /**
     * 模板方法 - 定义算法骨架
     */
    public final void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
        primitiveOperation3();
    }

    /**
     * 基本方法 - 由子类实现
     */
    protected abstract void primitiveOperation1();

    /**
     * 基本方法 - 由子类实现
     */
    protected abstract void primitiveOperation2();

    /**
     * 基本方法 - 由子类实现
     */
    protected abstract void primitiveOperation3();
}
