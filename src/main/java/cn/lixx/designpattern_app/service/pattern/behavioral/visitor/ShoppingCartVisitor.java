package cn.lixx.designpattern_app.service.pattern.behavioral.visitor;

/**
 * 具体访问者 - 购物车访问者
 */
public class ShoppingCartVisitor implements Visitor {

    @Override
    public void visit(Book book) {
        System.out.println("  [购物车] 书: " + book.getName() + ", 价格: ¥" + book.getPrice());
    }

    @Override
    public void visit(Fruit fruit) {
        System.out.println("  [购物车] 水果: " + fruit.getName() + ", 价格: ¥" + fruit.getPrice());
    }
}
