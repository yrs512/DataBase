package org.example.disease;

import lombok.Getter;
import lombok.Setter;
import org.example.disease.model.DiseaseDetail;

import java.util.List;

/**
 * 疾病模糊查询服务
 */
public class DiseaseFuzzyQuery {

    /**
     * 根据疾病名称模糊查询
     * @param name 疾病名称(模糊)
     * @param page 页码(从1开始)
     * @param limit 每页数量
     * @return 疾病列表结果
     */
    public DiseaseListResult fuzzyQueryByName(String name, int page, int limit) {
        // 模拟API调用
        DiseaseListResult result = new DiseaseListResult();

        if (name != null && !name.trim().isEmpty()) {
            result.setCode(200);

            // 模拟数据
            DiseaseDetail disease1 = new DiseaseDetail();
            disease1.setId(1);
            disease1.setName("过敏性鼻炎");
            disease1.setDescription("由过敏原引起的鼻黏膜非感染性炎症");

            DiseaseDetail disease2 = new DiseaseDetail();
            disease2.setId(2);
            disease2.setName("过敏性哮喘");
            disease2.setDescription("由过敏原引起的气道慢性炎症性疾病");

            result.setData(List.of(disease1, disease2));
            result.setMsg("查询成功");
        } else {
            result.setCode(400);
            result.setMsg("疾病名称不能为空");
        }

        return result;
    }

    /**
     * 疾病列表查询结果
     */
    @Getter
    @Setter
    public static class DiseaseListResult {
        private int code;               // 状态码
        private List<DiseaseDetail> data; // 疾病列表
        private String msg;             // 消息
    }
}