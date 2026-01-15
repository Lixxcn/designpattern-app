package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.iterator.Aggregate;
import cn.lixx.designpattern_app.service.pattern.behavioral.iterator.Iterator;
import cn.lixx.designpattern_app.service.pattern.behavioral.iterator.NameCollection;
import org.springframework.stereotype.Service;

@Service
public class IteratorService {

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

    public String getCodeExample() {
        return """
// 迭代器接口
interface Iterator {
    boolean hasNext();
    Object next();
}

// 聚合接口
interface Aggregate {
    Iterator createIterator();
}

// 具体聚合
class NameCollection implements Aggregate {
    private String[] names;

    public NameCollection(String[] names) {
        this.names = names;
    }

    @Override
    public Iterator createIterator() {
        return new NameIterator(names);
    }
}

// 具体迭代器
class NameIterator implements Iterator {
    private String[] names;
    private int position = 0;

    public NameIterator(String[] names) {
        this.names = names;
    }

    @Override
    public boolean hasNext() {
        return position < names.length;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return names[position++];
        }
        return null;
    }
}

// 使用示例
String[] names = {"张三", "李四", "王五"};
Aggregate collection = new NameCollection(names);
Iterator iterator = collection.createIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
""";
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
