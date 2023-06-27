package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
                // HandlerExceptionResolver 반환 값에 따른 DispatcherServlet의 동작방식
                // ``빈 ModelAndView` : 뷰 렌더링 X. 정상흐름으로 서블릿 return
                // `ModelAndView 지정` : 뷰 렌더링
                // `null` : 다음 ExceptionResolver 찾아서 실행. 처리할 수 있는 ExceptionResolver가 없다면 ? 기존 발생 예외를 서블릿 밖으로 던짐
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }
        return null;
    }
}
