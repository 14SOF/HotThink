package skhu.sof14.hotthink.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @GetMapping("error")
    public String error(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        //404
        if(status!=null){
            int code = Integer.parseInt(status.toString());
            if(code == HttpStatus.NOT_FOUND.value()) return "error/404";
        }

        return null;
    }
}
