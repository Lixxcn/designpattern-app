package cn.lixx.designpattern_app.service.pattern.structural;

import cn.lixx.designpattern_app.service.pattern.structural.facade.ComputerFacade;
import org.springframework.stereotype.Service;

@Service
public class FacadeService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 外观模式演示 ===\n\n");

        // 创建外观对象
        ComputerFacade computer = new ComputerFacade();

        // 使用简化的接口操作复杂系统
        computer.startComputer();
        computer.shutdownComputer();

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 子系统类
class CPU {
    public void start() { System.out.println("CPU启动"); }
    public void shutdown() { System.out.println("CPU关闭"); }
}

class Memory {
    public void load() { System.out.println("内存加载"); }
    public void unload() { System.out.println("内存释放"); }
}

class HardDrive {
    public void read() { System.out.println("硬盘读取"); }
    public void write() { System.out.println("硬盘写入"); }
}

// 外观类
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void startComputer() {
        hardDrive.read();
        memory.load();
        cpu.start();
        System.out.println("电脑启动完成");
    }

    public void shutdownComputer() {
        cpu.shutdown();
        memory.unload();
        hardDrive.write();
        System.out.println("电脑已关闭");
    }
}

// 使用示例
ComputerFacade computer = new ComputerFacade();
computer.startComputer();
computer.shutdownComputer();
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class CPU {
        +start() void
        +execute() void
        +shutdown() void
    }
    class Memory {
        +load() void
        +unload() void
    }
    class HardDrive {
        +read() void
        +write() void
    }
    class ComputerFacade {
        -CPU cpu
        -Memory memory
        -HardDrive hardDrive
        +ComputerFacade()
        +startComputer() void
        +shutdownComputer() void
    }
    class Client {
        +main()
    }

    ComputerFacade --> CPU : uses
    ComputerFacade --> Memory : uses
    ComputerFacade --> HardDrive : uses
    Client --> ComputerFacade : uses
""";
    }
}
