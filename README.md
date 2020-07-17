# dagger2-examples

## download gradle

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

## dagger 的简单使用

<img src="http://yuml.me/diagram/scruffy/class/[CommandRouter]->[HelloWorldCommand]" >

1. 使用@Inject 注解`HelloWorldCommand`的构造函数

```
class HelloWorldCommand @Inject constructor() : Command
```

2. 使用@Inject 注解`CommandRouter`的构造函数

```
class CommandRouter @Inject constructor(helloWorldCommand: HelloWorldCommand)
```

3. 使用@Component 注解将带有@Inject 注解的类通过其依赖关系形成对象图

```
@Component
interface CommandRouterComponent {
    fun router(): CommandRouter
}
```
