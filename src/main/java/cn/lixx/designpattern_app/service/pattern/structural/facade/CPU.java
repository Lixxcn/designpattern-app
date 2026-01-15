package cn.lixx.designpattern_app.service.pattern.structural.facade;

/**
 * 子系统A - CPU
 */
public class CPU {
    public void start() {
        System.out.println("  [CPU] 启动...");
    }

    public void execute() {
        System.out.println("  [CPU] 执行指令...");
    }

    public void shutdown() {
        System.out.println("  [CPU] 关闭...");
    }
}
