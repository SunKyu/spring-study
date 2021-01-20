package hello.core.scope

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class SingletonWithProtoTypeTest1 {

    @Test
    fun prototypeFind() {
        val ac = AnnotationConfigApplicationContext(PrototypeBean::class.java)
        val prototypeBean1 = ac.getBean(PrototypeBean::class.java)
        prototypeBean1.addCount()
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1)

        val prototypeBean2 = ac.getBean(PrototypeBean::class.java)
        prototypeBean2.addCount()
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1)
    }

    @Test
    fun singletonClientUsePrototype() {
        val ac = AnnotationConfigApplicationContext(ClientBean::class.java, PrototypeBean::class.java)
        val clientBean1 = ac.getBean(ClientBean::class.java)
        val count1 = clientBean1.logic()
        Assertions.assertThat(count1).isEqualTo(1)

        val clientBean2 = ac.getBean(ClientBean::class.java)
        val count2 = clientBean2.logic()
        Assertions.assertThat(count2).isEqualTo(1)
    }
    companion object{
        @Scope("singleton")
        class ClientBean(@Autowired private val prototypeBeanProvider: ObjectProvider<PrototypeBean>){
            //ObjectProvider 를 사용해서 prototype 스코프 빈을 새로 받는다.
            // 핵심컨셉은 스피링 컨테이너를 찾기 쉽게 해주는것

            fun logic(): Int {
                val prototypeBean = prototypeBeanProvider.getObject()
                println(prototypeBean)
                prototypeBean.addCount()
                return prototypeBean.getCount()
            }

        }

        @Scope("prototype")
        class PrototypeBean{
            private var count = 0

            fun addCount(){
                count++
            }
            fun getCount() : Int{
                return count
            }

            @PostConstruct
            fun beanInit() {
                println("prototype bean init : $this")
            }
            @PreDestroy
            fun destroy() {
                println("prototype bean destroy")
            }

        }
    }
}