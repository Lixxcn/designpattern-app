package cn.lixx.designpattern_app.service.pattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合节点 - 包含子节点
 */
public class Composite implements Component {
    private final String name;
    private final List<Component> children = new ArrayList<>();

    public Composite(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("组合节点: " + name);
        for (Component child : children) {
            child.operation();
        }
    }

    @Override
    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public Component getChild(int i) {
        return children.get(i);
    }
}
