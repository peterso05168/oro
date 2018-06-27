package hk.oro.iefas.domain.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version $Revision: 1079 $ $Date: 2018-02-07 18:02:00 +0800 (週三, 07 二月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class UpdateTxnKeyable extends UpdateAuditable {

    @Column(name = "TXN_KEY_REF")
    private Long txnKeyRef;

}
