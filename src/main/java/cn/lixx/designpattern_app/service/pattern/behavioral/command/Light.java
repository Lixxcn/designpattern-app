package cn.lixx.designpattern_app.service.pattern.behavioral.command;

/**
 * 接收者 - 电灯
 */
public class Light {
    public void on() {
        System.out.println("  [电灯] 开灯");
    }

    public void off() {
        System.out.println("  [电灯] 关灯");
    }
}
