package cn.lixx.designpattern_app.service.pattern.behavioral.strategy;

/**
 * 具体策略 - 加法
 */
public class AddStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a + b;
    }
}
