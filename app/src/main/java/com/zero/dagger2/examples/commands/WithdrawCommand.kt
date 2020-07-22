package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Database.Account
import com.zero.dagger2.examples.Outputter
import com.zero.dagger2.examples.WithdrawalLimiter
import com.zero.dagger2.examples.di.qualifiers.MinimumBalance
import java.math.BigDecimal
import javax.inject.Inject

class WithdrawCommand @Inject constructor(val outputter: Outputter, val account: Account, @MinimumBalance val minimumBalance: BigDecimal, val limiter: WithdrawalLimiter) : BigDecimalCommand(outputter) {

    override fun handleAmount(amount: BigDecimal) {
        val remainingWithdrawalLimit = limiter.remainingWithdrawalLimit
        if (amount > remainingWithdrawalLimit) {
            outputter.output("you may not withdraw $amount; you may withdraw $remainingWithdrawalLimit more in this session")
            return
        }
        val newBalance = account.balance() - amount
        if (newBalance < minimumBalance) {
            outputter.output("error")
        } else {
            account.withdraw(amount)
            limiter.recordWithdrawal(amount)
            outputter.output("your new balance is: " + account.balance())
        }
    }
}
