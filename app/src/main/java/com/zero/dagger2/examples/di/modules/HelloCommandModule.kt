package com.zero.dagger2.examples.di.modules

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.Outputter
import com.zero.dagger2.examples.commands.HelloWorldCommand
import dagger.Module
import dagger.Provides

@Module
class HelloCommandModule {
    @Provides
    fun helloWorldCommand(outputter: Outputter): Command {
        return HelloWorldCommand(outputter)
    }
}