package hello.core.order

import hello.core.annotationpackage.MainDiscountPolicy
import hello.core.discount.DiscountPolicy
import hello.core.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class OrderServiceImpl(
        val memberRepository: MemberRepository,
        @MainDiscountPolicy
        private val DiscountPolicy: DiscountPolicy
) : OrderService {
    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order {
        val member = memberRepository.findById(memberId)
        val discountPrice = DiscountPolicy.discount(member!!, itemPrice)

        return Order(memberId, itemName, itemPrice, discountPrice)
    }
}