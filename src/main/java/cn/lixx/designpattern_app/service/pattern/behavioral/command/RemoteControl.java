package cn.lixx.designpattern_app.service.pattern.behavioral.command;

/**
 * 调用者 - 遥控器
 */
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        System.out.println("[遥控器] 按下按钮");
        command.execute();
    }

    public void pressUndo() {
        System.out.println("[遥控器] 按下撤销");
        command.undo();
    }
}
