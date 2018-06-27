package hk.oro.iefas.domain.core.vo;

import java.util.Date;

import lombok.Data;

/**
 * @version $Revision: 1088 $ $Date: 2018-02-08 11:15:43 +0800 (週四, 08 二月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
public class BaseVO {

    private String createdByUser;

    private Date createDate;

    private String lastUpdatedByUser;

    private Date lastUpdateDate;

    private Integer versionNo;

    private String createdByPost;

    private String lastUpdatedByPost;

}
