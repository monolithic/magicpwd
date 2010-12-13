/*
 *  Copyright (C) 2010 Amon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.__i;

/**
 *
 * @author Amon
 */
public interface IEditItem
{

    int getType();

    void setType(int type);

    String getName();

    void setName(String name);

    String getData();

    boolean setData(String data);
    ///////////////////////////////////////////////////////
    // Spec属性
    ///////////////////////////////////////////////////////
    /**
     * 附加属性索引
     */
    int SPEC_GUID_TPLT = 0;// 口令模板索引
    int SPEC_FILE_NAME = 0;// 附件原文件名
    int SPEC_PWDS_HASH = 0;// 字符空间索引
    int SPEC_PWDS_SIZE = 1;// 生成口令长度
    int SPEC_PWDS_LOOP = 2;// 是否允许重复
    int SPEC_DATE_FORM = 0;// 日期显示格式
    int SPEC_DATA_OPT = 0;//可选输入
    int SPEC_DATA_SET = 1;//数据集
    int SPEC_DATA_INT = 2;//整数位
    int SPEC_DATA_DEC = 3;//小数位
    int SPEC_DATA_CHAR = 4;//特殊符号
    int SPEC_DATA_CHAR_OPT = 5;//是否可选
    int SPEC_DATA_CHAR_POS = 6;//符号位置
    int SPEC_SIGN_TYPE = 0;//控制类型
    int SPEC_SIGN_TPLT = 1;//显示模板
    // 常量
    String SPEC_VALUE_TRUE = "1";
    String SPEC_VALUE_FAIL = "0";

    /**
     * 
     * @param spec
     */
    void addSpec(String spec);

    String getSpec(int index);

    String getSpec(int index, String defValue);

    void setSpec(int index, String spec);

    String enCodeSpec(String c);

    void deCodeSpec(String text, String c);

    void setDefault();
}
