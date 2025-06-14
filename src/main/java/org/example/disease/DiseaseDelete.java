package org.example.disease;

import lombok.Getter;
import lombok.Setter;

/**
 * 疾病删除服务
 */
public class DiseaseDelete {

    /**
     * 删除疾病
     * @param diseaseId 疾病ID
     * @return 删除结果
     */
    public DeleteResult deleteDisease(int diseaseId) {
        // 模拟API调用
        DeleteResult result = new DeleteResult();

        if (diseaseId > 0) {
            result.setCode(200);
            result.setMsg("删除成功");
        } else {
            result.setCode(404);
            result.setMsg("疾病不存在");
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