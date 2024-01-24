package me.dio.credit.application.system.service.impl

import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.entity.CreditRepository
import me.dio.credit.application.system.service.CreditService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): CreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllbyCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditcode: UUID): Credit {
        val credit: Credit = (this.creditRepository.findByCreditCode(creditcode)
        ?: throw RuntimeException("CreditCode $creditcode not found "))
        return if (credit.customer?.id == customerId) credit
        else throw RuntimeException("Contact admin")
    }
}