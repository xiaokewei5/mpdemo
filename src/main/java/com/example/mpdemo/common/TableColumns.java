package com.example.mpdemo.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TableColumns extends Base {

    /**表名*/
    private String tableName;
    
    /**字段名*/
    private String columnName;
    
    /**字段类型*/
    private String columnType;
    
    /**字段注释*/
    private String columnComment;

}
