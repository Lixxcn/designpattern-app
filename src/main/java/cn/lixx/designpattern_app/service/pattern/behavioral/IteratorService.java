package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.iterator.Aggregate;
import cn.lixx.designpattern_app.service.pattern.behavioral.iterator.Iterator;
import cn.lixx.designpattern_app.service.pattern.behavioral.iterator.NameCollection;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class IteratorService {

    private final CodeReaderUtil codeReaderUtil;

    public IteratorService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 迭代器模式演示 ===\n\n");

        // 创建集合
        String[] names = {"张三", "李四", "王五", "赵六"};
        Aggregate nameCollection = new NameCollection(names);

        // 创建迭代器
        Iterator iterator = nameCollection.createIterator();

        output.append("遍历名字集合：\n");
        while (iterator.hasNext()) {
            String name = (String) iterator.next();
            output.append("  - ").append(name).append("\n");
        }

        return output.toString();
    }

    /**
     * 从 iterator 包读取所有示例代码
     */
    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.behavioral.iterator"
        );
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Iterator {
        <<interface>>
        +hasNext() boolean
        +next() Object
    }
    class Aggregate {
        <<interface>>
        +createIterator() Iterator
    }
    class NameCollection {
        -String[] names
        +NameCollection(String[])
        +createIterator() Iterator
    }
    class NameIterator {
        -String[] names
        -int position
        +NameIterator(String[])
        +hasNext() boolean
        +next() Object
    }
    class Client {
        +main()
    }

    Iterator <|.. NameIterator
    Aggregate <|.. NameCollection
    NameCollection --> NameIterator : creates
    Client --> Aggregate : uses
    Client --> Iterator : uses
""";
    }
}
