/**
 * 
 */
package hk.oro.iefas.ws.system.repository.assembler;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.DslUtils;
import hk.oro.iefas.domain.system.dto.OroInfoDTO;
import hk.oro.iefas.domain.system.entity.QOroInfo;

/**
 * @version $Revision: 2829 $ $Date: 2018-06-02 11:00:32 +0800 (週六, 02 六月 2018) $
 * @author $Author: marvel.ma $
 */
public class OroInformationDTOAssembler {

    public static QBean<OroInfoDTO> toDTO() {
        return Projections.bean(OroInfoDTO.class, DslUtils.getAllExpression(QOroInfo.oroInfo));
    }
}
