package cn.lixx.designpattern_app.service.pattern.creational.prototype;

/**
 * 具体原型类 - 文档
 */
public class ConcretePrototype implements Prototype {
    private String title;
    private String content;
    private String author;

    public ConcretePrototype(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 克隆方法 - 浅拷贝
    @Override
    public Prototype clone() {
        return new ConcretePrototype(this.title, this.content, this.author);
    }

    // Getter和Setter方法
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return "文档 [标题: " + title + ", 内容: " + content + ", 作者: " + author + "]";
    }
}
