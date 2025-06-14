package org.example.healthcare;

/**
 * 医保付款功能
 */
public class Pay {

    /**
     * 医保付款
     * @param amount 付款金额(单位:分)
     * @return 付款结果
     */
    public PaymentResult pay(long amount) {
        // 验证参数
        if (amount <= 0) {
            return new PaymentResult(400, null, "付款金额必须为正数");
        }

        // 模拟付款成功
        return new PaymentResult(200, null, "付款成功");
    }

    /**
     * 付款结果封装类
     */
    public static class PaymentResult {
        private int code;           // 状态码
        private Object data;        // 数据(此处为null)
        private String msg;         // 消息

        public PaymentResult(int code, Object data, String msg) {
            this.code = code;
            this.data = data;
            this.msg = msg;
        }

        // Getter方法
        public int getCode() {
            return code;
        }

        public Object getData() {
            return data;
        }

        public String getMsg() {
            return msg;
        }

        @Override
        public String toString() {
            return "PaymentResult{" +
                    "code=" + code +
                    ", data=" + data +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}