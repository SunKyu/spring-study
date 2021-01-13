package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member

class RateDiscountPolicy : DiscountPolicy {
    private val discountPercent = 10;
    override fun discount(member: Member, price: Int): Int {
        return when (member.getGrade()) {
            Grade.VIP -> price / 100 * discountPercent
            else -> 0
        }
    }
}