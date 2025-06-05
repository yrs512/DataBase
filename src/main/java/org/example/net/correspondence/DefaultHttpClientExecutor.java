package org.example.net.correspondence;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.net.vo.RestfulHttpResponse;

/**
 * {@link HttpClientExecutor} 的实现
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-05-08 00:26
 */
@Slf4j
class DefaultHttpClientExecutor implements HttpClientExecutor {
    private final HttpClientManager manager;

    DefaultHttpClientExecutor(HttpClientManager manager) {
        this.manager = manager;
    }

    @Override
    public RestfulHttpResponse execute() {
        execute(null);
        return syncReceive();
    }

    @Override
    public void execute(ResponseListener listener) {
        try {
            synchronized (this) {
                // 代理类
                Channel channel = this.manager.peekTask().channel;
                channel.closeFuture().addListener(
                        future -> {
                            if (listener != null) {
                                listener.listen(syncReceive());
                            }
                        }
                ).addListener(future -> {
                    channel.close();
                    manager.poolTask();
                });
            }
        } catch (Throwable e) {
            log.error("client error", e);
            throw e;
        }
    }


    private RestfulHttpResponse syncReceive() {
        // 放入Map
        try {
            // 等待响应
            CorrespondenceTask task = manager.peekTask();
            task.headerPromise.await(); // sync() 会自动抛异常, await不会自动抛异常
            task.contentPromise.await();
            // 我们要自己通过Success方法来检查
            if (task.headerPromise.isSuccess() && task.contentPromise.isSuccess()) {
                return new RestfulHttpResponse(
                        task.headerPromise.get(),
                        task.contentPromise.get()
                );
            } else if (!task.headerPromise.isSuccess()) {
                throw task.headerPromise.cause();
            } else if (!task.contentPromise.isSuccess()) {
                throw task.contentPromise.cause();
            }
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }


}
