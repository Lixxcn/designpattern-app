package cn.lixx.designpattern_app.service.pattern.structural;

import cn.lixx.designpattern_app.service.pattern.structural.composite.*;
import org.springframework.stereotype.Service;

@Service
public class CompositeService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 组合模式演示 ===\n\n");

        // 创建叶子节点
        output.append("1. 创建文件系统结构：\n\n");
        Leaf file1 = new Leaf("文件1.txt");
        Leaf file2 = new Leaf("文件2.txt");
        Leaf file3 = new Leaf("文件3.txt");

        // 创建组合节点
        Composite folder1 = new Composite("文件夹1");
        Composite folder2 = new Composite("文件夹2");
        Composite rootFolder = new Composite("根目录");

        // 构建树形结构
        folder1.add(file1);
        folder1.add(file2);

        folder2.add(file3);

        rootFolder.add(folder1);
        rootFolder.add(folder2);

        // 执行操作
        output.append("2. 遍历文件系统：\n");
        rootFolder.operation();

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 组件接口
interface Component {
    void operation();
    void add(Component component);
    void remove(Component component);
    Component getChild(int i);
}

// 叶子节点
class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("叶子: " + name);
    }

    @Override
    public void add(Component component) {
        // 叶子不能添加子节点
    }

    // ...其他方法
}

// 组合节点
class Composite implements Component {
    private List<Component> children = new ArrayList<>();

    @Override
    public void operation() {
        for (Component child : children) {
            child.operation();
        }
    }

    @Override
    public void add(Component component) {
        children.add(component);
    }

    // ...其他方法
}

// 使用示例
Composite root = new Composite();
root.add(new Leaf("文件1"));
root.add(new Leaf("文件2"));
root.operation();
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Component {
        <<interface>>
        +operation() void
        +add(Component)
        +remove(Component)
        +getChild(int) Component
    }
    class Leaf {
        -String name
        +Leaf(String)
        +operation() void
        +add(Component)
        +remove(Component)
        +getChild(int) Component
    }
    class Composite {
        -String name
        -List~Component~ children
        +Composite(String)
        +operation() void
        +add(Component)
        +remove(Component)
        +getChild(int) Component
    }
    class Client {
        +main()
    }

    Component <|.. Leaf
    Component <|.. Composite
    Composite o-- Component : contains
    Client --> Component : uses
""";
    }
}
