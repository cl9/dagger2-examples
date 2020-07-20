package com.zero.dagger2.examples.commands

import android.os.Build
import androidx.annotation.RequiresApi
import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.Command.Result
import com.zero.dagger2.examples.Database
import com.zero.dagger2.examples.Outputter
import javax.inject.Inject

class LoginCommand @Inject constructor(val outputter: Outputter, val database: Database) : SingleArgCommand() {

    override fun handleArg(username: String): Result {
        val account: Database.Account = database.getAccount(username)
        outputter.output(
                username.toString() + " is logged in with balance: " + account.balance())
        return Result.handled();
    }
}