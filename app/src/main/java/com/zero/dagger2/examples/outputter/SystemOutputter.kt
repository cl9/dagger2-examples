package com.zero.dagger2.examples.outputter

import com.zero.dagger2.examples.Outputter

class SystemOutputter : Outputter {
    override fun output(output: String?) {
        println(output)
    }
}