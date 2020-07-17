# dagger2-examples

## gradle 配置

- java

```
implementation 'com.google.dagger:dagger:2.28.2'
annotationProcessor 'com.google.dagger:dagger-compiler:2.28.2'
```

- kotlin

```
implementation 'com.google.dagger:dagger:2.28.2'
kapt 'com.google.dagger:dagger-compiler:2.28.2'
```

## 使用@Provides 替代@Inject

@Inject 注解并非在所有地方都有效：

- 接口无法构建
- 不能注解第三方类
- 必须配置可配置对象

1. 去掉`HelloWorldCommand`的@Inject 注解

2. 修改`CommandRouter`的构造函数，入参修改为接口`Command`

```
class CommandRouter @Inject constructor(command: Command)
```

3. 所有@Provides 注解必须在@Modules 注解的 Module 类中

```
@Module
class HelloCommandModule {
    @Provides
    fun helloWorldCommand(): Command {
        return HelloWorldCommand()
    }
}
```

4. 将模块类型传递给@Component 注解的 modules 参数

```
@Component(modules = [HelloCommandModule::class])
interface CommandRouterComponent {
    fun router(): CommandRouter
}
```

> @Provides 方法以 provide 前缀命名，@Module 类以 Module 后缀命名 。
