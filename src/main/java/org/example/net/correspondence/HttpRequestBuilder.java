package org.example.net.correspondence;

import io.netty.handler.codec.http.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.net.JacksonUtil;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 一个headers对应一个Builder
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-05-08 02:38
 */
public class HttpRequestBuilder {

    private final List<Map.Entry<String, String>> headers;

    public HttpRequestBuilder() {
        headers = new ArrayList<>();
        this.initHeaders();
    }

    @AllArgsConstructor
    @Getter
    public static class Header implements Map.Entry<String, String> {
        private final String key;
        private String value;

        @Override
        public String setValue(String value) {
            return this.value = value;
        }
    }

    public HttpRequestBuilder resetHeaders(List<Header> headers) {
        this.headers.clear();
        initHeaders();
        this.headers.addAll(headers);
        return this;
    }

    private void initHeaders() {
        addHeader(HttpHeaderNames.CONNECTION.toString(), HttpHeaderValues.KEEP_ALIVE.toString());
        addHeader(HttpHeaderNames.CONTENT_TYPE.toString(), HttpHeaderValues.APPLICATION_JSON.toString());
        addHeader(HttpHeaderNames.TRANSFER_ENCODING.toString(), HttpHeaderValues.CHUNKED.toString());
    }

    public HttpRequestBuilder addHeader(String header, String value) {
        this.headers.add(new Header(header, value));
        return this;
    }

    public HttpRequest buildRequest(
            HttpMethod httpMethod, URI uri, String jsonBody) {
        // 通断被激活时我们发送请求到指定路径
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, httpMethod, uri.toASCIIString());
        request.content().writeBytes(jsonBody.getBytes(Charset.defaultCharset()));
        for (Map.Entry<String, String> header : headers) {
            request.headers().add(header.getKey(), header.getValue());
        }


        // 将请求实体写入出站处理器
        return request;
    }

    public HttpRequest buildGetRequest(URI uri) {
        return buildRequest(HttpMethod.GET, uri, "");
    }


    public <T> HttpRequest buildPostRequest(URI uri, T body) {
        return buildRequest(HttpMethod.POST, uri, JacksonUtil.toJsonStr(body));
    }

    public <T> HttpRequest buildPutRequest(URI uri, T body) {
        return buildRequest(HttpMethod.PUT, uri, JacksonUtil.toJsonStr(body));
    }

    public <T> HttpRequest buildDeleteRequest(URI uri) {
        return buildRequest(HttpMethod.DELETE, uri, "");
    }
}
