package cn.lixx.designpattern_app.service.pattern.behavioral.template;

/**
 * 具体类B - 实现B
 */
public class ConcreteClassB extends AbstractClass {

    @Override
    protected void primitiveOperation1() {
        System.out.println("  [ConcreteClassB] 步骤1: 数据采集");
    }

    @Override
    protected void primitiveOperation2() {
        System.out.println("  [ConcreteClassB] 步骤2: 数据分析");
    }

    @Override
    protected void primitiveOperation3() {
        System.out.println("  [ConcreteClassB] 步骤3: 生成报告");
    }
}
