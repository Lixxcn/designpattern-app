package cn.lixx.designpattern_app.service.pattern.structural.facade;

/**
 * 外观类 - 提供简化的接口
 */
public class ComputerFacade {
    private final CPU cpu;
    private final Memory memory;
    private final HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void startComputer() {
        System.out.println("启动电脑：");
        hardDrive.read();
        memory.load();
        cpu.start();
        System.out.println("电脑启动完成！\n");
    }

    public void shutdownComputer() {
        System.out.println("关闭电脑：");
        cpu.shutdown();
        memory.unload();
        hardDrive.write();
        System.out.println("电脑已关闭！");
    }
}
