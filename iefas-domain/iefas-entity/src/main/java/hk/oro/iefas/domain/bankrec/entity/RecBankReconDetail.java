package hk.oro.iefas.domain.bankrec.entity;

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

/**
 * The persistent class for the REC_BANK_RECON_DETAIL database table.
 * 
 */
@Entity
@Table(name = "REC_BANK_RECON_DETAIL")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RecBankReconDetail extends UpdateTxnKeyable {

    @Id
    @Column(name = "BANK_RECON_DETAIL_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BANK_RECON_DETAIL_SEQ")
    @TableGenerator(name = "BANK_RECON_DETAIL_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BANK_RECON_DETAIL_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long bankReconDetailId;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    // bi-directional many-to-one association to RecBankReconFile
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_RECON_FILES_ID")
    private RecBankReconFile recBankReconFile;

}