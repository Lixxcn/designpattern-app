package cn.lixx.designpattern_app.service.pattern.structural.bridge;

/**
 * 具体实现化 - 绿色
 */
public class GreenColor implements Color {
    @Override
    public String apply() {
        return "绿色";
    }
}
