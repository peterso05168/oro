package hk.oro.iefas.domain.system.entity;

import java.util.Date;

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

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HOLIDAY")
public class Holiday extends UpdateTxnKeyable {

    @Id
    @Column(name = "HOLIDAY_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "HOLIDAY_SEQ")
    @TableGenerator(name = "HOLIDAY_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "HOLIDAY_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer holidayId;

    @Column(name = "HOLIDAY_DATE")
    private Date holidayDate;

    @Column(name = "HOLIDAY_DESC", length = 200)
    private String holidayDesc;

    @Column(name = "STATUS", length = 3)
    private String status;

}
