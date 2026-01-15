package cn.lixx.designpattern_app.service.pattern.structural.decorator;

/**
 * 具体组件 - 咖啡
 */
public class ConcreteComponent implements Component {
    private final String description;

    public ConcreteComponent(String description) {
        this.description = description;
    }

    @Override
    public void operation() {
        System.out.print(description);
    }

    public String getDescription() {
        return description;
    }
}
