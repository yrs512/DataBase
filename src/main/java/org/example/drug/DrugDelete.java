package org.example.drug;

import lombok.Getter;
import lombok.Setter;

/**
 * 药物删除服务
 */
public class DrugDelete {

    /**
     * 删除药品
     * @param drugId 药品ID
     * @return 删除结果
     */
    public DeleteResult deleteDrug(int drugId) {
        // 模拟API调用
        DeleteResult result = new DeleteResult();

        if (drugId > 0) {
            result.setCode(200);
            result.setMsg("删除成功");
        } else {
            result.setCode(404);
            result.setMsg("药品不存在");
        }

        return result;
    }

    /**
     * 删除结果封装
     */
    @Getter
    @Setter
    public static class DeleteResult {
        private int code;       // 状态码
        private Object data;    // 数据(通常为null)
        private String msg;     // 消息
    }
}