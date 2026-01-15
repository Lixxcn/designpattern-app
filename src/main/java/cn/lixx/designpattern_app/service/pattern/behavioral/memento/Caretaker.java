package cn.lixx.designpattern_app.service.pattern.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理者类 - 管理备忘录
 */
public class Caretaker {
    private final List<Memento> mementos = new ArrayList<>();

    public void add(Memento memento) {
        mementos.add(memento);
        System.out.println("  [管理者] 保存备忘录 #" + mementos.size());
    }

    public Memento get(int index) {
        System.out.println("  [管理者] 获取备忘录 #" + (index + 1));
        return mementos.get(index);
    }
}
