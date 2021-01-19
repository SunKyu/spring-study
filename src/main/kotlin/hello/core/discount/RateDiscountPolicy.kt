package hello.core.discount

import hello.core.annotationpackage.MainDiscountPolicy
import hello.core.member.Grade
import hello.core.member.Member
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@MainDiscountPolicy
class RateDiscountPolicy : DiscountPolicy {
    private val discountPercent = 10;
    override fun discount(member: Member, price: Int): Int {
        return when (member.getGrade()) {
            Grade.VIP -> price / 100 * discountPercent
            else -> 0
        }
    }
}