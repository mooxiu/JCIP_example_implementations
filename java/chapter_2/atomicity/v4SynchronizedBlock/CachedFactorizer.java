package chapter_2.atomicity.v4SynchronizedBlock;

import jakarta.servlet.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class CachedFactorizer implements Servlet {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;

    // no need to use atomicLong here
    private long hits;
    private long cachedHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCachedHitRatio() {
        return (double) cachedHits / (double) hits;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cachedHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
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
