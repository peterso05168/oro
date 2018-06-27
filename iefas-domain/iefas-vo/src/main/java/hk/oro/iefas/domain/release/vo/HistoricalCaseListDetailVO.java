package hk.oro.iefas.domain.release.vo;

import java.util.List;

import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherAccountItemVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherBasicInformationVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @version $Revision: 2659 $ $Date: 2018-05-28 10:45:24 +0800 (Mon, 28 May 2018) $
 * @author $Author: george.wu $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalCaseListDetailVO {
    private RelHistListVO historicalCaseListInformation;
    private List<RelHistListItemVO> historicalCaseListDetailItems;
}
