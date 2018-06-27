package hk.oro.iefas.domain.shroff.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SHR_ANALYSIS_CODE_TYPE")
public class AnalysisCodeType extends UpdateTxnKeyable {

    @Id
    @Column(name = "ANALYSIS_CODE_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ANALYSIS_CODE_TYPE_SEQ")
    @TableGenerator(name = "ANALYSIS_CODE_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ANALYSIS_CODE_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer analysisCodeTypeId;

    @Column(name = "ANALYSIS_CODE_TYPE_NAME")
    private String analysisCodeTypeName;

    @Column(name = "ANALYSIS_CODE_TYPE_DESC")
    private String analysisCodeTypeDesc;

    @Column(name = "STATUS")
    private String status;
}
