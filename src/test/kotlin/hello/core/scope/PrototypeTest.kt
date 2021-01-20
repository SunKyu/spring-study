package hello.core.scope

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class PrototypeTest {
    @Test
    fun prototypeBeanTest() {
        val ac = AnnotationConfigApplicationContext(PrototypeBean::class.java)

        println("find prototypeBean1")
        val prototypeBean1 = ac.getBean(PrototypeBean::class.java)
        println("find prototypeBean2")
        val prototypeBean2 = ac.getBean(PrototypeBean::class.java)

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2)
        // prototype scope 에서는 둘이 다르다.
        // postConstruct까지만 실행
        // Predestory는 하지 않음
        // client가 직접 관리해야함.
        ac.close()
        prototypeBean1.destory()
        prototypeBean2.destory()
    }

    companion object {
        @Scope("prototype")
        class PrototypeBean {

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