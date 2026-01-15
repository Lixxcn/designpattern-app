package cn.lixx.designpattern_app.service.pattern.creational.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * 原型管理器 - 管理原型对象
 */
public class PrototypeManager {
    private final Map<String, Prototype> prototypes = new HashMap<>();

    public void registerPrototype(String key, Prototype prototype) {
        prototypes.put(key, prototype);
    }

    public Prototype create(String key) {
        Prototype prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("未找到原型: " + key);
        }
        return prototype.clone();
    }
}
