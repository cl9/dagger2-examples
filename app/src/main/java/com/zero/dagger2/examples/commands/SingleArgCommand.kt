package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Command

abstract class SingleArgCommand : Command {
    override fun handleInput(input: List<String?>): Command.Status {
        return if (input.size == 1) handleArg(input[0]) else Command.Status.INVALID
    }

    /** Handles the single argument to the command.  */
    protected abstract fun handleArg(str: String?): Command.Status
}
