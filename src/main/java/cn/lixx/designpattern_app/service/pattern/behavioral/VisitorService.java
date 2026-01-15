package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.visitor.*;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {

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

    public String getCodeExample() {
        return """
// 访问者接口
interface Visitor {
    void visit(Book book);
    void visit(Fruit fruit);
}

// 元素接口
interface ItemElement {
    void accept(Visitor visitor);
}

// 具体元素 - 书
class Book implements ItemElement {
    private String name;
    private int price;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// 具体元素 - 水果
class Fruit implements ItemElement {
    private String name;
    private int price;

    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// 具体访问者
class ShoppingCartVisitor implements Visitor {
    @Override
    public void visit(Book book) {
        System.out.println("书: " + book.getName() + ", ¥" + book.getPrice());
    }

    @Override
    public void visit(Fruit fruit) {
        System.out.println("水果: " + fruit.getName() + ", ¥" + fruit.getPrice());
    }
}

// 使用示例
ItemElement[] items = {
    new Book("设计模式", 89),
    new Fruit("苹果", 10)
};

Visitor visitor = new ShoppingCartVisitor();
for (ItemElement item : items) {
    item.accept(visitor);
}
""";
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
