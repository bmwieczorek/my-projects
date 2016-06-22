package com.bawi.asyncservlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/asyncservlet"}, asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AsyncServlet.class.getName());
    private ExecutorService executorService = Executors.newFixedThreadPool(10);


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        write(response, "started processing");

        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.start(() -> {
            ServletResponse asyncResp = asyncCtx.getResponse();
            write(asyncResp, "resumed processing");

            longRunning(asyncCtx);

            //write(asyncResp, "finished processing processing");
            //asyncCtx.complete();
        });

    }

    private void longRunning(final AsyncContext asyncCtx) {

        List<String> strings = Arrays.asList("a", "bb", "ccc", "dddd");

        List<CompletableFuture<Double>> doublesCF = strings
            .stream()
            .map(s -> CompletableFuture.supplyAsync(() -> {
                    sleepSeconds(1);
                    LOGGER.info("Finished sleeping in supplyAsync");
                    return s.length();
                }, executorService)
                    .thenApplyAsync (i -> {
                            sleepSeconds(1);
                            LOGGER.info("Finished sleeping in thenApply");
                            return new Double(i);
                        }, executorService
                    )
            )
            .collect(Collectors.toList());

        CompletableFuture<Void> voidCF = CompletableFuture.allOf(doublesCF.toArray(new CompletableFuture[doublesCF.size()]));
        CompletableFuture<List<Double>> listOfDoublesCF = voidCF.thenApply(v -> doublesCF
            .stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList()));

        listOfDoublesCF.thenAccept(list -> write(asyncCtx.getResponse(), "size=" + list.size()))
                       .thenRun(asyncCtx::complete);
    }

    private static void sleepSeconds(int sleepSeconds) {
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void write(ServletResponse aresp, String message) {
        try {
            aresp.getWriter().write("[" + Thread.currentThread().getName() + "] " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}