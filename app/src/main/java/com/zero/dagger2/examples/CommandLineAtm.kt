package com.zero.dagger2.examples

import java.util.*

class CommandLineAtm {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val scanner = Scanner(System.`in`)
            val commandRouter = CommandRouter()
            while (scanner.hasNextLine()) {
                commandRouter.route(scanner.nextLine())
            }
        }
    }
}