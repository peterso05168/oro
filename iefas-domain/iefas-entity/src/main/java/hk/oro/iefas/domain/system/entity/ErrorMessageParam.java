package hk.oro.iefas.domain.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

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
@Table(name = "ERROR_MESSAGE_PARAM")
public class ErrorMessageParam {

    @Id
    @Column(name = "ERROR_MESSAGE_PARAM_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ERROR_MSG_PARAM_SEQ")
    @TableGenerator(name = "ERROR_MSG_PARAM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ERROR_MESSAGE_PARAM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer errorMessageParamId;

    @Column(name = "PARAM_CODE", length = 7)
    private String paramCode;

    @Column(name = "PARAM_CONTENT", length = 7)
    private String paramContent;
}
