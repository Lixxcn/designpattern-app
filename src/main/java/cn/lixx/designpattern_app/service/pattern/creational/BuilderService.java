package cn.lixx.designpattern_app.service.pattern.creational;

import cn.lixx.designpattern_app.service.pattern.creational.builder.Director;
import cn.lixx.designpattern_app.service.pattern.creational.builder.Product;
import org.springframework.stereotype.Service;

@Service
public class BuilderService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 建造者模式演示 ===\n\n");

        // 使用Builder直接构建
        output.append("1. 使用Builder自定义配置：\n");
        Product customComputer = new Product.Builder()
                .cpu("AMD Ryzen 9 7950X")
                .ram("128GB DDR5")
                .storage("4TB NVMe SSD")
                .gpu("RTX 4090 Ti")
                .monitor("49寸超宽带鱼屏")
                .build();
        output.append(customComputer).append("\n\n");

        // 使用Director构建预设配置
        output.append("2. 使用Director构建高性能电脑：\n");
        Director director = new Director(new Product.Builder());
        Product highEnd = director.buildHighPerformanceComputer();
        output.append(highEnd).append("\n\n");

        output.append("3. 使用Director构建办公电脑：\n");
        Product office = director.buildOfficeComputer();
        output.append(office).append("\n\n");

        output.append("4. 使用Director构建游戏电脑：\n");
        Product gaming = director.buildGamingComputer();
        output.append(gaming);

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 产品类
public class Product {
    private String cpu;
    private String ram;
    private String storage;
    // ...

    private Product(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        // ...
    }

    public static class Builder {
        private String cpu = "默认CPU";
        private String ram = "8GB";
        private String storage = "512GB SSD";

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}

// 使用示例
Product product = new Product.Builder()
    .cpu("Intel i9")
    .ram("64GB")
    .storage("2TB SSD")
    .build();
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Product {
        -String cpu
        -String ram
        -String storage
        -Product()
        +getCpu() String
        +getRam() String
        +getStorage() String
    }
    class Product$Builder {
        -String cpu
        -String ram
        -String storage
        +cpu(String) Builder
        +ram(String) Builder
        +storage(String) Builder
        +build() Product
    }
    class Director {
        -Builder builder
        +buildHighPerformance() Product
        +buildOffice() Product
        +buildGaming() Product
    }
    class Client {
        +main()
    }

    Product --> Product$Builder : creates
    Director --> Product$Builder : uses
    Client --> Product$Builder : uses
    Client --> Director : uses
    Product$Builder ..> Product : builds
""";
    }
}
