package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Command.Result
import com.zero.dagger2.examples.Database
import com.zero.dagger2.examples.Database.Account
import com.zero.dagger2.examples.Outputter
import com.zero.dagger2.examples.di.components.UserCommandsComponent
import java.util.*
import javax.inject.Inject

class LoginCommand @Inject constructor(val outputter: Outputter, val database: Database, val userCommandsRouterFactory: UserCommandsComponent.Builder, val account: Optional<Account>) : SingleArgCommand() {

    override fun handleArg(username: String): Result {
        if (account.isPresent) {
            return Result.handled()
        }
        val account: Account = database.getAccount(username)
        outputter.output(
                username.toString() + " is logged in with balance: " + account.balance())
        return Result.enterNestedCommandSet(
                userCommandsRouterFactory.account(account).build().router());
    }
}