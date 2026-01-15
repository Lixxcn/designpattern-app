package cn.lixx.designpattern_app.model;

public enum PatternDifficulty {
    BEGINNER("初级"),
    INTERMEDIATE("中级"),
    ADVANCED("高级");

    private final String displayName;

    PatternDifficulty(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
