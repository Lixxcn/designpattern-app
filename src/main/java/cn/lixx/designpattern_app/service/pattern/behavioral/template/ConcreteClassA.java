package cn.lixx.designpattern_app.service.pattern.behavioral.template;

/**
 * 具体类A - 实现A
 */
public class ConcreteClassA extends AbstractClass {

    @Override
    protected void primitiveOperation1() {
        System.out.println("  [ConcreteClassA] 步骤1: 准备原材料");
    }

    @Override
    protected void primitiveOperation2() {
        System.out.println("  [ConcreteClassA] 步骤2: 加工处理");
    }

    @Override
    protected void primitiveOperation3() {
        System.out.println("  [ConcreteClassA] 步骤3: 包装成品");
    }
}
