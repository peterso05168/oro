package hk.oro.iefas.domain.ormis.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORM_ORMIS_FILE_DETAIL")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class OrmOrmisFileDetail extends UpdateTxnKeyable {

    @Id
    @Column(name = "FILE_TO_ORMIS_DETAIL_SEQ")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FILE_TO_ORMIS_DETAIL_SEQ")
    @TableGenerator(name = "FILE_TO_ORMIS_DETAIL_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "FILE_TO_ORMIS_DETAIL_SEQ", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long fileToOrmisDetailSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_TO_ORMIS_SEQ")
    private OrmOrmisFile ormisFile;
    
    @Column(name = "CASE_TYPE")
    private String caseType;
    
    @Column(name = "CASE_NO")
    private Integer caseNo;
    
    @Column(name = "CASE_YEAR")
    private Integer caseYear;
    
    @Column(name = "CASE_NAME")
    private String caseName;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "STATUS_UPDATE_DATE")
    private Date statusUpdateDate;
    
    @Column(name = "STAFF_CODE")
    private String staffCode;
    
    @Column(name = "POST_OF_CASE_OFFICER")
    private String postOfCaseOfficer;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_MADE_DATE")
    private Date orderMadeDate;
    
    @Column(name = "FIRM_NAME")
    private String firmName;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "ENTRY_START_DATE")
    private Date entryStartDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "ENTRY_END_DATE")
    private Date entryEndDate;
    
    @Column(name = "DELIMITER")
    private String delimiter;
    
    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal transactionAmount;
    
    @Column(name = "STATUS", length = 3)
    private String status;
    
    @Column(name = "CASE_STATUS", length = 3)
    private String caseStatus;
}
