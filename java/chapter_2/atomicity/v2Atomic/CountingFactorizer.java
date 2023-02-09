package chapter_2.atomicity.v2Atomic;

import jakarta.servlet.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;


public class CountingFactorizer implements Servlet {
    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(servletResponse, factors);
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        BigInteger b = new BigInteger(String.valueOf(0));
        return b;
    }

    private void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
        resp.setCharacterEncoding(Arrays.toString(factors));
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

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
