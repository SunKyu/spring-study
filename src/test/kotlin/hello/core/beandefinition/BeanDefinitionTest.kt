package hello.core.beandefinition

import hello.core.AppConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class BeanDefinitionTest {

    val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    fun `find application Bean`() {
        val beanDefinitionNames = ac.beanDefinitionNames
        for (beanDefinitionName in beanDefinitionNames){
            val beanDefinition = ac.getBeanDefinition(beanDefinitionName)
            if (beanDefinition.role === BeanDefinition.ROLE_APPLICATION){
                println("beanDefintionName = $beanDefinitionName beanDefintion = $beanDefinition" )
            }
        }

    }
}