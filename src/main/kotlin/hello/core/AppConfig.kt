package hello.core

import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.discount.RateDiscountPolicy
import hello.core.member.MemberRepository
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import hello.core.member.MemoryMemberRepository
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean
    fun memberService() : MemberService {
        return MemberServiceImpl(memberRepository())
    }

    @Bean
    fun orderService() : OrderService {
        return OrderServiceImpl(memberRepository(), discountPolicy())
    }

    @Bean
    fun memberRepository() : MemberRepository {
        return MemoryMemberRepository()
    }

    @Bean
    fun discountPolicy() : DiscountPolicy {
        return RateDiscountPolicy()
    }
}