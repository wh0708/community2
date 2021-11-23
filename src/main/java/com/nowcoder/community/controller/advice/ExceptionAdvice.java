package com.nowcoder.community.controller.advice;

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author WH
 * @create 2021/11/20 16:18
 */
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);


    @ExceptionHandler({Exception.class})
    public void handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.error("服务器发送异常：" + e.getMessage());
        //遍历异常栈数组，输出完整信息
        for (StackTraceElement element : e.getStackTrace()) {
            logger.error(element.toString());
        }
        String xRequestWith = request.getHeader("x-requested-with");
        if ("XMLHttpRequest".equals(xRequestWith)) {
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(CommunityUtil.getJSONString(1, "服务器异常"));
        } else {
            response.sendRedirect(request.getContextPath() + "/error");
        }

    }


}
