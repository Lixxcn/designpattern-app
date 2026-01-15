package cn.lixx.designpattern_app.service.pattern.structural;
import cn.lixx.designpattern_app.model.CodeFile;
import java.util.List;

import cn.lixx.designpattern_app.service.pattern.structural.facade.ComputerFacade;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class FacadeService {

    private final CodeReaderUtil codeReaderUtil;

    public FacadeService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

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

    /**
     * 从 facade 包读取所有示例代码
     */
    public List<CodeFile> getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.structural.facade"
        );
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
