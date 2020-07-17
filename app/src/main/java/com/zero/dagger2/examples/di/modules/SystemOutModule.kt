package com.zero.dagger2.examples.di.modules

import com.zero.dagger2.examples.Outputter
import com.zero.dagger2.examples.outputter.SystemOutputter
import dagger.Binds
import dagger.Module

@Module
abstract class SystemOutModule {
    @Binds
    abstract fun textOutputter(outputter: SystemOutputter): Outputter
}