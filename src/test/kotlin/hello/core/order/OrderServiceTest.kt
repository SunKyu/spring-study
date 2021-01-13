package hello.core.order

import hello.core.AppConfig
import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OrderServiceTest {

    lateinit var memberService : MemberService
    lateinit var orderService : OrderService

    @BeforeEach
    fun beforeEach(){
        val appConfig = AppConfig()
        memberService = appConfig.memberService()
        orderService = appConfig.orderService()
    }
    @Test
    fun `create order`() {
        //given
        val memberId = 1L
        val member = Member(memberId, "memberA", Grade.VIP)
        memberService.join(member)

        //when
        val order = orderService.createOrder(memberId, "itmeA", 10000)

        //then
        assertThat(order.discountPrice).isEqualTo(1000)

    }
}