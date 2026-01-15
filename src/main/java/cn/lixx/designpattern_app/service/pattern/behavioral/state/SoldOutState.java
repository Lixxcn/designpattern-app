package cn.lixx.designpattern_app.service.pattern.behavioral.state;

/**
 * 具体状态 - 售罄
 */
public class SoldOutState implements State {
    @Override
    public void insertCoin() {
        System.out.println("  [状态] 售罄，不能投币");
    }

    @Override
    public void ejectCoin() {
        System.out.println("  [状态] 没有硬币，无法退币");
    }

    @Override
    public void turnCrank() {
        System.out.println("  [状态] 售罄，无法购买");
    }
}
