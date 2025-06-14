package org.example.expenserecord;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 医疗费用记录查询
 */
public class ExpenseRecordQuery {

    /**
     * 查询问诊下的医疗费用记录
     * @param visitId 问诊号
     * @return 包含费用记录的响应结果
     */
    public ExpenseResult queryByVisitId(long visitId) {
        // 模拟API调用，实际应用中这里会调用REST API
        // 根据提供的API文档模拟响应

        ExpenseResult result = new ExpenseResult();

        if (visitId > 0) {
            result.setCode(200);

            // 模拟费用数据
            ExpenseRecord record1 = new ExpenseRecord();
            record1.setId(1);
            record1.setVisitDoctorId(visitId);
            record1.setCategory("诊疗费");
            record1.setDescription("专家门诊诊疗费");
            record1.setAmount(5000); // 50元

            ExpenseRecord record2 = new ExpenseRecord();
            record2.setId(2);
            record2.setVisitDoctorId(visitId);
            record2.setCategory("检查费");
            record2.setDescription("血常规检查");
            record2.setAmount(3000); // 30元

            result.setData(List.of(record1, record2));
            result.setMsg("查询成功");
        } else {
            result.setCode(404);
            result.setMsg("记录不存在");
        }

        return result;
    }

    /**
     * 费用记录查询结果封装类
     */
    @Setter
    @Getter
    public static class ExpenseResult {
        private int code;               // 状态码
        private List<ExpenseRecord> data; // 费用记录列表
        private String msg;             // 消息
    }

    /**
     * 费用记录实体类
     */
    @Setter
    @Getter
    public static class ExpenseRecord {
        private long id;               // 费用表id
        private long visitDoctorId;    // 就诊号/就诊表id
        private String category;       // 类别
        private String description;    // 描述
        private int amount;            // 金额(分)

        // 获取金额(元)
        public double getAmountInYuan() {
            return amount / 100.0;
        }
    }
}