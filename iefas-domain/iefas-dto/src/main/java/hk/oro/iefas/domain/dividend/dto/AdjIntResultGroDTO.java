package hk.oro.iefas.domain.dividend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AdjIntResultGroDTO {

    private long adjIntResultGroId;
    private String status;
    private GroundCodeDTO groundCode;
}
