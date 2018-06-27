/**
 * 
 */
package hk.oro.iefas.ws.organization.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.organization.dto.ApproverDTO;
import hk.oro.iefas.domain.organization.entity.Post;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class ApproverDTOAssembler extends AssemblerSupport<ApproverDTO, Post> {

    @Override
    public ApproverDTO toDTO(Post entity) {
        if (entity != null) {
            return new ApproverDTO(entity.getPostId(), entity.getPostTitle(), RankDTOAssembler.toDTO(entity.getRank()));
        }
        return null;
    }

}
