package com.zero.dagger2.examples.di.modules

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.commands.HelloWorldCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
abstract class HelloCommandModule {
    @Binds
    @IntoMap
    @StringKey("hello")
    abstract fun helloWorldCommand(helloWorldCommand: HelloWorldCommand): Command
}