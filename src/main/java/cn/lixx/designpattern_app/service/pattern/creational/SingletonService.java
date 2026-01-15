package cn.lixx.designpattern_app.service.pattern.creational;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.lixx.designpattern_app.model.CodeFile;
import cn.lixx.designpattern_app.service.pattern.creational.singleton.Client;
import cn.lixx.designpattern_app.util.CodeReaderUtil;

@Service
public class SingletonService {

    private final CodeReaderUtil codeReaderUtil;

    public SingletonService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

    public String executeExample() {
        return Client.demonstrate();
    }

    /**
     * 从 singleton 包读取所有示例代码
     */
    // public List<CodeFile> getCodeExample() {
    // return codeReaderUtil.readCodeFromPackage(
    // "cn.lixx.designpattern_app.service.pattern.creational.singleton",
    // "Client" // 排除 Client 类，因为它只是演示类
    // );
    // }
    public List<CodeFile> getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
                "cn.lixx.designpattern_app.service.pattern.creational.singleton");
    }

    public String getMermaidDiagram() {
        return """
                classDiagram
                    class Singleton {
                        -static Singleton instance
                        -Singleton() private
                        +static Singleton getInstance()
                        +void doSomething()
                    }
                    class Client {
                        +main()
                    }
                    Client --> Singleton : uses
                    Singleton ..> Singleton : creates instance
                """;
    }
}
