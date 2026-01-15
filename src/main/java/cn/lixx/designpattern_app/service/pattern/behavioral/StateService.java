package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.state.VendingMachine;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    private final CodeReaderUtil codeReaderUtil;

    public StateService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

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

    /**
     * 从 state 包读取所有示例代码
     */
    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.behavioral.state"
        );
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
