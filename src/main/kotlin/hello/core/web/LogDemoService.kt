package hello.core.web

import hello.core.common.MyLogger
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service


@Service
class LogDemoService (
        val myLogger: MyLogger
        ){
    fun logic(id: String) {
        //val myLogger = myLoggerProvider.getObject()
        myLogger.log("service id = $id")
    }


}