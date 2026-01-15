package cn.lixx.designpattern_app.service.pattern.behavioral.iterator;

/**
 * 具体迭代器
 */
public class NameIterator implements Iterator {
    private final String[] names;
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
