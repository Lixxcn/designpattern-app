package cn.lixx.designpattern_app.service.pattern.structural.bridge;

/**
 * 具体实现化 - 蓝色
 */
public class BlueColor implements Color {
    @Override
    public String apply() {
        return "蓝色";
    }
}
