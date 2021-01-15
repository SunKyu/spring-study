package hello.core.singleton

import hello.core.AppConfig
import hello.core.member.MemberRepository
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConfigurationSingletonTest {
    @Test
    fun configuationTest() {
        val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

        val memberService = ac.getBean("memberService", MemberServiceImpl::class.java)
        val orderService = ac.getBean("orderService", OrderServiceImpl::class.java)
        val memberRepository = ac.getBean("memberRepository", MemberRepository::class.java)

        var memberRepository1 = memberService.memberRepository
        var memberRepository2 = orderService.memberRepository

        Assertions.assertThat(memberService.memberRepository).isSameAs(memberRepository)
        Assertions.assertThat(orderService.memberRepository).isSameAs(memberRepository)


    }

}