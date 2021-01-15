package hello.core.singleton

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean

internal class StatefulServiceTest{

    @Test
    fun `statefulServiceSingleton`() {
        val ac = AnnotationConfigApplicationContext(TestConfig::class.java)
        val statefulService1 = ac.getBean(StatefulService::class.java)
        val statefulService2 = ac.getBean(StatefulService::class.java)

        // Thread A : Order 10000won
        statefulService1.order("userA", 10000)
        // Thread B : Order 20000won
        statefulService2.order("userB", 20000)

        // Thread A : check price
        val price = statefulService1.price
        println("price = $price")


        // A는 10000원이 나오길 기대하지만 20000원이 나옴
        // 싱글톤이기때문에 문제가 됨.
        Assertions.assertThat(statefulService1.price).isEqualTo(20000)
        //price가 공유필드인데 다른 클라이언트가 값을 변경가능 -> stateless 하게 설계해야하는 이유
    }
    fun `statelessServiceSingleton`() {
        val ac = AnnotationConfigApplicationContext(TestConfig::class.java)
        val statefulService1 = ac.getBean(StatefulService::class.java)
        val statefulService2 = ac.getBean(StatefulService::class.java)

        // Thread A : Order 10000won
        statefulService1.order("userA", 10000)
        // Thread B : Order 20000won
        statefulService2.order("userB", 20000)

        // Thread A : check price
        val price = statefulService1.price
        println("price = $price")


        // A는 10000원이 나오길 기대하지만 20000원이 나옴
        // 싱글톤이기때문에 문제가 됨.
        Assertions.assertThat(statefulService1.price).isEqualTo(20000)
        //price가 공유필드인데 다른 클라이언트가 값을 변경가능 -> stateless 하게 설계해야하는 이유
    }

    companion object {
        class TestConfig{
            @Bean
            fun statefulService(): StatefulService{
                return StatefulService()
            }
        }
    }
}