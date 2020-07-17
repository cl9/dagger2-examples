package com.zero.dagger2.examples.outputter

import com.zero.dagger2.examples.Outputter
import javax.inject.Inject

class SystemOutputter @Inject constructor() : Outputter {
    override fun output(output: String?) {
        println(output)
    }
}