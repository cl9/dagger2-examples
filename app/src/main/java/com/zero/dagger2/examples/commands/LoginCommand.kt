package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.Outputter
import javax.inject.Inject

class LoginCommand @Inject constructor(val outputter: Outputter) : SingleArgCommand() {
    override fun key(): String {
        return "login";
    }

    override fun handleArg(username: String?): Command.Status {
        outputter.output("$username is logged in.");
        return Command.Status.HANDLED;
    }
}