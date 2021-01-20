package hello.core.web

import hello.core.common.MyLogger
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest


@Controller
class LogDemoController(
        val logDemoService: LogDemoService,
        val myLogger: MyLogger
) {
    @RequestMapping("log-demo")
    @ResponseBody
    fun logDemo(request: HttpServletRequest): String{
        val requestURL = request.requestURL.toString()
        //val myLogger = myLoggerProvider.getObject()
        myLogger.setRequestURL(requestURL)

        myLogger.log("controller test")
        logDemoService.logic("testID")
        return "OK"
    }


}