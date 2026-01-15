package cn.lixx.designpattern_app.service.pattern.structural.facade;

/**
 * 子系统B - 内存
 */
public class Memory {
    public void load() {
        System.out.println("  [内存] 加载数据...");
    }

    public void unload() {
        System.out.println("  [内存] 释放数据...");
    }
}
