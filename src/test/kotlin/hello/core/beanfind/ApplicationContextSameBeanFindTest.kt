package hello.core.beanfind

import hello.core.AppConfig
import hello.core.member.MemberRepository
import hello.core.member.MemoryMemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextSameBeanFindTest {

    val ac = AnnotationConfigApplicationContext(SameBeanConfig::class.java)

    @Test
    fun `throw errors when find bean using by type and there're more than 2 bean with same type`() {
        assertThrows<NoUniqueBeanDefinitionException> {
            ac.getBean(MemberRepository::class.java)
        }
    }
    @Test
    fun `when more than 2 same type of beans there, assign bean name`() {
        val memberRepository = ac.getBean("memberRepository1", MemberRepository::class.java)
        assertThat(memberRepository).isInstanceOf(MemberRepository::class.java)
    }

    @Test
    fun `find All Bean By Type`() {
        val memberRepositorys = ac.getBeansOfType(MemberRepository::class.java)
        for (key in memberRepositorys.keys){
            println("key : $key")
        }
        assertThat(memberRepositorys.size).isEqualTo(2)
    }
    companion object {
        @Configuration
        class SameBeanConfig {
            @Bean
            fun memberRepository1() : MemberRepository {
                return MemoryMemberRepository()
            }
            @Bean
            fun memberRepository2() : MemberRepository {
                return MemoryMemberRepository()
            }
        }
    }
}