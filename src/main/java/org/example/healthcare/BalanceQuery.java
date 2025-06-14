package org.example.healthcare;

import lombok.Getter;
import lombok.Setter;

/**
 * 病号查询余额功能
 */
public class BalanceQuery {

    /**
     * 用病号查询医保余额
     * @param healthcareId 医保号
     * @return 包含余额的响应结果
     */
    public QueryResult queryByPatientId(int healthcareId) {
        // 实际应用中这里会调用数据库或医保系统接口
        // 模拟实现
        QueryResult result = new QueryResult();

        if (healthcareId > 0) {
            result.setCode(200);
            result.setData(50000); // 模拟余额500元(单位:分)
            result.setMsg("查询成功");
        } else {
            result.setCode(404);
            result.setMsg("记录不存在");
        }

        return result;
    }

    /**
     * 查询结果封装类
     */
    @Setter
    @Getter
    public static class QueryResult {
        // Getter和Setter方法
        private int code;       // 状态码
        private int data;       // 余额数据(单位:分)
        private String msg;     // 消息

        @Override
        public String toString() {
            return "QueryResult{" +
                    "code=" + code +
                    ", data=" + data +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}