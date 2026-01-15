package cn.lixx.designpattern_app.service.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂 - 管理享元对象
 */
public class FlyweightFactory {
    private final Map<String, Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(String key) {
        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new ConcreteFlyweight(key));
            System.out.println("  [工厂] 创建新享元: " + key);
        } else {
            System.out.println("  [工厂] 复用已有享元: " + key);
        }
        return flyweights.get(key);
    }

    public int getCount() {
        return flyweights.size();
    }
}
