package com.zero.dagger2.examples.commands

import com.zero.dagger2.examples.Command
import com.zero.dagger2.examples.Command.Result
import com.zero.dagger2.examples.Outputter
import java.math.BigDecimal


abstract class BigDecimalCommand(private val outputter: Outputter) : SingleArgCommand() {

    override fun handleArg(str: String): Result {
        val amount = tryParse(str)
        when {
            amount == null -> outputter.output("$str is not a valid number")
            amount.signum() <= 0 -> outputter.output("amount must be positive")
            else -> handleAmount(amount)
        }
        return Result.handled()
    }

    private fun tryParse(arg: String): BigDecimal? {
        return try {
            BigDecimal(arg)
        } catch (e: NumberFormatException) {
            null
        }

    }

    /** Handles the given (positive) `amount` of money.  */
    protected abstract fun handleAmount(amount: BigDecimal)
}
