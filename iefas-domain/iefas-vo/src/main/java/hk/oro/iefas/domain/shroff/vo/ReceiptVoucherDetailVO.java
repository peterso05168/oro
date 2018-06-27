package hk.oro.iefas.domain.shroff.vo;

import java.util.List;

import hk.oro.iefas.domain.common.vo.ActionVO;
import hk.oro.iefas.domain.dividend.vo.ApproveHistoryVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @version $Revision: 3220 $ $Date: 2018-06-20 14:10:52 +0800 (週三, 20 六月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptVoucherDetailVO {

    private ReceiptVoucherBasicInformationVO receiptVoucherBasicInformation;
    private List<ReceiptVoucherAccountItemVO> receiptVoucherAccountItems;
    private List<ApproveHistoryVO> approveHistories;
    private ActionVO action;
}
