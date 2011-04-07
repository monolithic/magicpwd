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
package com.magicpwd._cons;

/**
 * @author Amon
 * 
 */
public interface ConsDat
{

    /**
     * 数据库版本
     */
    String VERSIONS = "4";
    /**
     * 口令状态：默认
     */
    int PWDS_MODE_0 = 0;
    /**
     * 口令状态：使用中
     */
    int PWDS_MODE_1 = 1;
    /**
     * 口令状态：待注册
     */
    int PWDS_MODE_2 = 2;
    /**
     * 口令状态：待认证
     */
    int PWDS_MODE_3 = 3;
    /**
     * 口令状态：待激活
     */
    int PWDS_MODE_4 = 4;
    /**
     * 口令状态：仅测试
     */
    int PWDS_MODE_5 = 5;
    /**
     * 口令状态：已过期
     */
    int PWDS_MODE_6 = 6;
    /**
     * 口令状态：已丢失
     */
    int PWDS_MODE_7 = 7;
    /**
     * 口令状态：已禁用
     */
    int PWDS_MODE_8 = 8;
    /**
     * 口令状态：已删除
     */
    int PWDS_MODE_9 = 6;
    /**
     * 重要程度：最高
     */
    int PWDS_NOTEP2 = +2;
    /**
     * 重要程度：高
     */
    int PWDS_NOTEP1 = +1;
    /**
     * 重要程度：正常
     */
    int PWDS_NOTE_0 = 0;
    /**
     * 重要程度：最低
     */
    int PWDS_NOTEN1 = -1;
    /**
     * 重要程度：低
     */
    int PWDS_NOTEN2 = -2;
    /**
     * 目录信息：密码节点
     */
    String HASH_ROOT = "sctfxrczgcywxezs";
    /**
     * 目录信息：日记节点
     */
    String HASH_NOTE = "qqqqqaavaqddvafs";
    /**
     * 目录信息：模板节点
     */
    String HASH_TPLT = "qqqqqaacbrrtysww";
    /**
     * 目录信息：电子邮件
     */
    String TEXT_MAIL = "mail";
    /**
     * 目录信息：电子卡片
     */
    String TEXT_CARD = "card";
    /**
     * 数据分隔：模板数据起始默认标记
     * 示例：&lt;电子邮件&gt;
     */
    String SP_TPL_LS = "<";
    /**
     * 数据分隔：模板数据结束默认标记
     * 示例：&lt;电子邮件&gt;
     */
    String SP_TPL_RS = ">";
    /**
     * 同一元素键值区分标记
     */
    String SP_SQL_KV = "\b";
    /**
     * 不同元素间隔区分标记
     */
    String SP_SQL_EE = "\f";
    /**
     * 属性：信息
     */
    int INDX_INFO = 0;
    /**
     * 属性：文本
     */
    int INDX_TEXT = INDX_INFO + 1;
    /**
     * 属性：口令
     */
    int INDX_PWDS = INDX_TEXT + 1;
    /**
     * 属性：链接
     */
    int INDX_LINK = INDX_PWDS + 1;
    /**
     * 属性：邮件
     */
    int INDX_MAIL = INDX_LINK + 1;
    /**
     * 属性：日期
     */
    int INDX_DATE = INDX_MAIL + 1;
    /**
     * 属性：附注
     */
    int INDX_AREA = INDX_DATE + 1;
    /**
     * 属性：附件
     */
    int INDX_FILE = INDX_AREA + 1;
    /**
     * 属性：数值
     */
    int INDX_DATA = INDX_FILE + 1;
    /**
     * 属性：列表
     */
    int INDX_LIST = INDX_DATA + 1;
    /**
     * 属性：分组
     */
    int INDX_SIGN = INDX_LIST + 1;
    /**
     * 属性：模板向导
     */
    int INDX_GUID = INDX_SIGN + 1;
    /**
     * 属性：关键搜索
     */
    int INDX_META = INDX_GUID + 1;
    /**
     * 属性：口令图标
     */
    int INDX_LOGO = INDX_META + 1;
    /**
     * 属性：过期提示
     */
    int INDX_HINT = INDX_LOGO + 1;
    /**
     * 属性：组件个数
     */
    int INDX_SIZE = INDX_HINT + 1;
    /** 邮件附加属性：邮件 */
    int TYPE_MAIL_MAIL = 0x1100;
    /** 邮件附加属性：用户 */
    int TYPE_MAIL_USER = 0x1200;
    /** 邮件附加属性：口令 */
    int TYPE_MAIL_PWDS = 0x1300;
    /** 即时通讯附加属性：账户*/
    int TYPE_IM_USER = 0x2100;
    /**即时通讯附加属性：口令*/
    int TYPE_IM_PWDS = 0x2200;
    /**
     * 用户权限：所有权限
     */
    int UR_ROOT = 3;
    /**
     * 用户权限：新增读取
     */
    int UR_USER = 2;
    /**
     * 用户权限：仅能读取
     */
    int UR_READ = 1;
    String GMTP_SMTP = "SMTP";
    String GMTP_POP3 = "POP3";
    String GMTP_IMAP = "IMAP";
    String GMTP_HTTP = "HTTP";
    /**
     * 附加布尔属性
     */
    String TOOL_VIEW_MOD1 = "icon";
    String TOOL_VIEW_MOD2 = "text";
    String TOOL_VIEW_MOD3 = "icon,text";
    String TOOL_VIEW_POS1 = "icon";
}
