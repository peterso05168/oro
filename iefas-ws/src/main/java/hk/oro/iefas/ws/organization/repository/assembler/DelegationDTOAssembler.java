/**
 * 
 */
package hk.oro.iefas.ws.organization.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.Tuple;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.dto.DelegationDTO;
import hk.oro.iefas.domain.organization.entity.QUserDelegation;
import hk.oro.iefas.domain.organization.entity.QUserProfile;
import hk.oro.iefas.domain.organization.entity.UserDelegation;
import hk.oro.iefas.domain.organization.entity.UserProfile;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class DelegationDTOAssembler {

    public static List<DelegationDTO> toDTOList(List<Tuple> tupleList) {
        List<DelegationDTO> dtoList = null;
        if (CommonUtils.isNotEmpty(tupleList)) {
            dtoList = new ArrayList<>();
            for (Tuple tuple : tupleList) {
                UserDelegation userDelegation = tuple.get(QUserDelegation.userDelegation);
                DelegationDTO delegationDTO = DataUtils.copyProperties(userDelegation, DelegationDTO.class);

                UserProfile userProfile = tuple.get(QUserProfile.userProfile);
                delegationDTO.setEngName(userProfile.getEngName());
                dtoList.add(delegationDTO);
            }
        }
        return dtoList;
    }
}
