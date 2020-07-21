package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Database.Account
import com.zero.dagger2.examples.Outputter
import java.math.BigDecimal
import javax.inject.Inject

class DepositCommand @Inject constructor(val outputter: Outputter, val account: Account) : BigDecimalCommand(outputter) {

    override fun handleAmount(amount: BigDecimal) {
        account.deposit(amount);
        outputter.output(account.username() + " now has: " + account.balance());
    }
}