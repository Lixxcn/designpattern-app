package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.command.*;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    private final CodeReaderUtil codeReaderUtil;

    public CommandService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 命令模式演示 ===\n\n");

        // 创建接收者
        Light light = new Light();

        // 创建命令对象
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        // 创建调用者
        RemoteControl remote = new RemoteControl();

        output.append("1. 开灯：\n");
        remote.setCommand(lightOn);
        remote.pressButton();

        output.append("\n2. 撤销开灯：\n");
        remote.pressUndo();

        output.append("\n3. 关灯：\n");
        remote.setCommand(lightOff);
        remote.pressButton();

        output.append("\n4. 撤销关灯：\n");
        remote.pressUndo();

        return output.toString();
    }

    /**
     * 从 command 包读取所有示例代码
     */
    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.behavioral.command"
        );
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Command {
        <<interface>>
        +execute() void
        +undo() void
    }
    class LightOnCommand {
        -Light light
        +LightOnCommand(Light)
        +execute() void
        +undo() void
    }
    class LightOffCommand {
        -Light light
        +LightOffCommand(Light)
        +execute() void
        +undo() void
    }
    class Light {
        +on() void
        +off() void
    }
    class RemoteControl {
        -Command command
        +setCommand(Command)
        +pressButton() void
        +pressUndo() void
    }
    class Client {
        +main()
    }

    Command <|.. LightOnCommand
    Command <|.. LightOffCommand
    LightOnCommand --> Light : operates on
    LightOffCommand --> Light : operates on
    RemoteControl o-- Command : uses
    Client --> RemoteControl : uses
""";
    }
}
