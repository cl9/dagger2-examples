package com.zero.dagger2.examples.di.modules

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.commands.HelloWorldCommand
import dagger.Binds
import dagger.Module

@Module
abstract class HelloCommandModule {
    @Binds
    abstract fun helloWorldCommand(command: HelloWorldCommand): Command
}