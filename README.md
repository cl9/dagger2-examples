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

## 自定义 Scope

<img src="http://yuml.me/diagram/scruffy/class/
[CommandRouterComponent]uses -.->[CommandProcessor],
[CommandProcessor]uses -.->[CommandRouter],
[CommandRouter]uses -.->[HelloWorldCommand],[CommandRouter]uses -.->[LoginCommand],
[LoginCommand]uses -.->[Database],
[DepositCommand]uses -.->[WithdrawalLimiter],
[WithdrawCommand]uses -.->[BigDecimal],
[WithdrawCommand]uses -.->[WithdrawalLimiter],
[WithdrawalLimiter]uses -.->[BigDecimal],
[LoginCommand]uses -.->[UserCommandsComponent],
[UserCommandsComponent]uses -.->[DepositCommand],
[UserCommandsComponent]uses -.->[WithdrawCommand]" >

1. 创建`WithdrawalLimiter`控制提现最大额度

2. 创建自定义 Scope`PerSession`

```
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerSession
```

3. 通过组件`UserCommandsComponent`的生命周期控制`WithdrawalLimiter`的生命周期

```
@PerSession
interface UserCommandsComponent

@PerSession
class WithdrawalLimiter
```

## 自定义 Scope VS @Singleton

1. 作用域的名称是没有意义的
2. 作用域实例的生存期与具有该作用域的组件的生存期直接相关
3. @Singleton 实际上只是另一个作用域注解

## @Singleton Database 和@PerSession WithdrawalLimiter 的区别

- 在`CommandRouterComponent`中的所有对象之间共享一个`Database`实例

- 在每个`UserCommandsComponent`中都存在一个单独的`WithdrawalLimiter`实例

## dagger2 是如何通过自定义 Scope 限制生命周期

通过@PerSession 生成的`WithdrawalLimiter`代码

```
public final class WithdrawalLimiter_Factory implements Factory<WithdrawalLimiter> {
  ...

  public static WithdrawalLimiter_Factory create(Provider<BigDecimal> maximumWithdrawalProvider) {
    return new WithdrawalLimiter_Factory(maximumWithdrawalProvider);
  }
}
```
