package org.example.drug;

import lombok.Getter;
import lombok.Setter;
import org.example.drug.model.DrugDetail;

import java.util.List;

/**
 * 药物模糊查询服务
 */
public class DrugFuzzyQuery {

    /**
     * 根据药物名称模糊查询
     * @param name 药物名称(模糊)
     * @param page 页码(从1开始)
     * @param limit 每页数量
     * @return 药品列表结果
     */
    public DrugListResult fuzzyQueryByName(String name, int page, int limit) {
        // 模拟API调用
        DrugListResult result = new DrugListResult();

        if (name != null && !name.trim().isEmpty()) {
            result.setCode(200);

            // 模拟数据
            DrugDetail drug1 = new DrugDetail();
            drug1.setId(1);
            drug1.setName("阿莫西林胶囊");
            drug1.setSpecification("0.25g*24粒/盒");
            drug1.setAdministrationRoute("口服");
            drug1.setExpenseEach(3500);

            DrugDetail drug2 = new DrugDetail();
            drug2.setId(2);
            drug2.setName("阿莫西林颗粒");
            drug2.setSpecification("0.125g*12袋/盒");
            drug2.setAdministrationRoute("口服");
            drug2.setExpenseEach(2800);

            result.setData(List.of(drug1, drug2));
            result.setMsg("查询成功");
        } else {
            result.setCode(400);
            result.setMsg("药物名称不能为空");
        }

        return result;
    }

    /**
     * 药品列表查询结果
     */
    @Getter
    @Setter
    public static class DrugListResult {
        private int code;               // 状态码
        private List<DrugDetail> data;   // 药品列表
        private String msg;             // 消息
    }
}