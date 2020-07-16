package com.zero.dagger2.examples.di.components

import com.zero.dagger2.examples.CommandRouter
import dagger.Component

@Component
interface CommandRouterComponent {
    fun router(): CommandRouter
}