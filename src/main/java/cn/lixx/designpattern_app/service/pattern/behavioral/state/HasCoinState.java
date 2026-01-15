package cn.lixx.designpattern_app.service.pattern.behavioral.state;

/**
 * 具体状态 - 有硬币
 */
public class HasCoinState implements State {
    @Override
    public void insertCoin() {
        System.out.println("  [状态] 已有硬币，不能重复投币");
    }

    @Override
    public void ejectCoin() {
        System.out.println("  [状态] 退币成功");
    }

    @Override
    public void turnCrank() {
        System.out.println("  [状态] 售货中...");
    }
}
