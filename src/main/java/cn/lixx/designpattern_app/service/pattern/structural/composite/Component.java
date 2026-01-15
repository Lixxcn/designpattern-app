package cn.lixx.designpattern_app.service.pattern.structural.composite;

/**
 * 组件接口 - 声明组合对象和叶子对象的接口
 */
public interface Component {
    void operation();
    void add(Component component);
    void remove(Component component);
    Component getChild(int i);
}
