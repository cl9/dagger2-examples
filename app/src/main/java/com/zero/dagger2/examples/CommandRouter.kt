package com.zero.dagger2.examples

import com.zero.dagger2.examples.Command.Status
import com.zero.dagger2.examples.commands.HelloWorldCommand
import javax.inject.Inject


class CommandRouter @Inject constructor(helloWorldCommand: HelloWorldCommand) {
    private val commands: HashMap<String, Command> = HashMap()

    init {
        commands[helloWorldCommand.key()] = helloWorldCommand
    }

    fun route(input: String): Status? {
        val splitInput: List<String?> = split(input)
        if (splitInput.isEmpty()) {
            return invalidCommand(input)
        }

        val commandKey = splitInput[0]
        val command: Command = commands.get(commandKey) ?: return invalidCommand(input)

        val status: Status? = command.handleInput(splitInput.subList(1, splitInput.size))
        if (status === Status.INVALID) {
            println("$commandKey: invalid arguments")
        }
        return status
    }

    private fun invalidCommand(input: String): Status? {
        println(String.format("couldn't understand \"%s\". please try again.", input))
        return Status.INVALID
    }

    // Split on whitespace
    private fun split(input: String): List<String> {
        return input.trim().split(Regex("\\s+"))
    }
}