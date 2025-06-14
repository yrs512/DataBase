package org.example.drug.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 抓药信息数据传输对象
 */
@Getter
@Setter
public class FillDrugDto {
    private String name;           // 药物名称
    private String specification;  // 药物规格
    private Integer count;         // 药品数量
}