package cn.lixx.designpattern_app.service.pattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题/被观察者接口
 */
public abstract class Subject {
    protected final List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
        System.out.println("  [主题] 添加观察者: " + observer.getClass().getSimpleName());
    }

    public void detach(Observer observer) {
        observers.remove(observer);
        System.out.println("  [主题] 移除观察者: " + observer.getClass().getSimpleName());
    }

    protected void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
