package hello.core

import hello.core.member.Grade
import hello.core.member.Member

fun main(args: Array<String>) {
    val appConfig = AppConfig()
    val memberService = appConfig.memberService()
    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)

    val findMember: Member? = memberService.findMember(1L)
    print(member.getName())
    print(findMember!!.getName())

}