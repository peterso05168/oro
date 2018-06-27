package hk.oro.iefas.web.dividend.maintenance.orsfees.view.validator;

import java.io.Serializable;
import java.util.List;

import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.dividend.vo.CaseFeeMaintenanceVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Named
@ViewScoped
public class OrFeeValidator extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1483689500826822814L;

    @Inject
    private AppResourceUtils appResourceUtils;

    public boolean validateValueOfCaseFeeMaintenanceListOfCalculationMaintenanceVO(
        List<CaseFeeMaintenanceVO> caseFeeMaintenanceVOs) throws ValidatorException {
        if (CommonUtils.isNotEmpty(caseFeeMaintenanceVOs)) {
            for (CaseFeeMaintenanceVO temp : caseFeeMaintenanceVOs) {
                boolean isShowMsg = false;
                if (temp.getValueTo() == null) {
                    Messages.addError("msgs",
                        appResourceUtils.getMessage(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.VALUE_FROM));
                    isShowMsg = true;
                } else if (temp.getValueFrom().compareTo(temp.getValueTo()) >= 0) {
                    Messages.addError("msgs", appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_BEFORE,
                        MsgParamCodeConstant.VALUE_TO, MsgParamCodeConstant.VALUE_FROM));
                    isShowMsg = true;
                }
                /*if (temp.getRoundingAmount() == null) {
                    Messages.addError("msgs", "Rounding Amount is required");
                    isShowMsg = true;
                }
                if (temp.getRoundingUnit() == null) {
                    Messages.addError("msgs", "Rounding Unit is required");
                    isShowMsg = true;
                }*/
                if (isShowMsg) {
                    Faces.renderResponse();
                    return !isShowMsg;
                }

            }
        }
        return true;
    }

}
