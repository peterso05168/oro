package hk.oro.iefas.domain.organization.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version $Revision: 2495 $ $Date: 2018-05-19 13:24:40 +0800 (週六, 19 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"userProfiles"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "POST")
public class Post extends UpdateTxnKeyable {

    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "POST_SEQ")
    @TableGenerator(name = "POST_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "POST_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer postId;

    @Column(name = "POST_TITLE", length = 100)
    private String postTitle;

    @Column(name = "POST_DESC", length = 200)
    private String postDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVISION_ID")
    private Division division;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RANK_ID")
    private Rank rank;

    @Column(name = "STATUS", length = 3)
    private String status;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<UserProfile> userProfiles;
}
