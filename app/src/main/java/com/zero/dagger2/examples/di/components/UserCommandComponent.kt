package com.zero.dagger2.examples.di.components

import com.zero.dagger2.examples.CommandRouter
import com.zero.dagger2.examples.Database.Account
import com.zero.dagger2.examples.di.modules.UserCommandsModule
import com.zero.dagger2.examples.di.scopes.PerSession
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent
import javax.inject.Singleton

@PerSession
@Subcomponent(modules = [UserCommandsModule::class])
interface UserCommandsComponent {
    fun router(): CommandRouter

//    @Subcomponent.Factory
//    interface Factory {
//        fun create(@BindsInstance account: Account): UserCommandsComponent
//    }

    @Subcomponent.Builder
    interface Builder {
        fun build(): UserCommandsComponent
        @BindsInstance
        fun account(account: Account): Builder
    }

    @Module(subcomponents = [UserCommandsComponent::class])
    interface InstallationModule
}