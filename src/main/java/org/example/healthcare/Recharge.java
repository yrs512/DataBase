package org.example.healthcare;

/**
 * 医保充值功能
 */
public class Recharge {

    /**
     * 医保充值
     * @param amount 充值金额(单位:分)
     * @param identifierCardId 身份证号
     * @param queryBasisId 查询依据ID
     * @return 充值结果
     */
    public RechargeResult recharge(int amount, String identifierCardId, int queryBasisId) {
        // 验证参数
        if (amount <= 0) {
            return new RechargeResult(400, 0, "充值金额必须为正数");
        }

        if ((identifierCardId == null || identifierCardId.isEmpty()) && queryBasisId <= 0) {
            return new RechargeResult(400, 0, "必须提供身份证号或查询依据ID");
        }

        // 模拟充值成功
        int newBalance = 100000 + amount; // 假设原余额为1000元

        return new RechargeResult(200, newBalance, "充值成功");
    }

    /**
     * 充值结果封装类
     */
    public static class RechargeResult {
        private int code;       // 状态码
        private int data;       // 充值后的余额(单位:分)
        private String msg;     // 消息

        public RechargeResult(int code, int data, String msg) {
            this.code = code;
            this.data = data;
            this.msg = msg;
        }

        // Getter方法
        public int getCode() {
            return code;
        }

        public int getData() {
            return data;
        }

        public String getMsg() {
            return msg;
        }

        @Override
        public String toString() {
            return "RechargeResult{" +
                    "code=" + code +
                    ", data=" + data +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}