package hello.core.autowired

import hello.core.AutoAppConfig
import hello.core.discount.DiscountPolicy
import hello.core.member.Grade
import hello.core.member.Member
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AllBeanTest {

    @Test
    fun findAllBean(){
        val ac = AnnotationConfigApplicationContext(AutoAppConfig::class.java, DiscountService::class.java)
        
        val discountService = ac.getBean(DiscountService::class.java)
        val member = Member(1L, "userA", Grade.VIP)
        val discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy")

        Assertions.assertThat(discountService).isInstanceOf(DiscountService::class.java)
        Assertions.assertThat(discountPrice).isEqualTo(1000)

        val rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy")

        Assertions.assertThat(discountService).isInstanceOf(DiscountService::class.java)
        Assertions.assertThat(rateDiscountPrice).isEqualTo(2000)
    }

    companion object {
        class DiscountService(
                @Autowired
                val policyMap: Map<String, DiscountPolicy>,
                @Autowired
                val policies: List<DiscountPolicy>
        ) {
            fun discount(member: Member, price: Int, discountCode: String): Int {
                val discountPolicy = policyMap[discountCode]
                return discountPolicy!!.discount(member, price)
            }

            init {
                println(policyMap)
                println(policies)
            }
        }
    }

}