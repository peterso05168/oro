package hk.oro.iefas.domain.dividend.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AdjIntResultGroVO {

    private long adjIntResultGroId;
    private String status;
    private GroundCodeVO groundCode;
}
