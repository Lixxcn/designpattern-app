package cn.lixx.designpattern_app.service.pattern.structural;

import cn.lixx.designpattern_app.service.pattern.structural.composite.*;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class CompositeService {

    private final CodeReaderUtil codeReaderUtil;

    public CompositeService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

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

    /**
     * 从 composite 包读取所有示例代码
     */
    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.structural.composite"
        );
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
