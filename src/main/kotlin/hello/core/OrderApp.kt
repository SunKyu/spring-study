package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberServiceImpl
import hello.core.order.OrderServiceImpl

fun main(args: Array<String>){
    val appConfig = AppConfig()
    val memberService = appConfig.memberService()
    val orderService = appConfig.orderService()

    val memberId:Long = 1L
    val member = Member(memberId, "memberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId, "itemA", 10000)

    println(order)

}