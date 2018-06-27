package hk.oro.iefas.ws.shroff.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.shroff.dto.SearchTransferToGrOrBeaCriteriaDTO;
import hk.oro.iefas.domain.shroff.entity.QShrTxfGrBea;
import hk.oro.iefas.domain.shroff.entity.QShrTxfGrBeaItem;

/**
 * @version $Revision: 3183 $ $Date: 2018-06-19 10:20:35 +0800 (週二, 19 六月 2018) $
 * @author $Author: dante.fang $
 */
public class TransferToGrOrBeaPredicate {

    private static QShrTxfGrBea SHR_TXF_GR_BEA = QShrTxfGrBea.shrTxfGrBea;

    public static Predicate findByCriteria(SearchTransferToGrOrBeaCriteriaDTO criteria) {

        BooleanExpression idExpression = null;
        if (criteria.getTransferId() != null) {
            idExpression = SHR_TXF_GR_BEA.transferId.eq(criteria.getTransferId());
        }

        BooleanExpression numberExpression = null;
        if (criteria.getTransferNo() != null) {
            numberExpression = SHR_TXF_GR_BEA.transferNo.like("%" + criteria.getTransferNo() + "%");
        }

        BooleanExpression typeExpression = null;
        if (criteria.getTransferTypeId() != null) {
            typeExpression = SHR_TXF_GR_BEA.transferType.txfAmountTypeId.eq(criteria.getTransferTypeId());
        }

        BooleanExpression accountTypeExpression = null;
        if (criteria.getAccountTypeId() != null) {
            QShrTxfGrBeaItem shrTxfGrBeaItem = SHR_TXF_GR_BEA.shrTxfGrBeaItems.any();
            accountTypeExpression = shrTxfGrBeaItem.acTypeId.eq(criteria.getAccountTypeId());
        }

        BooleanExpression processDateExpression = null;
        if (criteria.getProcessDate() != null) {
            processDateExpression = SHR_TXF_GR_BEA.processDate.between(
                DateUtils.getStartDate(criteria.getProcessDate()), DateUtils.getEndDate(criteria.getProcessDate()));
        }

        BooleanExpression cutOffDateExpression = null;
        if (criteria.getCutOffDate() != null) {
            cutOffDateExpression = SHR_TXF_GR_BEA.cutOffDate.between(DateUtils.getStartDate(criteria.getCutOffDate()),
                DateUtils.getEndDate(criteria.getCutOffDate()));
        }

        BooleanExpression voucherNumberExpression = null;
        if (CommonUtils.isNotBlank(criteria.getVoucherNumber())) {
            voucherNumberExpression = SHR_TXF_GR_BEA.voucher.voucherNo.like("%" + criteria.getVoucherNumber() + "%");
        }

        BooleanExpression statusExpression = SHR_TXF_GR_BEA.status.eq(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(idExpression, numberExpression, typeExpression, accountTypeExpression,
            processDateExpression, cutOffDateExpression, voucherNumberExpression, statusExpression);
    }
}
