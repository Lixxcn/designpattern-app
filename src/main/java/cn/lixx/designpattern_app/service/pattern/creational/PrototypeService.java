package cn.lixx.designpattern_app.service.pattern.creational;
import cn.lixx.designpattern_app.model.CodeFile;
import java.util.List;

import cn.lixx.designpattern_app.service.pattern.creational.prototype.ConcretePrototype;
import cn.lixx.designpattern_app.service.pattern.creational.prototype.Prototype;
import cn.lixx.designpattern_app.service.pattern.creational.prototype.PrototypeManager;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class PrototypeService {

    private final CodeReaderUtil codeReaderUtil;

    public PrototypeService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 原型模式演示 ===\n\n");

        // 创建原型对象
        output.append("1. 创建原型文档：\n");
        ConcretePrototype originalDoc = new ConcretePrototype(
            "设计模式学习笔记",
            "本文档介绍GoF 23种设计模式...",
            "张三"
        );
        output.append("原: ").append(originalDoc).append("\n\n");

        // 克隆对象
        output.append("2. 克隆文档并修改：\n");
        ConcretePrototype clonedDoc1 = (ConcretePrototype) originalDoc.clone();
        clonedDoc1.setTitle("设计模式学习笔记 - 副本");
        clonedDoc1.setContent("这是克隆的文档，可以独立修改...");
        output.append("克隆1: ").append(clonedDoc1).append("\n");
        output.append("原: ").append(originalDoc).append("\n\n");

        output.append("3. 再次克隆：\n");
        ConcretePrototype clonedDoc2 = (ConcretePrototype) originalDoc.clone();
        clonedDoc2.setAuthor("李四");
        output.append("克隆2: ").append(clonedDoc2).append("\n\n");

        // 使用原型管理器
        output.append("4. 使用原型管理器：\n");
        PrototypeManager manager = new PrototypeManager();
        manager.registerPrototype("template", new ConcretePrototype("模板", "模板内容", "系统"));

        Prototype doc1 = manager.create("template");
        if (doc1 instanceof ConcretePrototype) {
            ((ConcretePrototype) doc1).setTitle("从模板创建的文档1");
            ((ConcretePrototype) doc1).setContent("具体内容1");
            ((ConcretePrototype) doc1).setAuthor("用户1");
        }

        Prototype doc2 = manager.create("template");
        if (doc2 instanceof ConcretePrototype) {
            ((ConcretePrototype) doc2).setTitle("从模板创建的文档2");
            ((ConcretePrototype) doc2).setContent("具体内容2");
            ((ConcretePrototype) doc2).setAuthor("用户2");
        }

        output.append("文档1: ").append(doc1).append("\n");
        output.append("文档2: ").append(doc2);

        return output.toString();
    }

    /**
     * 从 prototype 包读取所有示例代码
     */
    public List<CodeFile> getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.creational.prototype"
        );
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Prototype {
        <<interface>>
        +clone() Prototype
    }
    class ConcretePrototype {
        -String title
        -String content
        -String author
        +clone() Prototype
        +getTitle() String
        +setTitle(String)
        +getContent() String
        +setContent(String)
    }
    class PrototypeManager {
        -Map~String,Prototype~ prototypes
        +registerPrototype(String, Prototype)
        +create(String) Prototype
    }
    class Client {
        +main()
    }

    Prototype <|.. ConcretePrototype
    Client --> Prototype : uses
    Client --> PrototypeManager : uses
    ConcretePrototype ..> ConcretePrototype : clones
""";
    }
}
