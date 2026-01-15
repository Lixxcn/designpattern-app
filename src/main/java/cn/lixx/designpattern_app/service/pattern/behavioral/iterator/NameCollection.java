package cn.lixx.designpattern_app.service.pattern.behavioral.iterator;

/**
 * 具体聚合 - 名字集合
 */
public class NameCollection implements Aggregate {
    private final String[] names;

    public NameCollection(String[] names) {
        this.names = names;
    }

    @Override
    public Iterator createIterator() {
        return new NameIterator(names);
    }
}
