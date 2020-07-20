package com.zero.dagger2.examples.di.modules

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.commands.DepositCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey


@Module
abstract class DepositCommandsModule {
    @Binds
    @IntoMap
    @StringKey("deposit")
    abstract fun depositCommand(command: DepositCommand): Command
}