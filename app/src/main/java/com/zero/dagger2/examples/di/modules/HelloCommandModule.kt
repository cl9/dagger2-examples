package com.zero.dagger2.examples.di.modules

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.commands.HelloWorldCommand
import dagger.Module
import dagger.Provides

@Module
class HelloCommandModule {
    @Provides
    fun helloWorldCommand(): Command {
        return HelloWorldCommand()
    }
}