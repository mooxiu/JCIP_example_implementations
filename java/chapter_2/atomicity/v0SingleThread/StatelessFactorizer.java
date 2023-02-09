package chapter_2.atomicity.v0SingleThread;

import jakarta.servlet.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class StatelessFactorizer implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(servletResponse, factors);
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        BigInteger b = new BigInteger(String.valueOf(0));
        return b;
    }

    private BigInteger[] factor(BigInteger b) {
        return new BigInteger[]{};
    }

    private void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
        resp.setCharacterEncoding(Arrays.toString(factors));
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
