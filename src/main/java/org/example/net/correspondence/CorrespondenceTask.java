package org.example.net.correspondence;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.concurrent.DefaultPromise;

import java.util.Map;

/**
 * TODO
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-05-08 11:41
 */
class CorrespondenceTask {
    Channel channel;
    final HttpRequest request;
    DefaultPromise<String> contentPromise;
    DefaultPromise<Iterable<Map.Entry<String, String>>> headerPromise;

    CorrespondenceTask(HttpRequest request) {
        this.request = request;
    }

    public DefaultPromise<String> buildContentPromise(Channel channel) {
        this.channel = channel;
        return this.contentPromise = new DefaultPromise<>(channel.eventLoop());
    }

    public DefaultPromise<Iterable<Map.Entry<String, String>>> buildRequestPromise(Channel channel) {
        this.channel = channel;
        return this.headerPromise = new DefaultPromise<>(channel.eventLoop());
    }
}
