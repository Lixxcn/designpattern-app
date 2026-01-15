package cn.lixx.designpattern_app.service.pattern.behavioral;
import cn.lixx.designpattern_app.model.CodeFile;
import java.util.List;

import cn.lixx.designpattern_app.service.pattern.behavioral.visitor.*;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {

    private final CodeReaderUtil codeReaderUtil;

    public VisitorService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 访问者模式演示 ===\n\n");

        // 创建商品列表
        ItemElement[] items = new ItemElement[]{
            new Book("设计模式", 89),
            new Book("重构", 79),
            new Fruit("苹果", 10),
            new Fruit("香蕉", 8)
        };

        // 创建访问者
        Visitor visitor = new ShoppingCartVisitor();

        output.append("1. 遍历购物车：\n");
        int total = 0;
        for (ItemElement item : items) {
            item.accept(visitor);
            if (item instanceof Book) {
                total += ((Book) item).getPrice();
            } else if (item instanceof Fruit) {
                total += ((Fruit) item).getPrice();
            }
        }

        output.append("\n2. 总价: ¥").append(total);

        return output.toString();
    }

    /**
     * 从 visitor 包读取所有示例代码
     */
    public List<CodeFile> getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.behavioral.visitor"
        );
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Visitor {
        <<interface>>
        +visit(Book)
        +visit(Fruit)
    }
    class ShoppingCartVisitor {
        +visit(Book)
        +visit(Fruit)
    }
    class ItemElement {
        <<interface>>
        +accept(Visitor)
    }
    class Book {
        -String name
        -int price
        +Book(String, int)
        +accept(Visitor)
        +getName() String
        +getPrice() int
    }
    class Fruit {
        -String name
        -int price
        +Fruit(String, int)
        +accept(Visitor)
        +getName() String
        +getPrice() int
    }

    Visitor <|.. ShoppingCartVisitor
    ItemElement <|.. Book
    ItemElement <|.. Fruit
    Book --> Visitor : accepts
    Fruit --> Visitor : accepts
""";
    }
}
