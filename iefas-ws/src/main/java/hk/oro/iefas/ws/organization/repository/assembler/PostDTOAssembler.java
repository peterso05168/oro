/**
 * 
 */
package hk.oro.iefas.ws.organization.repository.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.DslUtils;
import hk.oro.iefas.domain.organization.dto.PostDTO;
import hk.oro.iefas.domain.organization.dto.SearchPostResultDTO;
import hk.oro.iefas.domain.organization.entity.Post;
import hk.oro.iefas.domain.organization.entity.QPost;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class PostDTOAssembler {

    public static PostDTO toDTO(Post post) {
        PostDTO dto = null;
        if (post != null) {
            dto = DataUtils.copyProperties(post, PostDTO.class);
            if (CommonUtils.isNotEmpty(post.getUserProfiles())) {
                dto.setAssigned(true);
            } else {
                dto.setAssigned(false);
            }
        }
        return dto;
    }

    public static QBean<PostDTO> toDTO() {
        return Projections.bean(PostDTO.class, DslUtils.getAllExpression(QPost.post));
    }

    public static QBean<SearchPostResultDTO> toResultDTO() {
        QPost post = QPost.post;
        return Projections.bean(SearchPostResultDTO.class, post.postId, post.postTitle, post.division.divisionName,
            post.rank.rankName, post.status);
    }

    public static List<PostDTO> toDTOList(List<Post> posts) {
        List<PostDTO> dtoList = null;
        if (CommonUtils.isNotEmpty(posts)) {
            dtoList = posts.stream().map(type -> toDTO(type)).filter(dto -> dto != null).collect(Collectors.toList());
        }
        return dtoList;
    }
}
