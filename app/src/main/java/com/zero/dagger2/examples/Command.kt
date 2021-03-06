package com.zero.dagger2.examples

interface Command {
    /**
     * String token that signifies this command should be selected (e.g.:
     * "deposit", "withdraw")
     */
    fun key(): String?

    /** Process the rest of the command's words and do something.  */
    fun handleInput(input: List<String?>?): Status?

    enum class Status {
        INVALID, HANDLED
    }
}