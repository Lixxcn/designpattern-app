package cn.lixx.designpattern_app.service.pattern.behavioral.visitor;

/**
 * 元素接口
 */
public interface ItemElement {
    void accept(Visitor visitor);
}
