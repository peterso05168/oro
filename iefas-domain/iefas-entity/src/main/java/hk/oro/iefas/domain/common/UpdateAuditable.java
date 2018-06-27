package hk.oro.iefas.domain.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

/**
 * @version $Revision: 3215 $ $Date: 2018-06-20 14:00:28 +0800 (週三, 20 六月 2018) $
 * @author $Author: scott.feng $
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class UpdateAuditable {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "CREATED_BY_POST", length = 100)
    private String createdByPost;

//    @CreatedBy
    @Column(name = "CREATED_BY_USER", length = 100)
    private String createdByUser;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    @Column(name = "LAST_UPDATED_BY_POST", length = 100)
    private String lastUpdatedByPost;

//    @LastModifiedBy
    @Column(name = "LAST_UPDATED_BY_USER", length = 100)
    private String lastUpdatedByUser;

    @Version
    @Column(name = "VERSION_NO")
    private Integer versionNo;
}
