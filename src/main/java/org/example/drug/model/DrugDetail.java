package org.example.drug.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 药品详细信息
 */
@Getter
@Setter
public class DrugDetail {
    private Integer id;                  // 药品ID
    private String name;                 // 药物名称
    private String specification;        // 药物规格
    private String administrationRoute;  // 给药途径
    private Integer expenseEach;         // 药物单价(分)
    private String guidance;             // 用药指导
    private String medicationPrecaution; // 用药注意事项
    private String medicationSite;       // 用药部位

    // 获取价格(元)
    public double getExpenseInYuan() {
        return expenseEach / 100.0;
    }
}