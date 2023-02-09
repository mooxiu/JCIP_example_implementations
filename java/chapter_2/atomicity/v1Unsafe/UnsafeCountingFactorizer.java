package chapter_2.atomicity.v1Unsafe;

import jakarta.servlet.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class UnsafeCountingFactorizer implements Servlet {
    private long count = 0;
    public long getCount() {
        return count;
    }
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(i);
        ++count;
        encodeIntoResponse(servletResponse, factors);
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        BigInteger b = new BigInteger(String.valueOf(0));
        return b;
    }

    private BigInteger[] factor(BigInteger b) {
        return new BigInteger[]{};
    }


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
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
