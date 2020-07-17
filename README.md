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

## 概念

- @Binds 方法是抽象
- @Binds 是定义别名的首选方法
- @Binds 方法必须在模块内部

## @Binds 替代 @Provides

1. 加上`HelloWorldCommand`的@Inject 注解

2. 请注意，方法的返回类型 Command 是 Dagger 现在知道如何提供的类型，而参数类型是 Dagger 在依赖于某物时知道使用的类型 Command。该方法是抽象的，因为仅声明它就足以告诉 Dagger 该做什么。Dagger 实际上并未调用此方法或为其提供实现。

```
@Module
abstract class HelloCommandModule {
    @Binds
    abstract fun helloWorldCommand(command: HelloWorldCommand): Command
}
```

> @Provides 方法以 provide 前缀命名，@Module 类以 Module 后缀命名 。
