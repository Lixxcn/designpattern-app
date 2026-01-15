package cn.lixx.designpattern_app.service.pattern.behavioral.state;

/**
 * 具体状态 - 没有硬币
 */
public class NoCoinState implements State {
    @Override
    public void insertCoin() {
        System.out.println("  [状态] 已投币");
    }

    @Override
    public void ejectCoin() {
        System.out.println("  [状态] 没有硬币，无法退币");
    }

    @Override
    public void turnCrank() {
        System.out.println("  [状态] 请先投币");
    }
}
