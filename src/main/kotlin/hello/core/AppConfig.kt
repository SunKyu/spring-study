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

class AppConfig {

    fun memberService() : MemberService {
        return MemberServiceImpl(memberRepository())
    }

    fun orderService() : OrderService {
        return OrderServiceImpl(memberRepository(), discountPolicy())
    }

    fun memberRepository() : MemberRepository {
        return MemoryMemberRepository()
    }

    fun discountPolicy() : DiscountPolicy {
        return RateDiscountPolicy()
    }
}