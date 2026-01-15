package cn.lixx.designpattern_app.service.pattern.creational;
import cn.lixx.designpattern_app.model.CodeFile;
import java.util.List;

import cn.lixx.designpattern_app.service.pattern.creational.abstractfactory.*;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class AbstractFactoryService {

    private final CodeReaderUtil codeReaderUtil;

    public AbstractFactoryService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 抽象工厂模式演示 ===\n\n");

        // 使用工厂1创建产品族1
        output.append("1. 使用工厂1创建产品族1：\n");
        AbstractFactory factory1 = new ConcreteFactory1();
        AbstractProductA productA1 = factory1.createProductA();
        AbstractProductB productB1 = factory1.createProductB();
        productA1.operationA();
        productB1.operationB();

        output.append("\n2. 使用工厂2创建产品族2：\n");
        AbstractFactory factory2 = new ConcreteFactory2();
        AbstractProductA productA2 = factory2.createProductA();
        AbstractProductB productB2 = factory2.createProductB();
        productA2.operationA();
        productB2.operationB();

        return output.toString();
    }

    /**
     * 从 abstractfactory 包读取所有示例代码
     */
    public List<CodeFile> getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.creational.abstractfactory"
        );
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class AbstractFactory {
        <<interface>>
        +createProductA() AbstractProductA
        +createProductB() AbstractProductB
    }
    class ConcreteFactory1 {
        +createProductA() AbstractProductA
        +createProductB() AbstractProductB
    }
    class ConcreteFactory2 {
        +createProductA() AbstractProductA
        +createProductB() AbstractProductB
    }
    class AbstractProductA {
        <<interface>>
        +operationA()
    }
    class AbstractProductB {
        <<interface>>
        +operationB()
    }
    class ProductA1 {
        +operationA()
    }
    class ProductA2 {
        +operationA()
    }
    class ProductB1 {
        +operationB()
    }
    class ProductB2 {
        +operationB()
    }

    AbstractFactory <|.. ConcreteFactory1
    AbstractFactory <|.. ConcreteFactory2
    AbstractProductA <|.. ProductA1
    AbstractProductA <|.. ProductA2
    AbstractProductB <|.. ProductB1
    AbstractProductB <|.. ProductB2
    ConcreteFactory1 ..> ProductA1 : creates
    ConcreteFactory1 ..> ProductB1 : creates
    ConcreteFactory2 ..> ProductA2 : creates
    ConcreteFactory2 ..> ProductB2 : creates
""";
    }
}
