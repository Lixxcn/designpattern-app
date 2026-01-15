package cn.lixx.designpattern_app.service.pattern.structural.facade;

/**
 * 子系统C - 硬盘
 */
public class HardDrive {
    public void read() {
        System.out.println("  [硬盘] 读取数据...");
    }

    public void write() {
        System.out.println("  [硬盘] 写入数据...");
    }
}
