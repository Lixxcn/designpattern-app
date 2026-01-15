package cn.lixx.designpattern_app.service.pattern.behavioral.command;

/**
 * 命令接口
 */
public interface Command {
    void execute();
    void undo();
}
