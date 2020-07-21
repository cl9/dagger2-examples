package com.zero.dagger2.examples

import android.os.Build
import androidx.annotation.RequiresApi
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Database @Inject constructor() {
    private val accounts: MutableMap<String, Account> = HashMap()

    init {
        println("Creating a new $this");
    }

    fun getAccount(username: String): Account {
        return accounts.computeIfAbsent(username, ::Account)
    }

    class Account(private val username: String) {
        private var balance: BigDecimal = BigDecimal.ZERO
        fun username(): String? {
            return username
        }

        fun balance(): BigDecimal {
            return balance
        }

        fun deposit(amount: BigDecimal) {
            balance = balance.plus(amount)
        }

        fun withdraw(amount: BigDecimal) {
            balance = balance.subtract(amount)
        }

    }
}