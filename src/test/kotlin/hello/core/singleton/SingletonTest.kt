package hello.core.singleton

import hello.core.AppConfig
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class SingletonTest {

    @Test
    fun `pure Container without spring`() {
        val appConfig = AppConfig()

        // 1. 조회: 호출할때 마다 객체를 생성
        val memberService1 = appConfig.memberService()
        // 2. 조회: 호출할때 마다 객체를 생성
        val memberService2 = appConfig.memberService()

        // 참조값이 다른것을 확인
        //memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2)
    }

    @Test
    fun `singleton Container without spring`() {
        val singletonService1 = SingletonService.instance
        val singletonService2 = SingletonService.instance

        println(singletonService1)
        println(singletonService2)
        // 참조값이 같은것을 확인
        Assertions.assertThat(singletonService1).isSameAs(singletonService2)
    }

    @Test
    fun `singleton Container with spring`() {

        val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

        val memberService1 = ac.getBean("memberService")
        val memberService2 = ac.getBean("memberService")
        // 참조값이 같은것을 확인
        Assertions.assertThat(memberService1).isSameAs(memberService2)
    }
}