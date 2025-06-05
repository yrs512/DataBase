package org.example.net.correspondence;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;
import org.example.net.properties.NetProperties;
import org.example.net.vo.RestfulHttpResponse;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 不要多开, 多开损耗资源
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-05-08 02:22
 */
public class HttpClientManager {
    public static final String HOST = NetProperties.DEFAULT.get(NetProperties.PropertyName.HOST);
    public static final int PORT = Integer.parseInt(NetProperties.DEFAULT.get(NetProperties.PropertyName.PORT));
    public static final LogLevel LOG_LEVEL = LogLevel.INFO;
    private final NioEventLoopGroup group = new NioEventLoopGroup();
    private final Bootstrap bootstrap;

    // 搞一个任务队列
    //  request-chanel-executor(内涵promise)
    private final ConcurrentLinkedQueue<CorrespondenceTask> queue = new ConcurrentLinkedQueue<>();

    public HttpClientManager() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
        bootstrap = initBootstrap();
    }

    private Bootstrap initBootstrap() {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(group);
        ChannelInitializer<SocketChannel> handlerInitializer = getHandlerInitializer();
        bootstrap.handler(handlerInitializer);
        return bootstrap;
    }

    private void close() {
        group.shutdownGracefully();
    }

    private ChannelInitializer<SocketChannel> getHandlerInitializer() {
        LoggingHandler loggingHandler = new LoggingHandler(LOG_LEVEL);
        HttpClientCodec messageCodec = new HttpClientCodec();
        SimpleChannelInboundHandler<DefaultHttpResponse> responseHandler = new SimpleChannelInboundHandler<>() {
            @Override
            public void channelActive(ChannelHandlerContext ctx) {
                // 创建开始就报废 , 合理吗?
                HttpRequest request = peekTask().request;
                ctx.writeAndFlush(request);
            }

            @Override
            public void channelRead0(ChannelHandlerContext ctx, DefaultHttpResponse response) {
                // 即时删除Map中无用的promise
                // promise set 之后, 就会唤醒await()
                Iterable<Map.Entry<String, String>> headers = response.headers();
                DefaultPromise<Iterable<Map.Entry<String, String>>> requestPromise = peekTask()
                        .buildRequestPromise(ctx.channel());
                requestPromise.setSuccess(headers);
                ctx.writeAndFlush(response);
            }

        };
        SimpleChannelInboundHandler<DefaultHttpContent> contentHandler = new SimpleChannelInboundHandler<>() {
            @Override
            protected void channelRead0(
                    ChannelHandlerContext ctx, DefaultHttpContent content) {
                DefaultPromise<String> contentPromise = peekTask().buildContentPromise(ctx.channel());
                contentPromise.setSuccess(content.content().toString(Charset.defaultCharset()));
            }
        };
        return new ChannelInitializer<>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(loggingHandler);
                pipeline.addLast(messageCodec);
                pipeline.addLast(responseHandler);
                pipeline.addLast(contentHandler);
            }
        };
    }

    CorrespondenceTask peekTask() {
        CorrespondenceTask task = queue.peek();
        if (task == null) {
            throw new RuntimeException("需要先构建任务");
        }
        return task;
    }

    void poolTask() {
        this.queue.poll();
    }


    public RestfulHttpResponse execute(HttpRequest request) throws InterruptedException {
        // 假装服务器返回成功
        return new RestfulHttpResponse(
                List.of(),
                "{\"code\":200, \"data\":{}, \"msg\":\"登录成功\"}"
        );
    }

    public void execute(HttpRequest request, ResponseListener listener) throws InterruptedException {
        CorrespondenceTask task = new CorrespondenceTask(request);
        queue.offer(task);
        ChannelFuture channelFuture = this.bootstrap.connect(HOST, PORT);
        channelFuture.addListener(future -> {
            task.channel = channelFuture.sync().channel();
            HttpClientExecutor executor = new DefaultHttpClientExecutor(this);
            executor.execute(listener);
        });

    }


}
