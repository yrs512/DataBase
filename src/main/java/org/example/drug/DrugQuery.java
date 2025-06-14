package org.example.drug;

import lombok.Getter;
import lombok.Setter;
import org.example.drug.model.DrugDetail;

/**
 * 药物查询服务
 */
public class DrugQuery {

    /**
     * 根据药品ID查询详细信息
     * @param drugId 药品ID
     * @return 药品详细信息结果
     */
    public DrugResult queryDrugById(int drugId) {
        // 模拟API调用
        DrugResult result = new DrugResult();

        if (drugId > 0) {
            result.setCode(200);

            // 模拟数据
            DrugDetail drug = new DrugDetail();
            drug.setId(drugId);
            drug.setName("阿莫西林胶囊");
            drug.setSpecification("0.25g*24粒/盒");
            drug.setAdministrationRoute("口服");
            drug.setExpenseEach(3500); // 35元
            drug.setGuidance("每日3次，每次1粒");
            drug.setMedicationPrecaution("对青霉素过敏者禁用");
            drug.setMedicationSite("全身");

            result.setData(drug);
            result.setMsg("查询成功");
        } else {
            result.setCode(404);
            result.setMsg("药品不存在");
        }

        return result;
    }

    /**
     * 查询结果封装
     */
    @Getter
    @Setter
    public static class DrugResult {
        private int code;           // 状态码
        private DrugDetail data;    // 药品详细信息
        private String msg;         // 消息
    }
}