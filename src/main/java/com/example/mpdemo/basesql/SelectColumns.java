package com.example.mpdemo.basesql;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.example.mpdemo.common.TableColumns;

/**
 * 自定义的sql实现类
 */
public class SelectColumns extends AbstractMethod {
    
    private static final long serialVersionUID = 7439311443916344720L;
    
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = getTableColumnSql(DbType.MYSQL);
        String formatSql = String.format(sql, tableInfo.getTableName());
        String method = "selectColumns";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, formatSql, modelClass);
        return this.addSelectMappedStatementForOther(mapperClass, method, sqlSource, TableColumns.class);
    }
    
    private String getTableColumnSql(DbType type){
        StringBuilder sb = new StringBuilder();
        if(DbType.MYSQL == type){
            sb.append("select ");
            sb.append("table_name, ");
            sb.append("column_name, ");
            sb.append("column_type, ");
            sb.append("column_comment ");
            sb.append("from information_schema.columns ");
            sb.append("where table_schema = (select database()) ");
            sb.append("and table_name = '%s'");
        }
        return sb.toString();
    }
}
