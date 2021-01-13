package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberService
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>) {
    //val appConfig = AppConfig()
    //val memberService = appConfig.memberService()

    var applicationContext : ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService = applicationContext.getBean("memberService", MemberService::class.java)
    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)

    val findMember: Member? = memberService.findMember(1L)
    print(member.getName())
    print(findMember!!.getName())

}