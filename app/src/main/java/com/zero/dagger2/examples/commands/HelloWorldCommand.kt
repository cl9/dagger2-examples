package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.Command.Result
import com.zero.dagger2.examples.Command.Status
import com.zero.dagger2.examples.Outputter
import javax.inject.Inject

class HelloWorldCommand @Inject constructor(val outputter: Outputter) : Command {

    override fun handleInput(input: List<String>): Result {
        if (input.isNotEmpty()) {
            return Result.invalid()
        }
        outputter.output("world!");
        return Result.handled()
    }
}