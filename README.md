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

## 为什么要使用@Singleton

<img src="http://yuml.me/diagram/scruffy/class/[CommandRouter]uses -.->[HelloWorldCommand],[CommandRouter]uses -.->[LoginCommand],
[CommandRouter]uses -.->[DepositCommand],
[Command]^-.-[HelloWorldCommand],
[Command]^-.-[LoginCommand],
[Command]^-.-[DepositCommand],
[Outputter]^-.-[SystemOutputter],
[HelloWorldCommand]uses -.->[SystemOutputter],
[LoginCommand]uses -.->[SystemOutputter],
[DepositCommand]uses -.->[SystemOutputter],
[LoginCommand]uses -.->[Database],
[DepositCommand]uses -.->[Database]" >

1. 创建`Database`保存登录用户

2. 修改登录命令入参

```
class LoginCommand @Inject constructor(val outputter: Outputter, val database: Database) : SingleArgCommand()
```

3. 创建提现命令`DepositCommand`和对应 module

4. 在`CommandRouterComponent`中添加`DepositCommandsModule`依赖

> 使用以下命令运行该应用程序：<br/>
> deposit colin 2 <br/>
> login colin <br/>
> 第二个命令显示 colin 其余额为 0。怎么可能呢？
> 当我们在`Database`中添加`println("Creating a new $this");`代码，会发现打印创建了两次数据库对象，分别在`LoginCommand`和`DepositCommand`对象构造方法中传入

## dagger2 实现单例

```
@Singleton
final class Database { ... }

@Singleton
@Component
interface CommandRouterFactory {
  ...
}
```

## dagger2 是如何实现单例

1. `Database`生成单例代码

```
...
public static Database_Factory create() {
  return InstanceHolder.INSTANCE;

private static final class InstanceHolder {
  private static final Database_Factory INSTANCE = new Database_Factory();
}
```

2. `DaggerCommandRouter`生成获取`Database`单例代码

```
this.databaseProvider = DoubleCheck.provider(Database_Factory.create());
```
