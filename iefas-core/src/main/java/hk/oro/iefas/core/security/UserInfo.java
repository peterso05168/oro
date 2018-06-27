package hk.oro.iefas.core.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String userName;
    private String userPost;
    private Integer postId;
    private Integer divisionId;
    private String delegatePost;
    private String delegateUser;
    private Integer delegatePostId;
    private Integer delegateDivisionId;

}
