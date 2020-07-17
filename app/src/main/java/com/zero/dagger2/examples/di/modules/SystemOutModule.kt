package com.zero.dagger2.examples.di.modules

import com.zero.dagger2.examples.Outputter
import com.zero.dagger2.examples.outputter.SystemOutputter
import dagger.Module
import dagger.Provides

@Module
class SystemOutModule {
    @Provides
    fun textOutputter(): Outputter {
        return SystemOutputter()
    }
}