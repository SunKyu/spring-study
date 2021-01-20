package hello.core.scope

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class SingletonTest {

    @Test
    fun singletonBeanFind() {
        val ac = AnnotationConfigApplicationContext(SingletonBean::class.java)

        val singletonBean1 = ac.getBean(SingletonBean::class.java)
        val singletonBean2 = ac.getBean(SingletonBean::class.java)

        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2)
    }

    companion object {
        @Scope("singleton")
        class SingletonBean {

            @PostConstruct
            fun beanInit() {
                println("singletonBean init")
            }

            @PreDestroy
            fun destory(){
                println("singleBean destory")
            }
        }

    }
}