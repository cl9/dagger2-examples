package com.zero.dagger2.examples

import com.zero.dagger2.examples.di.components.DaggerCommandRouterComponent
import java.util.*

class CommandLineAtm {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val scanner = Scanner(System.`in`)
            val commandProcessor = DaggerCommandRouterComponent.create().processor()
            while (scanner.hasNextLine()) {
                commandProcessor.process(scanner.nextLine());
            }
        }
    }
}