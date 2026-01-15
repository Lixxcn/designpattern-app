package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.state.VendingMachine;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 状态模式演示 ===\n\n");

        // 创建自动售货机
        VendingMachine machine = new VendingMachine();

        output.append("1. 投币：\n");
        machine.insertCoin();

        output.append("\n2. 退币：\n");
        machine.ejectCoin();

        output.append("\n3. 未投币时转动曲柄：\n");
        machine.turnCrank();

        output.append("\n4. 再次投币并购买：\n");
        machine.insertCoin();
        machine.turnCrank();

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 状态接口
interface State {
    void insertCoin();
    void ejectCoin();
    void turnCrank();
}

// 具体状态 - 没有硬币
class NoCoinState implements State {
    @Override
    public void insertCoin() {
        System.out.println("已投币");
    }

    @Override
    public void ejectCoin() {
        System.out.println("没有硬币，无法退币");
    }

    @Override
    public void turnCrank() {
        System.out.println("请先投币");
    }
}

// 具体状态 - 有硬币
class HasCoinState implements State {
    @Override
    public void insertCoin() {
        System.out.println("已有硬币");
    }

    @Override
    public void ejectCoin() {
        System.out.println("退币成功");
    }

    @Override
    public void turnCrank() {
        System.out.println("售货中");
    }
}

// 上下文类
class VendingMachine {
    private State noCoinState;
    private State hasCoinState;
    private State currentState;

    public VendingMachine() {
        noCoinState = new NoCoinState();
        hasCoinState = new HasCoinState();
        currentState = noCoinState;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void insertCoin() {
        currentState.insertCoin();
        if (currentState == noCoinState) {
            setState(hasCoinState);
        }
    }

    // ...其他方法
}

// 使用示例
VendingMachine machine = new VendingMachine();
machine.insertCoin();
machine.turnCrank();
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class State {
        <<interface>>
        +insertCoin() void
        +ejectCoin() void
        +turnCrank() void
    }
    class NoCoinState {
        +insertCoin() void
        +ejectCoin() void
        +turnCrank() void
    }
    class HasCoinState {
        +insertCoin() void
        +ejectCoin() void
        +turnCrank() void
    }
    class SoldOutState {
        +insertCoin() void
        +ejectCoin() void
        +turnCrank() void
    }
    class VendingMachine {
        -State noCoinState
        -State hasCoinState
        -State soldOutState
        -State currentState
        +VendingMachine()
        +setState(State)
        +insertCoin() void
        +ejectCoin() void
        +turnCrank() void
    }

    State <|.. NoCoinState
    State <|.. HasCoinState
    State <|.. SoldOutState
    VendingMachine o-- State : uses
""";
    }
}
