package cn.lixx.designpattern_app.service.pattern.behavioral.iterator;

/**
 * 聚合接口
 */
public interface Aggregate {
    Iterator createIterator();
}
