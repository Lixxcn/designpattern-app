package cn.lixx.designpattern_app.service.pattern.creational.builder;

/**
 * 产品类 - 电脑
 */
public class Product {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    private String monitor;

    // 私有构造函数，只能通过Builder创建
    private Product(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.monitor = builder.monitor;
    }

    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGpu() { return gpu; }
    public String getMonitor() { return monitor; }

    @Override
    public String toString() {
        return "电脑配置:\n" +
                "  CPU: " + cpu + "\n" +
                "  内存: " + ram + "\n" +
                "  存储: " + storage + "\n" +
                "  显卡: " + gpu + "\n" +
                "  显示器: " + monitor;
    }

    /**
     * 建造者类
     */
    public static class Builder {
        private String cpu = "默认CPU";
        private String ram = "8GB";
        private String storage = "512GB SSD";
        private String gpu = "集成显卡";
        private String monitor = "24寸显示器";

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

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder monitor(String monitor) {
            this.monitor = monitor;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
