/**
 * 
 */
package com.magicpwd._cons;

/**
 * @author Amon
 * 
 */
public interface ConsEnv
{
    /**
     * 软件信息：当前版本
     */
    String VERSIONS = "V3.0.0.0";
    /**
     * 软件信息：软件代码
     */
    String SOFTHASH = "130F0000";
    /**
     * 软件信息：软件名称
     */
    String SOFTNAME = "魔方密码";
    /**
     * 软件信息：软件首页
     */
    String HOMEPAGE = "http://magicpwd.com/";
    /**
     * 软件信息：构建日期
     */
    String BUILDER = "2009-03-15";
    /**
     * 软件信息：软件版权
     */
    String SOFTCOPY = "© {0} MagicPwd.com";

    /**
     * 安全信息：摘要算法
     */
    String NAME_DIGEST = "SHA-512";
    /**
     * 安全信息：密码算法
     */
    String NAME_CIPHER = "AES";
    /**
     * 安全信息：文本编码
     */
    String FILE_ENCODING = "UTF-8";

    String DIR_BAK = "bak";
    String DIR_DAT = "dat";
    String FILE_DATA = "amon";
    String FILE_BACK = FILE_DATA + "_{0}.backup";
    String FILE_DATE = "yyyyMMdd-HHmmss";
    String VIEW_DATE = "yyyy-MM-dd HH:mm:ss";

    /**
     * 类别数据显示排序
     */
    /**
     * 按名称排序
     */
    String LIST_SORT_KEY = "key";
    /**
     * 按注册时间排序
     */
    String LIST_SORT_REG = "reg";
    /**
     * 按到期日期排序
     */
    String LIST_SORT_DUE = "due";
    /**
     * 按频率排序
     */
    String LIST_SORT_FEQ = "feq";
    int PWDS_HEAD_GUID = 0;
    int PWDS_HEAD_META = 1;
    int PWDS_HEAD_LOGO = 2;
    int PWDS_HEAD_NOTE = 3;
    /**
     * 记录头部预留属性个数
     */
    int PWDS_ITEM_HEAD = 4;
    /**
     * 数据保存时数据块大小
     */
    int PWDS_DATA_SIZE = 8192;

    int BUTN_MINI_WIDH = 22;
    int BUTN_MINI_HIGH = 20;
    int BUTN_NORM_WIDH = 24;
    int BUTN_NORM_HIGH = 22;

    /**
     * 用户登录
     */
    int SIGN_IN = 0;
    /**
     * 权限验证
     */
    int SIGN_RS = SIGN_IN + 1;
    /**
     * 用户注册
     */
    int SIGN_UP = SIGN_RS + 1;
    /**
     * 口令找回
     */
    int SIGN_FP = SIGN_UP + 1;
    /**
     * 更改登录口令
     */
    int SIGN_PK = SIGN_FP + 1;
    /**
     * 更改安全口令
     */
    int SIGN_SK = SIGN_PK + 1;
    /**
     * 添加附属用户
     */
    int SIGN_SU = SIGN_SK + 1;
    /**
     * 在线用户登录
     */
    int SIGN_NW = SIGN_SU + 1;

    String BEAN_PROP = "beanprop";
    String BEAN_INFO = BEAN_PROP + ConsDat.INDX_INFO;// "infobean";
    String BEAN_TEXT = BEAN_PROP + ConsDat.INDX_TEXT;// "textbean";
    String BEAN_PWDS = BEAN_PROP + ConsDat.INDX_PWDS;// "pwdsbean";
    String BEAN_LINK = BEAN_PROP + ConsDat.INDX_LINK;// "linkbean";
    String BEAN_MAIL = BEAN_PROP + ConsDat.INDX_MAIL;// "mailbean";
    String BEAN_DATE = BEAN_PROP + ConsDat.INDX_DATE;// "datebean";
    String BEAN_AREA = BEAN_PROP + ConsDat.INDX_AREA;// "areabean";
    String BEAN_FILE = BEAN_PROP + ConsDat.INDX_FILE;// "filebean";
    String BEAN_GMTP = BEAN_PROP + ConsDat.INDX_GMTP;// "gmtpbean";
    String BEAN_GUID = BEAN_PROP + ConsDat.INDX_GUID;// "guidbean";
    String BEAN_META = BEAN_PROP + ConsDat.INDX_META;// "metabean";
    String BEAN_ICON = BEAN_PROP + ConsDat.INDX_ICON;// "iconbean";
    String BEAN_TIME = BEAN_PROP + ConsDat.INDX_TIME;// "timebean";

    /**
     * 图标路径
     */
    String ICON_PATH = "/res/icon/";

    String ICON_LOGO_0016 = "1001.png";
    String ICON_LOGO_0032 = "1002.png";

    String ICON_TREE_CLPS = "2001.png";
    String ICON_TREE_XPND = "2002.png";

    String ICON_DATA_APND = "2101.png";
    String ICON_DATA_SAVE = "2102.png";
    String ICON_DATA_DELT = "2103.png";
    String ICON_DATA_EXIT = "2104.png";
    String ICON_DATA_SRCH = "2105.png";

    String ICON_ITEM_PREV = "2201.png";
    String ICON_ITEM_NEXT = "2202.png";
    String ICON_ITEM_LEFT = "2203.png";
    String ICON_ITEM_RGHT = "2204.png";
    String ICON_ITEM_SAVE = "2204.png";

    String ICON_HIST_BACK = "2301.png";
    String ICON_PROP_SIDE = "2302.png";
    String ICON_TOOL_UCFG = "2303.png";
    String ICON_TOOL_HELP = "2304.png";

    String ICON_PROP_COPY = "3001.png";
    String ICON_PROP_UPDT = "3002.png";
    String ICON_PROP_DELT = "3003.png";

    String ICON_PWDS_HIDE = "4201.png";
    String ICON_PWDS_VIEW = "4202.png";
    String ICON_PWDS_GENT = "4211.png";
    String ICON_PWDS_UCFG = "4221.png";

    String ICON_LINK_OPEN = "4301.png";

    String ICON_MAIL_OPEN = "4401.png";

    String ICON_DATE_TIME = "4501.png";

    String ICON_FILE_OPEN = "4601.png";

    String EVENT_FILE_HIDE = "130F_FILE_HIDE";
    String EVENT_FILE_APND = "130F_FILE_APND";
    String EVENT_FILE_SAVE = "130F_FILE_SAVE";
    String EVENT_FILE_OPEN = "130F_FILE_OPEN";
    String EVENT_FILE_DELT = "130F_FILE_DELT";
    String EVENT_EDIT_TEXT = "130F_EDIT_TEXT";
    String EVENT_EDIT_PWDS = "130F_EDIT_PWDS";
    String EVENT_EDIT_LINK = "130F_EDIT_LINK";
    String EVENT_EDIT_MAIL = "130F_EDIT_MAIL";
    String EVENT_EDIT_AREA = "130F_EDIT_AREA";
    String EVENT_EDIT_DATE = "130F_EDIT_DATE";
    String EVENT_EDIT_FILE = "130F_EDIT_FILE";
    String EVENT_EDIT_PREV = "130F_EDIT_PREV";
    String EVENT_EDIT_NEXT = "130F_EDIT_NEXT";
    String EVENT_EDIT_FCUS = "130F_EDIT_FCUS";
    String EVENT_VIEW_TOP1 = "130F_VIEW_TOP1";
    String EVENT_VIEW_EDIT = "130F_VIEW_EDIT";
    String EVENT_VIEW_SIDE = "130F_VIEW_SIDE";
    String EVENT_VIEW_MENU = "130F_VIEW_MENU";
    String EVENT_VIEW_TOOL = "130F_VIEW_TOOL";
    String EVENT_VIEW_FIND = "130F_VIEW_FIND";
    String EVENT_VIEW_INFO = "130F_VIEW_INFO";
    String EVENT_DATA_SKEY = "130F_DATA_SKEY";

    String PROP_CHAR = "130F_CHAR";
    String PROP_IMPT = "130F_IMPT";
    String PROP_INFO = "130F_INFO";
    String PROP_KIND = "130F_KIND";
    String PROP_TPLT = "130F_TPLT";
    String PROP_USET = "130F_USET";
    String PROP_SKEY = "130F_SKEY";
    String PROP_HIST = "130F_HIST";

    char[] UPPER_DIGEST = { 'Q', 'A', 'Z', 'W', 'S', 'X', 'E', 'D', 'C', 'R', 'F', 'V', 'T', 'G', 'B', 'Y' };

    char[] LOWER_DIGEST = { 'q', 'a', 'z', 'w', 's', 'x', 'e', 'd', 'c', 'r', 'f', 'v', 't', 'g', 'b', 'y' };

    char[] UPPER_NUMBER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    char[] LOWER_NUMBER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * 程序运行模式：独立程序
     */
    int MODE_RUN_APP = 0;
    /**
     * 程序运行模式：网络加载
     */
    int MODE_RUN_WEB = 1;

    String[] USER_SALT = {"Winshine.biz", "Amonsoft.com", "Magicpwd.com", "MyIM.im"};
}
