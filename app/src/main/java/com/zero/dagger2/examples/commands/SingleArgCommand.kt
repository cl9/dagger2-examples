package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.Command.Result

abstract class SingleArgCommand : Command {

    override fun handleInput(input: List<String>): Result {
        return if (input.size == 1) handleArg(input[0]) else Command.Result.invalid()
    }

    /** Handles the single argument to the command.  */
    protected abstract fun handleArg(str: String): Result
}
