package cn.lixx.designpattern_app.service.pattern.structural.bridge;

/**
 * 具体实现化 - 红色
 */
public class RedColor implements Color {
    @Override
    public String apply() {
        return "红色";
    }
}
