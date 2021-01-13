package hello.core.beanfind

import hello.core.AppConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextInfoTest {

    val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    fun `find All Bean`() {
        val beanDefintionNames = ac.beanDefinitionNames
        for (name in beanDefintionNames){
            val bean = ac.getBean(name)
            println("name = $name + object = ${bean.toString()}")
        }
    }
    @Test
    fun `find Application Bean`() {
        val beanDefintionNames = ac.beanDefinitionNames
        for (name in beanDefintionNames){
            val beanDefintion = ac.getBeanDefinition(name)
            if (beanDefintion.role == BeanDefinition.ROLE_APPLICATION) {
                val bean = ac.getBean(name)
                println("name = $name + object = ${bean.toString()}")
            }
        }
    }
}