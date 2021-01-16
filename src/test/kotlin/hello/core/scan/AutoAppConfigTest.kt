package hello.core.scan

import hello.core.AutoAppConfig
import hello.core.member.MemberService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.lang.AssertionError

class AutoAppConfigTest {
    @Test
    fun basicScan() {
        val ac = AnnotationConfigApplicationContext(AutoAppConfig::class.java)

        val memberService = ac.getBean(MemberService::class.java)

        Assertions.assertThat(memberService).isInstanceOf(MemberService::class.java)

    }
}