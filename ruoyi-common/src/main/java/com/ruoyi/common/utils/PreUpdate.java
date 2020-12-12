package com.ruoyi.common.utils;

import com.ruoyi.common.core.domain.CommonEntityField;

import java.util.Date;

public class PreUpdate {
    public static CommonEntityField preInsert(){
        CommonEntityField commonEntityField=new CommonEntityField();
        commonEntityField.setCreateBy(SecurityUtils.getUsername());

        commonEntityField.setCreateTime(new Date());
        return commonEntityField;

    }
    public static CommonEntityField preEdit(){
        CommonEntityField commonEntityField=new CommonEntityField();
        commonEntityField.setUpdateBy(SecurityUtils.getUsername());
        commonEntityField.setUpdateTime(new Date());
        return commonEntityField;
    }
}
