package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.interpreter.Context;
import org.springframework.stereotype.Service;

@Service
public class InterpreterService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 解释器模式演示 ===\n\n");

        // 创建上下文
        Context context = new Context();

        // 解析并计算表达式
        output.append("1. 计算 5 + 3 = ");
        context.parse("5 + 3");
        output.append(context.calculate()).append("\n");

        output.append("2. 计算 10 - 4 = ");
        context.parse("10 - 4");
        output.append(context.calculate()).append("\n");

        output.append("3. 计算 5 + 3 - 2 = ");
        context.parse("5 + 3 - 2");
        output.append(context.calculate()).append("\n");

        output.append("4. 计算 10 + 5 - 3 + 2 = ");
        context.parse("10 + 5 - 3 + 2");
        output.append(context.calculate());

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 表达式接口
interface Expression {
    int interpret();
}

// 终结符表达式 - 数字
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

// 非终结符表达式 - 加法
class AddExpression implements Expression {
    private Expression left;
    private Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

// 非终结符表达式 - 减法
class SubtractExpression implements Expression {
    private Expression left;
    private Expression right;

    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() - right.interpret();
    }
}

// 上下文类
class Context {
    private Expression expression;

    public void parse(String formula) {
        // 解析表达式
        // 简化实现：解析 "5 + 3 - 2"
        String[] parts = formula.split(" ");
        Expression expr = new NumberExpression(Integer.parseInt(parts[0]));

        for (int i = 1; i < parts.length; i += 2) {
            String op = parts[i];
            int num = Integer.parseInt(parts[i + 1]);
            if (op.equals("+")) {
                expr = new AddExpression(expr, new NumberExpression(num));
            } else if (op.equals("-")) {
                expr = new SubtractExpression(expr, new NumberExpression(num));
            }
        }
        this.expression = expr;
    }

    public int calculate() {
        return expression.interpret();
    }
}

// 使用示例
Context context = new Context();
context.parse("5 + 3 - 2");
int result = context.calculate();
System.out.println("结果: " + result);
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Expression {
        <<interface>>
        +interpret() int
    }
    class NumberExpression {
        -int number
        +NumberExpression(int)
        +interpret() int
    }
    class AddExpression {
        -Expression left
        -Expression right
        +AddExpression(Expression, Expression)
        +interpret() int
    }
    class SubtractExpression {
        -Expression left
        -Expression right
        +SubtractExpression(Expression, Expression)
        +interpret() int
    }
    class Context {
        -Expression expression
        +parse(String)
        +calculate() int
    }
    class Client {
        +main()
    }

    Expression <|.. NumberExpression
    Expression <|.. AddExpression
    Expression <|.. SubtractExpression
    Context o-- Expression : interprets
    Client --> Context : uses
""";
    }
}
