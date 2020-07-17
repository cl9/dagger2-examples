package com.zero.dagger2.examples.di.components

import com.zero.dagger2.examples.CommandRouter
import com.zero.dagger2.examples.di.modules.HelloCommandModule
import dagger.Component

@Component(modules = [HelloCommandModule::class])
interface CommandRouterComponent {
    fun router(): CommandRouter
}