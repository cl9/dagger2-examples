package com.zero.dagger2.examples.di.components

import com.zero.dagger2.examples.CommandProcessor
import com.zero.dagger2.examples.di.modules.DepositCommandsModule
import com.zero.dagger2.examples.di.modules.HelloCommandModule
import com.zero.dagger2.examples.di.modules.LoginCommandModule
import com.zero.dagger2.examples.di.modules.SystemOutModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HelloCommandModule::class, LoginCommandModule::class, DepositCommandsModule::class, SystemOutModule::class])
interface CommandRouterComponent {
    fun processor(): CommandProcessor
}