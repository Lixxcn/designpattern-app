package cn.lixx.designpattern_app.service.pattern.behavioral.visitor;

/**
 * 具体元素 - 水果
 */
public class Fruit implements ItemElement {
    private final String name;
    private final int price;

    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
