package com.zero.dagger2.examples.di.modules

import com.zero.dagger2.examples.di.qualifiers.MaximumWithdrawal
import com.zero.dagger2.examples.di.qualifiers.MinimumBalance
import dagger.Module
import dagger.Provides
import java.math.BigDecimal

@Module
class AmountsModule {
    @Provides
    @MinimumBalance
    fun minimumBalance(): BigDecimal {
        return BigDecimal.ZERO
    }

    @Provides
    @MaximumWithdrawal
    fun maximumWithdrawal(): BigDecimal {
        return BigDecimal(1000)
    }
}