package net.optifine.http;

import net.optifine.http.HttpRequest;
import net.optifine.http.HttpResponse;

public interface HttpListener {
    void finished(HttpRequest var1, HttpResponse var2);

    void failed(HttpRequest var1, Exception var2);
}

