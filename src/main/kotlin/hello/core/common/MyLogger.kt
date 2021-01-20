package hello.core.common

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class MyLogger {

    private var uuid : String? = null
    private var requestURL : String? = null

    fun setRequestURL(requestURL: String) {
        this.requestURL = requestURL
    }

    fun log(message: String){
        println("[$uuid][$requestURL] $message")
    }

    @PostConstruct
    fun myLoggerInit(){
        uuid = UUID.randomUUID().toString()
        println("[$uuid] rquest scope bean create: $this")
    }

    @PreDestroy
    fun close(){
        println("[$uuid] rquest scope bean close: $this")
    }
}