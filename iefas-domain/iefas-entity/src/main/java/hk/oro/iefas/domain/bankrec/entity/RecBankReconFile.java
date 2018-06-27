package hk.oro.iefas.domain.bankrec.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
 * The persistent class for the REC_BANK_RECON_FILE database table.
 * 
 */
@Entity
@Table(name = "REC_BANK_RECON_FILE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RecBankReconFile extends UpdateTxnKeyable {

    @Id
    @Column(name = "BANK_RECON_FILES_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BANK_RECON_FILES_SEQ")
    @TableGenerator(name = "BANK_RECON_FILES_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BANK_RECON_FILES_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long bankReconFilesId;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

    @Column(name = "UPLOAD_FILE_NAME", length = 100)
    private String uploadFileName;

    // bi-directional many-to-one association to RecBankReconDetail
    @OneToMany(mappedBy = "bankReconDetailId", fetch = FetchType.LAZY)
    private List<RecBankReconDetail> recBankReconDetails;

}