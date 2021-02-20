package com.example.mpdemo.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询基础类
 */
public class BaseDto extends BasePage{
    
    /** 搜索值 */
    private String searchValue;
    
    /** 查询的字段 */
    private String[] selectColumns;
    
    /** 请求参数 */
    private Map<String, Object> params;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String[] getSelectColumns() {
        return selectColumns == null ? new String[]{} : selectColumns;
    }

    public void setSelectColumns(String[] selectColumns) {
        this.selectColumns = selectColumns;
    }

    public Map<String, Object> getParams() {
        return params == null ? new HashMap<>() : params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    
}
