package hello.core.autowired

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.lang.Nullable
import java.lang.reflect.Member
import java.util.*

class AutowiredTest {

    @Test
    fun autoWiredOption() {
        val ac = AnnotationConfigApplicationContext(TestBean::class.java)

    }

    companion object {
        class TestBean(){
            @Autowired(required = false)
            fun setNoBean1(noBean1: Member){
                println("noBean1 = $noBean1")
            }
            @Autowired
            fun setNoBean2(noBean2: Member?){
                println("noBean1 = $noBean2")
            }

            @Autowired
            fun setNoBean3(noBean3: Optional<Member>){
                println("noBean1 = $noBean3")
            }

        }

    }
}