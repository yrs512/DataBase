package org.example.net.vo;


import lombok.Getter;
import org.example.net.JacksonUtil;
import org.example.net.correspondence.HttpRequestBuilder;
import org.example.net.properties.NetProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 符合Restful风格的Result
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-05-08 02:19
 */
@Getter
public class RestfulHttpResponse {
    public static final String CODE_FIELD = NetProperties.DEFAULT.get(NetProperties.PropertyName.CODE_FIELD);
    public static final String DATA_FIELD = NetProperties.DEFAULT.get(NetProperties.PropertyName.DATA_FIELD);
    public static final String MESSAGE_FIELD = NetProperties.DEFAULT.get(NetProperties.PropertyName.MESSAGE_FIELD);
    private final Iterable<HttpRequestBuilder.Header> headers;
    private final int code;
    private final String message;
    private final String data;

    public RestfulHttpResponse(Iterable<Map.Entry<String, String>> headers, String content) {
        List<HttpRequestBuilder.Header> headersTemp = new ArrayList<>();
        for (var header : headers) {
            headersTemp.add(new HttpRequestBuilder.Header(header.getKey(), header.getValue()));}
        this.headers = headersTemp;
        Map<String, String> map = JacksonUtil.toStringMap(content);
        this.code = Integer.parseInt(map.get(CODE_FIELD));//"code";
        this.data = map.get(DATA_FIELD);//data;
        this.message = map.get(MESSAGE_FIELD);//msg;
    }

    public <T> T getData(Class<T> targetType) {
        return JacksonUtil.toBean(data, targetType);
    }

    public boolean success() {
        return code == 200;
    }

    @Override
    public String toString() {
        return "RestfulHttpResponse{" +
                "headers=" + headers +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
