/**
 * 
 */
package hk.oro.iefas.ws.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.security.dto.UserPwdHistDTO;
import hk.oro.iefas.domain.security.entity.UserPwdHist;
import hk.oro.iefas.ws.security.repository.UserPwdHistRepository;
import hk.oro.iefas.ws.security.repository.assembler.UserPwdHistDTOAssembler;
import hk.oro.iefas.ws.security.repository.predicate.UserPwdHistPredicate;
import hk.oro.iefas.ws.security.service.UserPwdHistService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class UserPwdHistServiceImpl implements UserPwdHistService {

    @Autowired
    private UserPwdHistRepository userPwdHistRepository;

    @Autowired
    private UserPwdHistDTOAssembler userPwdHistDTOAssembler;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveUserPwdHist(UserPwdHistDTO userPwdHistDTO) {
        log.info("saveUserPwdHist() start - " + userPwdHistDTO);
        UserPwdHist userPwdHist = DataUtils.copyProperties(userPwdHistDTO, UserPwdHist.class);
        userPwdHist = userPwdHistRepository.save(userPwdHist);
        log.info("saveUserPwdHist() end - " + userPwdHist);
        return userPwdHist.getUserAcPwdId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserPwdHistDTO> findByUserAcId(SearchDTO<Integer> searchDTO) {
        log.info("findByUserAcId() start - " + searchDTO);
        Page<UserPwdHist> page = userPwdHistRepository
            .findAll(UserPwdHistPredicate.findByUserAcId(searchDTO.getCriteria()), searchDTO.getPage().toPageable());
        List<UserPwdHistDTO> result = userPwdHistDTOAssembler.toDTOList(page.getContent());
        log.info("findByUserAcId() end - " + result);
        return result;
    }

}
