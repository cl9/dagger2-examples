package com.zero.dagger2.examples

import com.zero.dagger2.examples.Command.Result
import javax.inject.Inject


class CommandRouter @Inject constructor(private val commands: Map<String, @JvmSuppressWildcards Command>) {
    fun route(input: String): Result {
        val splitInput = split(input)
        if (splitInput.isEmpty()) {
            return invalidCommand(input)
        }
        val commandKey = splitInput[0]
        val command = commands[commandKey]
        command ?: return invalidCommand(input)
        val result = command.handleInput(splitInput.subList(1, splitInput.size))
        if (result.status() === Command.Status.INVALID) {
            println("$commandKey: invalid arguments")
        }
        return result
    }

    private fun invalidCommand(input: String): Result {
        println(
                String.format("couldn't understand \"%s\". please try again.", input)
        )
        return Result.invalid()
    }

    // Split on whitespace
    private fun split(input: String): List<String> {
        return input.trim().split(Regex("\\s+"))
    }
}