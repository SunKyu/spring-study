package hello.core.beanfind

import hello.core.AppConfig
import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.discount.RateDiscountPolicy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.jvm.javaClass as javaClass1

class ApplicationContextExtendsFindTest {

    val ac = AnnotationConfigApplicationContext(TestConfig::class.java)

    @Test
    fun `throw error when find beans by parents Type then more than 2 results`() {
        assertThrows<NoUniqueBeanDefinitionException> {
            val bean = ac.getBean(DiscountPolicy::class.java)
        }

    }
    @Test
    fun `when more than 2 same type of beans there, assign bean name`() {
        val rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy::class.java)
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy::class.java)
    }

    @Test
    fun `find Bean by sub type`() {
        val rateDiscountPolicy = ac.getBean(RateDiscountPolicy::class.java)
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy::class.java)
    }
    @Test
    fun `find all beans by parent type`() {
        val beansOfType = ac.getBeansOfType(DiscountPolicy::class.java)
        assertThat(beansOfType.size).isEqualTo(2)
    }


    companion object {
        @Configuration
        class TestConfig {

            @Bean
            fun rateDiscountPolicy(): DiscountPolicy {
                return RateDiscountPolicy()
            }

            @Bean
            fun fixDiscountPolicy(): DiscountPolicy {
                return FixDiscountPolicy()
            }
        }
    }
}