package org.example.disease.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 疾病详细信息
 */
@Getter
@Setter
public class DiseaseDetail {
    private Integer id;          // 疾病ID
    private String name;         // 疾病名称
    private String description;  // 疾病描述
}