package org.example.net.correspondence;


import org.example.net.vo.RestfulHttpResponse;

/**
 * 用于异步的监听器
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-05-08 10:39
 */
@FunctionalInterface
public interface ResponseListener {
     void listen(RestfulHttpResponse response);
}
