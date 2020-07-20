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

## 多重绑定

可以使用多重绑定来实现插件体系结构，例如，其中几个模块可以贡献各个插件接口实现，以便使用整个插件集。

<img src="http://yuml.me/diagram/scruffy/class/[CommandRouter]uses -.->[HelloWorldCommand],[CommandRouter]uses -.->[LoginCommand],
[Command]^-.-[HelloWorldCommand],
[Command]^-.-[LoginCommand],
[Outputter]^-.-[SystemOutputter],
[HelloWorldCommand]uses -.->[SystemOutputter],
[LoginCommand]uses -.->[SystemOutputter]" >

1. 创建登录命令`LoginCommand`

2. 将每个命令添加到可注入的多绑定集合中

```
@Binds
@IntoMap
@StringKey("hello")
abstract fun helloWorldCommand(helloWorldCommand: HelloWorldCommand): Command
}
```

```
@Binds
@IntoMap
@StringKey("login")
abstract fun loginCommand(command: LoginCommand): Command
```

3. `CommandRouter`入参修改成集合，以使用提供的各个不同的命令

```
class CommandRouter @Inject constructor(private val commands: Map<String, @JvmSuppressWildcards Command>)
```

## dagger2 是如何实现多重绑定的

根据`@IntoMap`和`@StringKey`注解生成以下方法

```
private Map<String, Command> getMapOfStringAndCommand() {
    return MapBuilder.<String, Command>newMapBuilder(2).put("hello", getHelloWorldCommand()).put("login", getLoginCommand()).build();
}
```

然后提供给`CommandRouter`入参

```
@Override
public CommandRouter router() {
    return new CommandRouter(getMapOfStringAndCommand());
}
```
