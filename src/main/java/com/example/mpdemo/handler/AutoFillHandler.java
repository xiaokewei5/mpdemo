package com.example.mpdemo.handler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.function.Supplier;

import com.example.mpdemo.enums.DeleteEnum;
import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * 自动填充
 */
public class AutoFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject,"deleted", DeleteEnum.class,DeleteEnum.DELETE_NO);
        this.strictInsertFill(metaObject,"version",Integer.class,0);
        this.strictInsertFill(metaObject, "createtime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "createId", String.class, getCurrentUserId());
        this.strictInsertFill(metaObject, "updatetime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updateId", String.class, getCurrentUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.strictUpdateFill(metaObject, "updatetime", LocalDateTime.class, now);
        this.strictUpdateFill(metaObject, "updateId", String.class, "updateId");
    }
    

    /**
     * 获取用户id
     */
    private String getCurrentUserId() {
        return "createId";
    }
    
    /**
     * 重写填充策略
     * 填充策略,默认有值覆盖,如果提供的值为null不填充
     */
    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        Object obj = fieldVal.get();
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj);
        }
        return this;
    }
}
