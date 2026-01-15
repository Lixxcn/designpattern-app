package cn.lixx.designpattern_app.service.pattern.behavioral.state;

/**
 * 状态接口
 */
public interface State {
    void insertCoin();
    void ejectCoin();
    void turnCrank();
}
