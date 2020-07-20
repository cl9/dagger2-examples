package com.zero.dagger2.examples.commands

import android.os.Build
import androidx.annotation.RequiresApi
import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.Database
import com.zero.dagger2.examples.Outputter
import javax.inject.Inject

class LoginCommand @Inject constructor(val outputter: Outputter, val database: Database) : SingleArgCommand() {
    override fun key(): String {
        return "login";
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun handleArg(username: String?): Command.Status {
        val account: Database.Account = database.getAccount(username!!)
        outputter.output(
                username.toString() + " is logged in with balance: " + account.balance())
        return Command.Status.HANDLED;
    }
}