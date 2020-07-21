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

## @Subcomponent

<img src="http://yuml.me/diagram/scruffy/class/
[CommandRouterComponent]uses -.->[CommandProcessor],
[CommandProcessor]uses -.->[CommandRouter],
[CommandRouter]uses -.->[HelloWorldCommand],[CommandRouter]uses -.->[LoginCommand],
[HelloWorldCommand]uses -.->[SystemOutputter],
[LoginCommand]uses -.->[SystemOutputter],
[DepositCommand]uses -.->[SystemOutputter],
[LoginCommand]uses -.->[Database],
[DepositCommand]uses -.->[Database.Account],
[LoginCommand]uses -.->[UserCommandsComponent],
[UserCommandsComponent]uses -.->[DepositCommand]" >

1. 创建子组件`UserCommandsComponent`

```
@Subcomponent(modules = [UserCommandsModule::class])
interface UserCommandsComponent {
    fun router(): CommandRouter

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance account: Account): UserCommandsComponent
    }
}
```

2. 建立父子组件之间的依赖关系

```
@Module(subcomponents = [UserCommandsComponent::class])
interface InstallationModule

@Component(modules = [... UserCommandsComponent.InstallationModule::class])
```

3. 登录命令`LoginCommand`依赖子组件`UserCommandsComponent`

```
class LoginCommand @Inject constructor(... val userCommandsRouterFactory: UserCommandsComponent.Factory) : SingleArgCommand()
```

## dagger2 声明子组件

1. builder 模式

```
@Subcomponent.Builder
interface Builder {
    fun build(): UserCommandsComponent
    @BindsInstance
    fun account(account: Account): Builder
}
```

2. 工厂模式

```
@Subcomponent.Factory
interface Factory {
    fun create(@BindsInstance account: Account): UserCommandsComponent
}
```

## dagger2 将子组件添加到父组件

定义一个带有 subcomponents 属性的@Module 模块，在父组件添加相关依赖。

```
@Module(subcomponents = [UserCommandsComponent::class])
interface InstallationModule

@Component(modules = [... UserCommandsComponent.InstallationModule::class])
```

## dagger2 是如何实现创建子组件的

1. 如果使用工厂模式创建子组件实例，例如

```
@Subcomponent.Factory
interface Factory {
    fun create(@BindsInstance account: Account): UserCommandsComponent
}
```

则会生成如下代码

```
private final class UserCommandsComponentFactory implements UserCommandsComponent.Factory {
  @Override
  public UserCommandsComponent create(Database.Account account) {
    Preconditions.checkNotNull(account);
    return new UserCommandsComponentImpl(account);
  }
}
```

2. 如果使用 builder 模式创建子组件实例，例如

```
@Subcomponent.Builder
interface Builder {
    fun build(): UserCommandsComponent
    @BindsInstance
    fun account(account: Account): Builder
}
```

则会生成如下代码

```
private final class UserCommandsComponentBuilder implements UserCommandsComponent.Builder {
  private Database.Account account
  @Override
  public UserCommandsComponentBuilder account(Database.Account account) {
    this.account = Preconditions.checkNotNull(account);
    return this;

  @Override
  public UserCommandsComponent build() {
    Preconditions.checkBuilderRequirement(account, Database.Account.class);
    return new UserCommandsComponentImpl(account);
  }
}
```
