package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.Command.Status
import com.zero.dagger2.examples.Outputter
import javax.inject.Inject

class HelloWorldCommand @Inject constructor(val outputter: Outputter) : Command {

    override fun key(): String {
        return "hello";
    }

    override fun handleInput(input: List<String?>): Command.Status? {
        if (input.isNotEmpty()) {
            return Status.INVALID;
        }
        outputter.output("world!");
        return Status.HANDLED;
    }
}