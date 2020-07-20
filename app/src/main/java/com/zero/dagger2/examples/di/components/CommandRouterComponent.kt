package com.zero.dagger2.examples.di.components

import com.zero.dagger2.examples.CommandRouter
import com.zero.dagger2.examples.di.modules.HelloCommandModule
import com.zero.dagger2.examples.di.modules.LoginCommandModule
import com.zero.dagger2.examples.di.modules.SystemOutModule
import dagger.Component

@Component(modules = [HelloCommandModule::class, LoginCommandModule::class, SystemOutModule::class])
interface CommandRouterComponent {
    fun router(): CommandRouter
}