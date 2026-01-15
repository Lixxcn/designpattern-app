package cn.lixx.designpattern_app.service.pattern.behavioral.interpreter;

/**
 * 上下文类 - 解析和计算表达式
 */
public class Context {
    private Expression expression;

    public void parse(String formula) {
        // 简化版解析：解析 "5 + 3 - 2" 格式
        String[] parts = formula.split(" ");

        Expression expr = new NumberExpression(Integer.parseInt(parts[0]));

        for (int i = 1; i < parts.length; i += 2) {
            String operator = parts[i];
            int number = Integer.parseInt(parts[i + 1]);

            if (operator.equals("+")) {
                expr = new AddExpression(expr, new NumberExpression(number));
            } else if (operator.equals("-")) {
                expr = new SubtractExpression(expr, new NumberExpression(number));
            }
        }

        this.expression = expr;
    }

    public int calculate() {
        return expression.interpret();
    }
}
