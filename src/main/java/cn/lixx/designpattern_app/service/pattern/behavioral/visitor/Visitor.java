package cn.lixx.designpattern_app.service.pattern.behavioral.visitor;

/**
 * 访问者接口
 */
public interface Visitor {
    void visit(Book book);
    void visit(Fruit fruit);
}
