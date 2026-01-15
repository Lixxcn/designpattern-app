package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.strategy.*;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class StrategyService {

    private final CodeReaderUtil codeReaderUtil;

    public StrategyService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 策略模式演示 ===\n\n");

        // 创建上下文，使用加法策略
        output.append("1. 使用加法策略：10 + 5 = ");
        Context context = new Context(new AddStrategy());
        int result = context.executeStrategy(10, 5);
        output.append(result).append("\n");

        // 切换到减法策略
        output.append("2. 切换到减法策略：10 - 5 = ");
        context.setStrategy(new SubtractStrategy());
        result = context.executeStrategy(10, 5);
        output.append(result).append("\n");

        // 切换到乘法策略
        output.append("3. 切换到乘法策略：10 * 5 = ");
        context.setStrategy(new MultiplyStrategy());
        result = context.executeStrategy(10, 5);
        output.append(result).append("\n");

        return output.toString();
    }

    /**
     * 从 strategy 包读取所有示例代码
     */
    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.behavioral.strategy"
        );
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Strategy {
        <<interface>>
        +execute(int, int) int
    }
    class AddStrategy {
        +execute(int, int) int
    }
    class SubtractStrategy {
        +execute(int, int) int
    }
    class MultiplyStrategy {
        +execute(int, int) int
    }
    class Context {
        -Strategy strategy
        +Context(Strategy)
        +setStrategy(Strategy)
        +executeStrategy(int, int) int
    }
    class Client {
        +main()
    }

    Strategy <|.. AddStrategy
    Strategy <|.. SubtractStrategy
    Strategy <|.. MultiplyStrategy
    Context o-- Strategy : uses
    Client --> Context : uses
""";
    }
}
