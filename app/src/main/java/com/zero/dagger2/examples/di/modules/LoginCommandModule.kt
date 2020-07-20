package com.zero.dagger2.examples.di.modules

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.commands.LoginCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
abstract class LoginCommandModule {
    @Binds
    @IntoMap
    @StringKey("login")
    abstract fun loginCommand(command: LoginCommand): Command
}