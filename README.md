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

1. 登录命令添加可选绑定 account 依赖

```
class LoginCommand @Inject constructor(... val account: Optional<Account>)
```

2. 登录 module 使用@BindsOptionalOf 注解 account 依赖

```
@BindsOptionalOf
abstract fun optionalAccount(): Account
```

## dagger2 如何实现可选绑定

被@BindsOptionalOf 注解的 Account 会生成如下代码提供给登录命令

```
Optional.of(account)
```
