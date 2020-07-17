package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.Command.Status
import javax.inject.Inject

class HelloWorldCommand : Command {

    override fun key(): String {
        return "hello";
    }

    override fun handleInput(input: List<String?>): Command.Status? {
        if (input.isNotEmpty()) {
            return Status.INVALID;
        }
        println("world!");
        return Status.HANDLED;
    }
}