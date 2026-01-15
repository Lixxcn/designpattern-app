package cn.lixx.designpattern_app.service.pattern.behavioral.state;

/**
 * 上下文类 - 自动售货机
 */
public class VendingMachine {
    private State noCoinState;
    private State hasCoinState;
    private State soldOutState;
    private State currentState;

    public VendingMachine() {
        noCoinState = new NoCoinState();
        hasCoinState = new HasCoinState();
        soldOutState = new SoldOutState();
        currentState = noCoinState;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public State getNoCoinState() {
        return noCoinState;
    }

    public State getHasCoinState() {
        return hasCoinState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public void insertCoin() {
        currentState.insertCoin();
        if (currentState == noCoinState) {
            setState(hasCoinState);
        }
    }

    public void ejectCoin() {
        currentState.ejectCoin();
        if (currentState == hasCoinState) {
            setState(noCoinState);
        }
    }

    public void turnCrank() {
        currentState.turnCrank();
        if (currentState == hasCoinState) {
            setState(noCoinState);
        }
    }
}
