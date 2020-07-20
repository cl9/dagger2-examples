package com.zero.dagger2.examples.commands

import android.os.Build
import androidx.annotation.RequiresApi
import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.Database
import com.zero.dagger2.examples.Outputter
import java.math.BigDecimal
import javax.inject.Inject

class DepositCommand @Inject constructor(val outputter: Outputter, val database: Database) : Command {
    override fun key(): String {
        return "deposit"
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun handleInput(input: List<String?>): Command.Status? {
        if (input.size != 2) {
            return Command.Status.INVALID
        }

        val account: Database.Account = database.getAccount(input[0]!!)
        account.deposit(BigDecimal(input[1]))
        outputter.output(account.username().toString() + " now has: " + account.balance())
        return Command.Status.HANDLED
    }

}