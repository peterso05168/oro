/**
 * 
 */
package hk.oro.iefas.ws.shroff.repository.assembler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.domain.shroff.dto.JournalVoucherResultItemDTO;
import hk.oro.iefas.domain.shroff.entity.QVoucher;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class JournalVoucherResultItemDTOAssembler {

    public QBean<JournalVoucherResultItemDTO> getQBean() {
        QVoucher voucher = QVoucher.voucher;
        Map<String, Expression<?>> bindings = new HashMap<>();
        bindings.put("voucherId", voucher.voucherId);
        bindings.put("voucherNumber", voucher.voucherNo);
        bindings.put("voucherDate", voucher.voucherDate);
        bindings.put("groupCode", voucher.groupCode);
        bindings.put("journalType", voucher.journalType.journalTypeDesc);
        bindings.put("status", voucher.status);
        return Projections.bean(JournalVoucherResultItemDTO.class, bindings);
    }
}
