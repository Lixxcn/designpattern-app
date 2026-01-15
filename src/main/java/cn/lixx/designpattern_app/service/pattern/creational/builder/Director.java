package cn.lixx.designpattern_app.service.pattern.creational.builder;

/**
 * 指挥者类 - 封装创建逻辑
 */
public class Director {
    private Product.Builder builder;

    public Director(Product.Builder builder) {
        this.builder = builder;
    }

    /**
     * 构建高性能电脑
     */
    public Product buildHighPerformanceComputer() {
        return builder
                .cpu("Intel i9-13900K")
                .ram("64GB DDR5")
                .storage("2TB NVMe SSD")
                .gpu("RTX 4090")
                .monitor("32寸 4K 显示器")
                .build();
    }

    /**
     * 构建办公电脑
     */
    public Product buildOfficeComputer() {
        return builder
                .cpu("Intel i5-13400")
                .ram("16GB DDR4")
                .storage("512GB SSD")
                .gpu("集成显卡")
                .monitor("24寸显示器")
                .build();
    }

    /**
     * 构建游戏电脑
     */
    public Product buildGamingComputer() {
        return builder
                .cpu("AMD Ryzen 7 7800X3D")
                .ram("32GB DDR5")
                .storage("1TB NVMe SSD")
                .gpu("RTX 4080")
                .monitor("27寸 2K 144Hz 显示器")
                .build();
    }
}
