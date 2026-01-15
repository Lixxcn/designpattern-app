package cn.lixx.designpattern_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Pattern {
    private String id;
    private String name;
    private String nameEn;
    private PatternCategory category;
    private PatternDifficulty difficulty;
    private String definition;
    private String intent;
    private String useCases;
    private String participants;
    private String collaboration;
    private String prosCons;
    private String relatedPatterns;
    private String springExample;
    private String jdkExample;
    private String realWorldExample;
    private String mermaidDiagram;
    private String codeExample;
}
