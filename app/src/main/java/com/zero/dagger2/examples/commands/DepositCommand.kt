package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Database.Account
import com.zero.dagger2.examples.Outputter
import com.zero.dagger2.examples.WithdrawalLimiter
import java.math.BigDecimal
import javax.inject.Inject

class DepositCommand @Inject constructor(val outputter: Outputter, val account: Account, val limiter: WithdrawalLimiter) : BigDecimalCommand(outputter) {

    override fun handleAmount(amount: BigDecimal) {
        account.deposit(amount)
        limiter.recordDeposit(amount)
        outputter.output("${account.username()} now has ${account.balance()}")
    }
}