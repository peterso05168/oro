/**
 * 
 */
package hk.oro.iefas.ws.security.repository.assembler;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.DslUtils;
import hk.oro.iefas.domain.organization.entity.QDivision;
import hk.oro.iefas.domain.organization.entity.QPost;
import hk.oro.iefas.domain.organization.entity.QUserProfile;
import hk.oro.iefas.domain.security.dto.SearchUserResultDTO;
import hk.oro.iefas.domain.security.dto.UserAccountDTO;
import hk.oro.iefas.domain.security.dto.UserDetailDTO;
import hk.oro.iefas.domain.security.entity.QUserAccount;
import hk.oro.iefas.domain.security.entity.UserAccount;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class UserAccountDTOAssembler {

    public static QBean<UserAccountDTO> toDTO() {
        return Projections.bean(UserAccountDTO.class, DslUtils.getAllExpression(QUserAccount.userAccount));
    }

    public static UserAccountDTO toDTO(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = null;
        if (userAccount != null) {
            userAccountDTO = DataUtils.copyProperties(userAccount, UserAccountDTO.class);
        }
        return userAccountDTO;
    }

    public static QBean<UserDetailDTO> toDetailDTO() {
        QUserAccount useraccount = QUserAccount.userAccount;
        QUserProfile userProfile = useraccount.userProfile;
        QPost post = userProfile.post;
        QDivision division = userProfile.division;
        return Projections.bean(UserDetailDTO.class, useraccount.loginName, userProfile.engName, useraccount.password,
            post.postId, post.postTitle, division.divisionId, division.divisionName, useraccount.lockedInd,
            useraccount.unlockTime, useraccount.failCount, useraccount.expiryDate, useraccount.status);
    }

    public static QBean<SearchUserResultDTO> toResultDTO() {
        QUserAccount useraccount = QUserAccount.userAccount;
        QUserProfile userProfile = useraccount.userProfile;
        QPost post = userProfile.post;
        QDivision division = userProfile.division;
        return Projections.bean(SearchUserResultDTO.class, useraccount.userAcId, useraccount.loginName,
            userProfile.emailAddress, userProfile.engName, division.divisionName, post.postTitle, useraccount.status);
    }

}
