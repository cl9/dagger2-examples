# dagger2-examples

## download gradle
* java
```
implementation 'com.google.dagger:dagger:2.28.2'
annotationProcessor 'com.google.dagger:dagger-compiler:2.28.2'
```

* kotlin
```
implementation 'com.google.dagger:dagger:2.28.2'
kapt 'com.google.dagger:dagger-compiler:2.28.2'
```

## simple dagger
use @Inject and @Component annotation build a simple example

1. create new command for type hello to print world
```
class HelloWorldCommand @Inject constructor() : Command
```
2. CommandRouter depends on HelloWorldCommand
```
class CommandRouter @Inject constructor(helloWorldCommand: HelloWorldCommand)
```
3. create CommandRouterComponent bridge @Inject
```
@Component
interface CommandRouterComponent {
    fun router(): CommandRouter
}
```