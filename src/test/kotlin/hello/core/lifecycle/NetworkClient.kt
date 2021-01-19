package hello.core.lifecycle

import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class NetworkClient {
    private var url: String? = null

    init {
        println("init 호출, url = $url")
        connect()
        call("초기화 연결 메시지")
    }

    fun setUrl(url: String){
        this.url = url
    }

    fun connect() {
        println("connect: $url")
    }

    fun call(message: String){
        println("call: $url,  message = $message")
    }

    fun disconnect() {
        println("close $url")

    }

    @PostConstruct
    fun initClient() {
        //의존관계 주입이 끝난후 호출
        connect()
        call("초기화 연결 메시지")
    }

    @PreDestroy
    fun close() {
        //빈이 종료될때
        disconnect()
    }

}