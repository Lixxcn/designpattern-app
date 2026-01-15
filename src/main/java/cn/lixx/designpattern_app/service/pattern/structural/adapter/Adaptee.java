package cn.lixx.designpattern_app.service.pattern.structural.adapter;

/**
 * 被适配者 - 需要被适配的类
 */
public class Adaptee {
    public void specificRequest() {
        System.out.println("被适配者的特殊请求");
    }
}
