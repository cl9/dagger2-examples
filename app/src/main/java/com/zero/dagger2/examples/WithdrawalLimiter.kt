package com.zero.dagger2.examples

import com.zero.dagger2.examples.di.qualifiers.MaximumWithdrawal
import com.zero.dagger2.examples.di.scopes.PerSession
import java.math.BigDecimal
import javax.inject.Inject

@PerSession
class WithdrawalLimiter @Inject constructor(@MaximumWithdrawal private val maximumWithdrawal: BigDecimal) {
    var remainingWithdrawalLimit: BigDecimal = maximumWithdrawal

    fun recordDeposit(amount: BigDecimal) {
        remainingWithdrawalLimit += amount
    }

    fun recordWithdrawal(amount: BigDecimal) {
        remainingWithdrawalLimit -= amount
    }
}
