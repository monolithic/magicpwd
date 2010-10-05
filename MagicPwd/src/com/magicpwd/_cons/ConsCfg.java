/**
 * 
 */
package com.magicpwd._cons;

/**
 * @author shangwen.yao
 * 
 */
public interface ConsCfg
{

    /**
     * 配置数据：区分前缀
     */
    String CFG_PRE = "magicpwd";
    /**
     * 用户信息：注册用户代码列表，以英文逗号分隔
     */
    String CFG_USER = CFG_PRE + ".user";
    /**
     * 用户信息：上次登录用户，不显示则置为空
     */
    String CFG_USER_LAST = CFG_USER + ".last";
    /**
     * 用户信息：用户偏好配置
     */
    String CFG_USER_NAME = CFG_USER + ".{0}";
    /**
     * 用户信息：用户网站编码
     */
    String CFG_USER_CODE = CFG_USER_NAME + ".code";
    /**
     * 用户信息：用户安全信息
     */
    String CFG_USER_INFO = CFG_USER_NAME + ".info";
    /**
     * 用户信息：用户登录口令
     */
    String CFG_USER_PKEY = CFG_USER_NAME + ".pkey";
    /**
     * 用户信息：用户安全口令
     */
    String CFG_USER_SKEY = CFG_USER_NAME + ".skey";
    /**
     * 界面配置：界面风格
     */
    String CFG_VIEW = CFG_PRE + ".view";
    /**
     * 界面配置：窗口是否总在最上
     */
    String CFG_VIEW_TOPS = CFG_VIEW + ".tops";
    /**
     * 界面配置：是否显示菜单栏
     */
    String CFG_VIEW_MENU = CFG_VIEW + ".menu";
    /**
     * 界面配置：菜单栏显示位置
     */
    String CFG_VIEW_MENU_LOC = CFG_VIEW_MENU + ".loc";
    /**
     * 界面配置：是否显示工具栏
     */
    String CFG_VIEW_TOOL = CFG_VIEW + ".tool";
    /**
     * 界面配置：工具栏显示位置
     */
    String CFG_VIEW_TOOL_LOC = CFG_VIEW_TOOL + ".loc";
    /**
     * 界面配置：是否显示信息栏
     */
    String CFG_VIEW_INFO = CFG_VIEW + ".info";
    /**
     * 界面配置：是否显示查找栏
     */
    String CFG_VIEW_FIND = CFG_VIEW + ".find";
    /**
     * 界面配置：
     */
    String CFG_VIEW_EDIT = CFG_VIEW + ".edit";
    /**
     * 界面配置：是否显示编辑板块
     */
    String CFG_VIEW_EDIT_VIW = CFG_VIEW_EDIT + ".viw";
    /**
     * 界面配置：编辑板块显示风格
     */
    String CFG_VIEW_EDIT_WND = CFG_VIEW_EDIT + ".wnd";
    /**
     * 界面配置：口令列表显示风格
     */
    String CFG_VIEW_LIST = CFG_PRE + ".list";
    /**
     * 界面配置：口令列表数据排序依据
     */
    String CFG_VIEW_LIST_KEY = CFG_VIEW_LIST + ".key";
    /**
     * 界面配置：口令列表数据排序方式
     */
    String CFG_VIEW_LIST_ASC = CFG_VIEW_LIST + ".asc";
    /**
     * 过期提示
     */
    String CFG_HINT = CFG_PRE + ".hint";
    /**
     * 过期提示：检测间隔
     */
    String CFG_HINT_INT = CFG_HINT + ".int";
    /**
     * 过期提示：提前提示时间
     */
    String CFG_HINT_PRE = CFG_HINT + ".pre";
    /**
     * 
     */
    String CFG_LANG = CFG_PRE + ".lang";
    /**
     * 
     */
    String CFG_SKIN = CFG_PRE + ".skin";
    String CFG_SKIN_DECO = CFG_SKIN + ".deco";
    String CFG_SKIN_FEEL = CFG_SKIN + ".feel";
    String CFG_SKIN_LOOK = CFG_SKIN + ".look";//Look显示名称
    String CFG_SKIN_TYPE = CFG_SKIN + ".type";//皮肤类型：java/synth/user
    String CFG_SKIN_NAME = CFG_SKIN + ".name";//Look类名称
    /**
     * 系统托盘显示方式
     */
    String CFG_TRAY = CFG_PRE + ".tray";
    String CFG_TRAY_PTN = CFG_TRAY + ".ptn";
    String CFG_TRAY_LOC = CFG_TRAY + ".loc";
    /**
     * 
     */
    String CFG_PWDS = CFG_PRE + ".pwds";
    /**
     * 系统默认口令字符集
     */
    String CFG_PWDS_HASH = CFG_PWDS + ".hash";
    /**
     * 系统默认口令长度
     */
    String CFG_PWDS_SIZE = CFG_PWDS + ".size";
    /**
     * 生成口令时系统默认是否可以生出重复字符
     */
    String CFG_PWDS_LOOP = CFG_PWDS + ".loop";
    /**
     * 数据备份：默认备份文件数量
     */
    String CFG_BACK_SIZE = CFG_PRE + ".back.size";
    /**
     * 数据备份：默认备份文件路径
     */
    String CFG_BACK_PATH = CFG_PRE + ".back.path";
    /**
     * 默认数据存放目录
     */
    String CFG_DATA_PATH = CFG_PRE + ".data.path";
    /**
     * 界面默认等等时间
     */
    String CFG_STAY_TIME = CFG_PRE + ".safe.time";
    /**
     * 默认数据：是
     */
    String DEF_TRUE = "true";
    /**
     * 默认数据：否
     */
    String DEF_FALSE = "false";
    /**
     * 默认风格：系统风格
     */
    String DEF_SKIN_SYS = "system";
    String DEF_SKIN_DEF = "default";
    String DEF_TRAY = "icon";
    /**
     * 默认数据：剪贴板数据保留时长
     */
    String DEF_STAY_TIME = "60";
    /**
     * 
     */
    String DEF_PWDS_HASH = "1000000000000006";
    /**
     * 默认数据：口令字符空间
     */
    String DEF_PWDS_CHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /**
     * 默认数据：口令长度
     */
    String DEF_PWDS_SIZE = "8";
    /**
     * 默认数据：备份目录
     */
    String DEF_BACK_PATH = ConsEnv.DIR_BAK;
    /**
     * 默认数据：数据目录
     */
    String DEF_DATA_PATH = ConsEnv.DIR_DAT;
    /**
     * 默认数据：备份数量
     */
    String DEF_BACK_SIZE = "3";
}
