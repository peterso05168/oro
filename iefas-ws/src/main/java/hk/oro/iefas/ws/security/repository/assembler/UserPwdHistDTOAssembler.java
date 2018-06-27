/**
 * 
 */
package hk.oro.iefas.ws.security.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.security.dto.UserPwdHistDTO;
import hk.oro.iefas.domain.security.entity.UserPwdHist;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class UserPwdHistDTOAssembler extends AssemblerSupport<UserPwdHistDTO, UserPwdHist> {

    @Override
    public UserPwdHistDTO toDTO(UserPwdHist entity) {
        UserPwdHistDTO userPwdHistDTO = null;
        if (entity != null) {
            userPwdHistDTO = DataUtils.copyProperties(entity, UserPwdHistDTO.class);
        }
        return userPwdHistDTO;
    }

}
