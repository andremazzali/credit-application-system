package me.dio.credit.application.system.service

import me.dio.credit.application.system.entity.Credit
import java.util.UUID

interface CreditService {
    fun save(credit: Credit): Credit
    fun findAllbyCustomer(customerId: Long):List<Credit>
    fun findByCreditCode(customerId: Long, creditcode: UUID): Credit
}