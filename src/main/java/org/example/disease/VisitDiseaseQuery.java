package org.example.disease;

import lombok.Getter;
import lombok.Setter;
import org.example.disease.model.DiseaseDetail;

import java.util.List;

/**
 * 问诊疾病查询服务
 */
public class VisitDiseaseQuery {

    /**
     * 根据问诊号查询疾病信息
     * @param visitId 问诊号
     * @return 疾病信息结果
     */
    public VisitDiseaseResult queryByVisitId(long visitId) {
        // 模拟API调用
        VisitDiseaseResult result = new VisitDiseaseResult();

        if (visitId > 0) {
            result.setCode(200);

            // 模拟数据
            DiseaseDetail disease1 = new DiseaseDetail();
            disease1.setId(1);
            disease1.setName("过敏性鼻炎");
            disease1.setDescription("由过敏原引起的鼻黏膜非感染性炎症");

            DiseaseDetail disease2 = new DiseaseDetail();
            disease2.setId(2);
            disease2.setName("过敏性结膜炎");
            disease2.setDescription("由过敏原引起的结膜炎症");

            result.setData(List.of(disease1, disease2));
            result.setMsg("查询成功");
        } else {
            result.setCode(404);
            result.setMsg("问诊记录不存在");
        }

        return result;
    }

    /**
     * 问诊疾病查询结果
     */
    @Getter
    @Setter
    public static class VisitDiseaseResult {
        private int code;               // 状态码
        private List<DiseaseDetail> data; // 疾病列表
        private String msg;             // 消息
    }
}