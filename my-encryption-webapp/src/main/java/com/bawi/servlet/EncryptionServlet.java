package com.bawi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bawi.encryption.Decrypter;
import com.bawi.encryption.Encrypter;
import com.bawi.encryption.SecretKeyProvider;

public class EncryptionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String KEY_RESOURCE = "encryption.key";

    private final SecretKey key = new SecretKeyProvider().readBase64EncodedKeyFromResource(KEY_RESOURCE);
    private final Encrypter encrypter = new Encrypter(key);
    private final Decrypter decrypter = new Decrypter(key);

    private enum Action {
        encrypt, decrypt;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        String url = req.getRequestURL().toString();
        String action = req.getParameter("action");
        String data = req.getParameter("data");

        if (invalidParameters(action, data)) {
            printHeader(out);
            printUsage(out, url);
            return;
        }

        switch (Action.valueOf(action)) {
        case encrypt:
            out.println(encrypter.encryptToBase64Encoded(data));
            return;
        case decrypt:
            out.println(decrypter.decryptBase64Encoded(data));
            return;
        default:
            throw new UnsupportedOperationException(action);
        }

    }

    private boolean invalidParameters(String action, String data) {
        return action == null || data == null || !matchesAction(action);
    }

    private boolean matchesAction(String action) {
        return Action.decrypt.toString().equals(action) || Action.encrypt.toString().equals(action);
    }

    private void printHeader(PrintWriter out) {
        startHtml(out);
        out.println("**************************************************************************************************");
        htmlBR(out);
        out.println("* Encryption/decryption tool. Decrypts strings from / encrypts string to base 64 encoded format *");
        htmlBR(out);
        out.println("**************************************************************************************************");
        htmlBR(out);
        htmlBR(out);
    }

    private void printUsage(PrintWriter w, String url) {
        String plain = "HelloWorld!";
        String encrypted = "rfbznJqIiss07e9whQCQEA==";
        w.println("Usage: " + url + "?action=[" + Action.decrypt + "|" + Action.encrypt + "]&data=[data]");
        htmlBR(w);
        htmlBR(w);
        w.print("i.e: " + createHtmlLink(url + "?action=decrypt&data=" + encrypted) + " returns " + plain);
        htmlBR(w);
        w.println("i.e: " + createHtmlLink(url + "?action=encrypt&data=" + plain) + " returns " + encrypted);
    }

    private String createHtmlLink(String link) {
        return "<a href=\"" + link + "\">" + link + "</a>";
    }

    private void startHtml(PrintWriter out) {
        out.print("<html>");
        out.print("<head><title>Encryption/decryption tool</title></head>");
        out.print("<body>");
    }

    private void htmlBR(PrintWriter out) {
        out.println("</br>");
    }
}
