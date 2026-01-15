package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.command.*;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

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

    public String getCodeExample() {
        return """
// 命令接口
interface Command {
    void execute();
    void undo();
}

// 接收者
class Light {
    public void on() { System.out.println("开灯"); }
    public void off() { System.out.println("关灯"); }
}

// 具体命令
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

// 调用者
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}

// 使用示例
Light light = new Light();
Command lightOn = new LightOnCommand(light);

RemoteControl remote = new RemoteControl();
remote.setCommand(lightOn);
remote.pressButton();
remote.pressUndo();
""";
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
