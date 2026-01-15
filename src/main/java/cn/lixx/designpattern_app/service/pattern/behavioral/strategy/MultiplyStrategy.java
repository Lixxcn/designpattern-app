package cn.lixx.designpattern_app.service.pattern.behavioral.strategy;

/**
 * 具体策略 - 乘法
 */
public class MultiplyStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a * b;
    }
}
