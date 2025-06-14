package org.example.drug;

import lombok.Getter;
import lombok.Setter;
import org.example.drug.model.FillDrugDto;

import java.util.List;

/**
 * 抓药信息查询服务
 */
public class PrescriptionQuery {

    /**
     * 根据问诊号查询抓药信息
     * @param visitId 问诊号
     * @return 抓药信息结果
     */
    public PrescriptionResult queryByVisitId(long visitId) {
        // 模拟API调用
        PrescriptionResult result = new PrescriptionResult();

        if (visitId > 0) {
            result.setCode(200);

            // 模拟数据
            FillDrugDto drug1 = new FillDrugDto();
            drug1.setName("阿莫西林胶囊");
            drug1.setSpecification("0.25g*24粒/盒");
            drug1.setCount(2);

            FillDrugDto drug2 = new FillDrugDto();
            drug2.setName("布洛芬缓释胶囊");
            drug2.setSpecification("0.3g*20粒/盒");
            drug2.setCount(1);

            result.setData(List.of(drug1, drug2));
            result.setMsg("查询成功");
        } else {
            result.setCode(404);
            result.setMsg("问诊记录不存在");
        }

        return result;
    }

    /**
     * 抓药信息查询结果
     */
    @Getter
    @Setter
    public static class PrescriptionResult {
        private int code;               // 状态码
        private List<FillDrugDto> data; // 抓药信息列表
        private String msg;             // 消息
    }
}