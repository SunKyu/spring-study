package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RateDiscountPolicyTest {

    val discountPolicy = RateDiscountPolicy()

    @Test
    fun `vip 10percent discount`() {
       //given
        val member = Member(1L, "memberVIP", Grade.VIP)

        //when
        val discount = discountPolicy.discount(member, 10000)

        //then
        assertThat(discount).isEqualTo(1000)
    }

    @Test
    fun `not vip discount`() {
        //given
        val member = Member(2L, "memberBASIC", Grade.BASIC)
        //when
        val discount = discountPolicy.discount(member, 10000)
        //then
        assertThat(discount).isEqualTo(0)
    }
}