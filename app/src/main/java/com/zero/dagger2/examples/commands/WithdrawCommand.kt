package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Database.Account
import com.zero.dagger2.examples.Outputter
import com.zero.dagger2.examples.di.qualifiers.MaximumWithdrawal
import com.zero.dagger2.examples.di.qualifiers.MinimumBalance
import java.math.BigDecimal
import javax.inject.Inject

class WithdrawCommand @Inject constructor(val outputter: Outputter, val account: Account, @MinimumBalance val minimumBalance: BigDecimal, @MaximumWithdrawal val maximumWithdrawal: BigDecimal) : BigDecimalCommand(outputter) {

    override fun handleAmount(amount: BigDecimal) {
        if (amount > maximumWithdrawal) {
            outputter.output("you may not withdraw $amount; you may withdraw $maximumWithdrawal more in this session")
            return
        }
        val newBalance = account.balance().subtract(amount);
        if (newBalance < minimumBalance) {
            outputter.output("error")
        } else {
            account.withdraw(amount)
            outputter.output("your new balance is: " + account.balance())
        }
    }
}
