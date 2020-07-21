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

## @Qualifier

<img src="http://yuml.me/diagram/scruffy/class/
[CommandRouterComponent]uses -.->[CommandProcessor],
[CommandProcessor]uses -.->[CommandRouter],
[CommandRouter]uses -.->[HelloWorldCommand],[CommandRouter]uses -.->[LoginCommand],
[LoginCommand]uses -.->[Database],
[DepositCommand]uses -.->[Database.Account],
[WithdrawCommand]uses -.->[Database.Account],
[WithdrawCommand]uses -.->[BigDecimal],
[WithdrawCommand]uses -.->[BigDecimal],
[LoginCommand]uses -.->[UserCommandsComponent],
[UserCommandsComponent]uses -.->[DepositCommand],
[UserCommandsComponent]uses -.->[WithdrawCommand]" >

1. 使用`@Qualifier`注解创建提现最大最小额度限定符

```
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MaximumWithdrawal

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MinimumBalance
```

2. 创建提现命令`WithdrawCommand`并使用最大最小额度限定符

```
class WithdrawCommand @Inject constructor(... @MinimumBalance val minimumBalance: BigDecimal, @MaximumWithdrawal val maximumWithdrawal: BigDecimal) : BigDecimalCommand(outputter)
```

3. 创建`AmountsModule`提供最大最小额度

```
@Provides
@MinimumBalance
fun minimumBalance(): BigDecimal {
    return BigDecimal.ZERO
}

@Provides
@MaximumWithdrawal
fun maximumWithdrawal(): BigDecimal {
    return BigDecimal(1000)
}
```

4. `UserCommandsModule`include 属性添加`AmountsModule`依赖

```
class LoginCommand @Inject constructor(... val userCommandsRouterFactory: UserCommandsComponent.Factory) : SingleArgCommand()
```

## dagger2 是如何通过限定符区分相同类型的

1. 在`AmountsModule`中通过`@MinimumBalance`注解的方法会生成以下代码

```
public final class AmountsModule_MinimumBalanceFactory implements Factory<BigDecimal> {
  ...

  public static BigDecimal minimumBalance(AmountsModule instance) {
    return Preconditions.checkNotNull(instance.minimumBalance(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
```

2. 在`AmountsModule`中通过`@MaximumWithdrawal`注解的方法会生成以下代码

```
public final class AmountsModule_MaximumWithdrawalFactory implements Factory<BigDecimal> {
  ...

  public static BigDecimal maximumWithdrawal(AmountsModule instance) {
    return Preconditions.checkNotNull(instance.maximumWithdrawal(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

```
