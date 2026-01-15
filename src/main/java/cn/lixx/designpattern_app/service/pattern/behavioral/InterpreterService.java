package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.interpreter.Context;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class InterpreterService {

    private final CodeReaderUtil codeReaderUtil;

    public InterpreterService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

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

    /**
     * 从 interpreter 包读取所有示例代码
     */
    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.behavioral.interpreter"
        );
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
