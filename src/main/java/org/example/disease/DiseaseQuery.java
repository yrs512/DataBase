package org.example.disease;

import lombok.Getter;
import lombok.Setter;
import org.example.disease.model.DiseaseDetail;

/**
 * 疾病查询服务
 */
public class DiseaseQuery {

    /**
     * 根据疾病ID查询详细信息
     * @param diseaseId 疾病ID
     * @return 疾病详细信息结果
     */
    public DiseaseResult queryDiseaseById(int diseaseId) {
        // 模拟API调用
        DiseaseResult result = new DiseaseResult();

        if (diseaseId > 0) {
            result.setCode(200);

            // 模拟数据
            DiseaseDetail disease = new DiseaseDetail();
            disease.setId(diseaseId);
            disease.setName("过敏性鼻炎");
            disease.setDescription("由过敏原引起的鼻黏膜非感染性炎症");

            result.setData(disease);
            result.setMsg("查询成功");
        } else {
            result.setCode(404);
            result.setMsg("疾病不存在");
        }

        return result;
    }

    /**
     * 查询结果封装
     */
    @Getter
    @Setter
    public static class DiseaseResult {
        private int code;           // 状态码
        private DiseaseDetail data; // 疾病详细信息
        private String msg;         // 消息
    }
}