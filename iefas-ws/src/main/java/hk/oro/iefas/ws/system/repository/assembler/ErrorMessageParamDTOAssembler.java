/**
 * 
 */
package hk.oro.iefas.ws.system.repository.assembler;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.DslUtils;
import hk.oro.iefas.domain.system.dto.ErrorMessageParamDTO;
import hk.oro.iefas.domain.system.entity.QErrorMessageParam;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class ErrorMessageParamDTOAssembler {

    public static QBean<ErrorMessageParamDTO> toDTO() {
        return Projections.bean(ErrorMessageParamDTO.class,
            DslUtils.getAllExpression(QErrorMessageParam.errorMessageParam));
    }
}
