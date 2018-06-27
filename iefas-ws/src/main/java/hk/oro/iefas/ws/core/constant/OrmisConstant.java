package hk.oro.iefas.ws.core.constant;

import lombok.Getter;

public class OrmisConstant {
    
    public static final String DEFAULT_USER_NAME = "System";
    public static final String DEFAULT_USER_POST = "System";

    public static enum FileId{
        CASE_INFO(1L);
        
        @Getter
        private Long fileId;
        
        private FileId(Long fileId) {
            this.fileId = fileId;
        }
    }
}
