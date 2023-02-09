package chapter_2.atomicity.v3MultiVariables;

import jakarta.servlet.*;
import utils.annotations.ThreadSafe;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class SynchronizedFactorizer implements Servlet {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;

    // safe but bad! even slower than single thread
    @Override
    public synchronized void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        if (i.equals(lastNumber)) {
            encodeIntoResponse(servletResponse, lastFactors);
        } else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            encodeIntoResponse(servletResponse, factors);
        }
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
