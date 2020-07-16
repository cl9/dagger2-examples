package com.zero.dagger2.examples

import com.zero.dagger2.examples.di.components.DaggerCommandRouterComponent
import java.util.*

class CommandLineAtm {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val scanner = Scanner(System.`in`)
            val commandRouter = DaggerCommandRouterComponent.builder().build().router()
            while (scanner.hasNextLine()) {
                commandRouter.route(scanner.nextLine())
            }
        }
    }
}