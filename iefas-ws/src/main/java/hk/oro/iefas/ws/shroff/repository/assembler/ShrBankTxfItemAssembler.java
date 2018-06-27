/**
 * 
 */
package hk.oro.iefas.ws.shroff.repository.assembler;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.domain.shroff.dto.BankTransferItemResultDTO;
import hk.oro.iefas.domain.shroff.entity.QShrBankTxfItem;

/**
 * @version $Revision: 3032 $ $Date: 2018-06-11 18:14:28 +0800 (週一, 11 六月 2018) $
 * @author $Author: marvel.ma $
 */
public class ShrBankTxfItemAssembler {

    public static QBean<BankTransferItemResultDTO> toResultDTO() {
        QShrBankTxfItem shrbanktxfitem = QShrBankTxfItem.shrBankTxfItem;
        return Projections.bean(BankTransferItemResultDTO.class, shrbanktxfitem.itemNo, shrbanktxfitem.transferDate,
            shrbanktxfitem.voucher.voucherNo, shrbanktxfitem.beneficiaryAc, shrbanktxfitem.toAcName,
            shrbanktxfitem.curcyCode, shrbanktxfitem.transferAmount);
    }

}
