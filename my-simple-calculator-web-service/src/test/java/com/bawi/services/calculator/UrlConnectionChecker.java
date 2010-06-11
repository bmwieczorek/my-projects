package com.bawi.services.calculator;

import static java.lang.Thread.sleep;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.net.ConnectException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

public class UrlConnectionChecker {

    public interface ServiceValidityEvaluator {
        boolean verifyExpession(HttpMethodBase httpMethod) throws Exception;
    }

    private static Logger logger = Logger.getLogger(UrlConnectionChecker.class);
    private static final int DEFAULT_TIMEOUT_SECONDS = 30;
    private static final int DEFAULT_RETRY_INTERVAL_SECONDS = 1;
    private static final int DEFAULT_WARMUP_SECONDS = 1;

    private final HttpClient client = new HttpClient();
    private final int timeoutSeconds;
    private final int retryIntervalSeconds;
    private final int warmUpSeconds;

    // ------------------------------------ //
    // ----------- Creation --------------- //
    // ------------------------------------ //

    UrlConnectionChecker(int warmUpSeconds, int timeoutSeconds, int retryIntervalSeconds) {
        this.warmUpSeconds = warmUpSeconds;
        this.timeoutSeconds = timeoutSeconds;
        this.retryIntervalSeconds = retryIntervalSeconds;
    }

    public UrlConnectionChecker() {
        this(DEFAULT_WARMUP_SECONDS, DEFAULT_TIMEOUT_SECONDS, DEFAULT_RETRY_INTERVAL_SECONDS);
    }

    public static UrlConnectionChecker create() {
        return new UrlConnectionChecker(DEFAULT_WARMUP_SECONDS, DEFAULT_TIMEOUT_SECONDS,
                DEFAULT_RETRY_INTERVAL_SECONDS);
    }

    public static UrlConnectionChecker createWithWarmUp(int warmUpSeconds) {
        return new UrlConnectionChecker(warmUpSeconds, DEFAULT_TIMEOUT_SECONDS,
                DEFAULT_RETRY_INTERVAL_SECONDS);
    }

    public static UrlConnectionChecker createWithTimeOut(int timeoutSeconds) {
        return new UrlConnectionChecker(DEFAULT_WARMUP_SECONDS, timeoutSeconds,
                DEFAULT_RETRY_INTERVAL_SECONDS);
    }

    public static UrlConnectionChecker createWithTimeOutAndRertyInterval(int timeoutSeconds, int retryInverval) {
        return new UrlConnectionChecker(DEFAULT_WARMUP_SECONDS, timeoutSeconds, retryInverval);
    }

    public static UrlConnectionChecker createWithWarmUpAndTimeOutAndRertyInterval(int warmUpSeconds,
            int timeoutSeconds, int retryInverval) {
        return new UrlConnectionChecker(warmUpSeconds, timeoutSeconds, retryInverval);
    }

    // ------------------------------------ //
    // ----- State validation methods ----- //
    // ------------------------------------ //

    public boolean isStatusCodeOK(String url) throws Exception {
        return isUp(url, new ServiceValidityEvaluator() {

            @Override
            public boolean verifyExpession(HttpMethodBase httpMethod) {
                return HttpStatus.SC_OK == httpMethod.getStatusCode();
            }
        });
    }

    public boolean isHasNonEmptyContent(String url) throws Exception {
        return isUp(url, new ServiceValidityEvaluator() {

            @Override
            public boolean verifyExpession(HttpMethodBase httpMethod) throws Exception {
                return isNotEmpty(httpMethod.getResponseBodyAsString());
            }
        });
    }

    public boolean isUp(String url) throws Exception {
        return isUp(url, new ServiceValidityEvaluator() {

            @Override
            public boolean verifyExpession(HttpMethodBase httpMethod) throws Exception {
                return HttpStatus.SC_OK == httpMethod.getStatusCode()
                        && isNotEmpty(httpMethod.getResponseBodyAsString());
            }
        });
    }

    public boolean isUp(String url, ServiceValidityEvaluator evaluator) throws Exception {

        validateNotNegative(warmUpSeconds);

        sleep(warmUpSeconds * 1000);

        GetMethod method = new GetMethod(url);

        for (int i = 0; i < timeoutSeconds; i = i + retryIntervalSeconds) {
            try {
                logger.debug("Trying " + url + " ...");
                client.executeMethod(method);
                if (evaluator.verifyExpession(method)) {
                    logger.debug("Successfully connected to " + url + " !");
                    return true;
                }
            } catch (ConnectException e) {
                logger.debug("Could not connect to " + url + " (" + e.getMessage() + "). Retrying in "
                        + retryIntervalSeconds + " second(s).");
                Thread.sleep(retryIntervalSeconds * 1000);
            } finally {
                method.releaseConnection();
            }
        }
        logger.debug("Failed to establish connection to " + url + " !");
        return false;
    }

    private static void validateNotNegative(int warmUpSeconds) {
        if (warmUpSeconds < 0) {
            throw new IllegalArgumentException("Warm up time cannot be negative: " + warmUpSeconds);
        }
    }

}
