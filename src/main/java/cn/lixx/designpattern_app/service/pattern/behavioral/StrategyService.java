package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.strategy.*;
import org.springframework.stereotype.Service;

@Service
public class StrategyService {

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

    public String getCodeExample() {
        return """
// 策略接口
interface Strategy {
    int execute(int a, int b);
}

// 具体策略 - 加法
class AddStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a + b;
    }
}

// 具体策略 - 减法
class SubtractStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a - b;
    }
}

// 上下文类
class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}

// 使用示例
Context context = new Context(new AddStrategy());
int result = context.executeStrategy(10, 5);  // 15

context.setStrategy(new SubtractStrategy());
result = context.executeStrategy(10, 5);  // 5
""";
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
