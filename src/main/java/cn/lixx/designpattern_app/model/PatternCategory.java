package cn.lixx.designpattern_app.model;

public enum PatternCategory {
    CREATIONAL("创建型"),
    STRUCTURAL("结构型"),
    BEHAVIORAL("行为型");

    private final String displayName;

    PatternCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
