package hello.core.beanfind

import hello.core.AppConfig
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextBasicFindTest {

    val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("빈 이름으로 조회")
    fun `find bean by Name`() {
        val memberService = ac.getBean("memberService", MemberService::class.java)
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl::class.java)
    }
    @Test
    @DisplayName("이름없이 타입으로만 조회")
    fun `find bean by Type`() {
        val memberService = ac.getBean(MemberService::class.java)
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl::class.java)
    }
    @Test
    @DisplayName("구체 타입으로 조회")
    fun `find bean by Name2`() {
        val memberService = ac.getBean("memberService", MemberServiceImpl::class.java)
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl::class.java)
    }

    @Test
    fun `find Bean By doesn't exist Name`() {
        assertThrows<NoSuchBeanDefinitionException> {ac.getBean("xxx", MemberService::class.java)  }
    }
}