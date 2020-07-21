package com.zero.dagger2.examples.di.modules

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.commands.DepositCommand
import com.zero.dagger2.examples.commands.LoginCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface UserCommandsModule {
    @Binds
    @IntoMap
    @StringKey("deposit")
    fun depositCommand(command: DepositCommand): Command
}