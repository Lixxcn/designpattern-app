package cn.lixx.designpattern_app.service.pattern.behavioral.memento;

/**
 * 发起人类 - 创建和恢复备忘录
 */
public class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
        System.out.println("  [发起者] 状态设置为: " + state);
    }

    public String getState() {
        return state;
    }

    public Memento save() {
        System.out.println("  [发起者] 保存状态到备忘录");
        return new Memento(state);
    }

    public void restore(Memento memento) {
        this.state = memento.getState();
        System.out.println("  [发起者] 从备忘录恢复状态: " + state);
    }
}
