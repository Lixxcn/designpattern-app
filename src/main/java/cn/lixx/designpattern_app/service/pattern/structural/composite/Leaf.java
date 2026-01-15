package cn.lixx.designpattern_app.service.pattern.structural.composite;

/**
 * 叶子节点 - 没有子节点
 */
public class Leaf implements Component {
    private final String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("  叶子节点: " + name);
    }

    @Override
    public void add(Component component) {
        System.out.println("不能添加子节点到叶子");
    }

    @Override
    public void remove(Component component) {
        System.out.println("不能从叶子移除子节点");
    }

    @Override
    public Component getChild(int i) {
        return null;
    }
}
