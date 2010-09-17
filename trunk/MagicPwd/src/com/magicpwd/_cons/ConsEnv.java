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
    String VERSIONS = "V3.3.8.13";
    /**
     * 软件信息：软件代码
     */
    String SOFTCODE = "130F0000";
    /**
     * 软件信息：软件名称
     */
    String SOFTNAME = "魔方密码";
    /**
     * 软件信息：软件首页
     */
    String HOMEPAGE = "http://magicpwd.com/";
    String BLOGSITE = "http://magicpwd.com/blog/";
    String MLOGSITE = "http://t.sina.com.cn/mpwd";
    /**
     * 软件信息：构建日期
     */
    String BUILDER = "2010-08-05";
    /**
     * 软件信息：软件版权
     */
    String SOFTCOPY = "© {0} MagicPwd.com";
    String SOFTMAIL = "Amon@magicpwd.com";
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
    String FILE_ATTACHMENT = ".ama";
    String FILE_DATA = "amon";
    String FILE_BACK = FILE_DATA + "_{0}.backup";
    String FILE_SYNC = "magicpwd.amb";
    String FILE_DATE = "yyyyMMdd-HHmmss";
    String VIEW_DATE = "yyyy-MM-dd HH:mm:ss";
    String IMAGE_FORMAT = "png";
    char PWDS_MASK = '*';
    String DIR_BAK = "bak";
    String DIR_DAT = "dat";
    String DIR_AMA = "ama";
    String DIR_ICO = "ico";
    String DIR_EXT = "ext";
    String DIR_MAIL = "mail";
    String DIR_SKIN = "skin";
    String DIR_LOOK = "look";
    String DIR_FEEL = "feel";
    ///////////////////////////////////////////////////////
    // 类别数据显示排序
    ///////////////////////////////////////////////////////
    /**
     * 记录头部预留属性
     */
    int PWDS_HEAD_GUID = 0;
    int PWDS_HEAD_META = PWDS_HEAD_GUID + 1;
    int PWDS_HEAD_LOGO = PWDS_HEAD_META + 1;
    int PWDS_HEAD_HINT = PWDS_HEAD_LOGO + 1;
    int PWDS_HEAD_SIZE = PWDS_HEAD_HINT + 1;
    /**
     * 数据保存时数据块大小
     */
    int PWDS_DATA_SIZE = 8192;
    int BUTN_MINI_WIDH = 24;
    int BUTN_MINI_HIGH = 22;
    int BUTN_NORM_WIDH = 26;
    int BUTN_NORM_HIGH = 24;
    /**
     * 用户登录
     */
    int INT_SIGN_IN = 1;
    String STR_SIGN_IN = "signIn";
    /**
     * 权限验证
     */
    int INT_SIGN_RS = INT_SIGN_IN + 1;
    String STR_SIGN_RS = "signRs";
    /**
     * 用户注册
     */
    int INT_SIGN_UP = INT_SIGN_RS + 1;
    String STR_SIGN_UP = "signUp";
    /**
     * 口令找回
     */
    int INT_SIGN_FP = INT_SIGN_UP + 1;
    String STR_SIGN_FP = "signFp";
    /**
     * 更改登录口令
     */
    int INT_SIGN_PK = INT_SIGN_FP + 1;
    String STR_SIGN_PK = "signPk";
    /**
     * 更改安全口令
     */
    int INT_SIGN_SK = INT_SIGN_PK + 1;
    String STR_SIGN_SK = "signSk";
    /**
     * 添加附属用户
     */
    int INT_SIGN_SU = INT_SIGN_SK + 1;
    String STR_SIGN_SU = "signSu";
    /**
     * 在线用户登录
     */
    int INT_SIGN_NW = INT_SIGN_SU + 1;
    String STR_SIGN_NW = "signNw";
    /**
     * 设置云存储用户
     */
    int INT_SIGN_CS = INT_SIGN_NW + 1;
    /////////////////////////////////////////////////////////////////
    String CARD_HTM = "htm";
    String CARD_TXT = "txt";
    String CARD_PNG = "png";
    String CARD_SVG = "svg";
    String CARD_ALL = "*";
    /////////////////////////////////////////////////////////////////
    String STR_SIGN_CS = "signCs";
    String BEAN_PROP = "beanprop";
    String BEAN_INFO = BEAN_PROP + ConsDat.INDX_INFO;// "infobean";
    String BEAN_TEXT = BEAN_PROP + ConsDat.INDX_TEXT;// "textbean";
    String BEAN_PWDS = BEAN_PROP + ConsDat.INDX_PWDS;// "pwdsbean";
    String BEAN_LINK = BEAN_PROP + ConsDat.INDX_LINK;// "linkbean";
    String BEAN_MAIL = BEAN_PROP + ConsDat.INDX_MAIL;// "mailbean";
    String BEAN_DATE = BEAN_PROP + ConsDat.INDX_DATE;// "datebean";
    String BEAN_AREA = BEAN_PROP + ConsDat.INDX_AREA;// "areabean";
    String BEAN_FILE = BEAN_PROP + ConsDat.INDX_FILE;// "filebean";
    String BEAN_GUID = BEAN_PROP + ConsDat.INDX_GUID;// "guidbean";
    String BEAN_META = BEAN_PROP + ConsDat.INDX_META;// "metabean";
    String BEAN_ICON = BEAN_PROP + ConsDat.INDX_LOGO;// "iconbean";
    String BEAN_NOTE = BEAN_PROP + ConsDat.INDX_HINT;// "timebean";
    /**
     * 图标路径
     */
    String ICON_PATH = "/res/icon/";
    int ICON_NONE = 0;// 空白
    int ICON_SOFT_HELP = ICON_NONE + 1;// 帮助
    int ICON_SOFT_EXIT = ICON_SOFT_HELP + 1;// 退出
    int ICON_SOFT_UCFG = ICON_SOFT_EXIT + 1;// 设定
    int ICON_KIND_CLPS = ICON_SOFT_UCFG + 1;// 折叠
    int ICON_KIND_XPND = ICON_KIND_CLPS + 1;// 展开
    int ICON_KEYS_APND = ICON_KIND_XPND + 1;// 新建
    int ICON_KEYS_SAVE = ICON_KEYS_APND + 1;// 保存
    int ICON_KEYS_DELT = ICON_KEYS_SAVE + 1;// 删除
    int ICON_KEYS_MOD0 = ICON_KEYS_DELT + 1;
    int ICON_KEYS_MOD1 = ICON_KEYS_MOD0 + 1;
    int ICON_KEYS_MOD2 = ICON_KEYS_MOD1 + 1;
    int ICON_KEYS_MOD3 = ICON_KEYS_MOD2 + 1;
    int ICON_KEYS_MOD4 = ICON_KEYS_MOD3 + 1;
    int ICON_KEYS_MOD5 = ICON_KEYS_MOD4 + 1;
    int ICON_KEYS_MOD6 = ICON_KEYS_MOD5 + 1;
    int ICON_KEYS_MOD7 = ICON_KEYS_MOD6 + 1;
    int ICON_KEYS_MOD8 = ICON_KEYS_MOD7 + 1;
    int ICON_KEYS_MOD9 = ICON_KEYS_MOD8 + 1;
    int ICON_KEYS_NTN2 = ICON_KEYS_MOD9 + 1;
    int ICON_KEYS_NTN1 = ICON_KEYS_NTN2 + 1;
    int ICON_KEYS_NOTE = ICON_KEYS_NTN1 + 1;
    int ICON_KEYS_NTP1 = ICON_KEYS_NOTE + 1;
    int ICON_KEYS_NTP2 = ICON_KEYS_NTP1 + 1;
    int ICON_ITEM_PREV = ICON_KEYS_NTP2 + 1;// 向上
    int ICON_ITEM_NEXT = ICON_ITEM_PREV + 1;// 向下
    int ICON_ITEM_LEFT = ICON_ITEM_NEXT + 1;// 向左
    int ICON_ITEM_RGHT = ICON_ITEM_LEFT + 1;// 向右
    int ICON_TOOL_HIST = ICON_ITEM_RGHT + 1;// 历史
    int ICON_TOOL_TAIL = ICON_TOOL_HIST + 1;// 分离
    int ICON_PROP_COPY = ICON_TOOL_TAIL + 1;// 复制
    int ICON_PROP_UPDT = ICON_PROP_COPY + 1;// 更新
    int ICON_PROP_DELT = ICON_PROP_UPDT + 1;// 删除
    int ICON_GUID_MAIL = ICON_PROP_DELT + 1;// 邮件
    int ICON_GUID_CARD = ICON_GUID_MAIL + 1;// 卡片
    int ICON_HINT_DATE = ICON_GUID_CARD + 1;// 提醒
    int ICON_PWDS_HIDE = ICON_HINT_DATE + 1;// 隐藏
    int ICON_PWDS_VIEW = ICON_PWDS_HIDE + 1;// 显示
    int ICON_PWDS_GENT = ICON_PWDS_VIEW + 1;// 生成
    int ICON_PWDS_UCFG = ICON_PWDS_GENT + 1;// 选项
    int ICON_HIST_PICK = ICON_PWDS_UCFG + 1;// 恢复
    int ICON_HIST_DROP = ICON_HIST_PICK + 1;// 恢复
    int ICON_LINK_OPEN = ICON_HIST_DROP + 1;// 链接
    int ICON_MAIL_OPEN = ICON_LINK_OPEN + 1;// 邮件
    int ICON_DATE_TIME = ICON_MAIL_OPEN + 1;// 时间
    int ICON_FILE_OPEN = ICON_DATE_TIME + 1;// 打开
    int ICON_FILE_PICK = ICON_FILE_OPEN + 1;// 选择
    int ICON_NOTE_OPEN = ICON_FILE_PICK + 1;// 选择
    int ICON_NOTE_SRCH = ICON_NOTE_OPEN + 1;// 选择
    int ICON_MAIL_FILE = ICON_NOTE_SRCH + 1;// 附件
    int ICON_SIZE = ICON_MAIL_FILE + 1;
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
    String EVENT_EDIT_GUID = "130F_EDIT_GUID";
    String EVENT_EDIT_KEYS = "130F_EDIT_KEYS";
    String EVENT_EDIT_ITEM = "130F_EDIT_ITEM";
    String EVENT_VIEW_PREV = "130F_VIEW_PREV";
    String EVENT_VIEW_NEXT = "130F_VIEW_NEXT";
    String EVENT_VIEW_TOP1 = "130F_VIEW_TOP1";
    String EVENT_VIEW_EDIT = "130F_VIEW_EDIT";
    String EVENT_VIEW_SIDE = "130F_VIEW_SIDE";
    String EVENT_VIEW_MENU = "130F_VIEW_MENU";
    String EVENT_VIEW_TOOL = "130F_VIEW_TOOL";
    String EVENT_VIEW_FIND = "130F_VIEW_FIND";
    String EVENT_VIEW_INFO = "130F_VIEW_INFO";
    String EVENT_DATA_SKEY = "130F_DATA_SKEY";
    String EVENT_NOTE_ALLS = "130F_NOTE_ALLS";
    String EVENT_NOTE_CUTS = "130F_NOTE_CUTS";
    String EVENT_NOTE_COPY = "130F_NOTE_COPY";
    String EVENT_NOTE_PAST = "130F_NOTE_PAST";
    String EVENT_NOTE_UNDO = "130F_NOTE_UNDO";
    String EVENT_NOTE_REDO = "130F_NOTE_REDO";
    String PROP_CHAR = "130F_CHAR";
    String PROP_IMPT = "130F_IMPT";
    String PROP_INFO = "130F_INFO";
    String PROP_KIND = "130F_KIND";
    String PROP_TPLT = "130F_TPLT";
    String PROP_USET = "130F_USET";
    String PROP_SKEY = "130F_SKEY";
    String PROP_JAVA = "130F_JAVA";
    String PROP_HIST = "130F_HIST";
    char[] UPPER_DIGEST =
    {
        'Q', 'A', 'Z', 'W', 'S', 'X', 'E', 'D', 'C', 'R', 'F', 'V', 'T', 'G', 'B', 'Y'
    };
    char[] LOWER_DIGEST =
    {
        'q', 'a', 'z', 'w', 's', 'x', 'e', 'd', 'c', 'r', 'f', 'v', 't', 'g', 'b', 'y'
    };
    char[] UPPER_NUMBER =
    {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    char[] LOWER_NUMBER =
    {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    /**
     * 程序运行模式：独立程序
     */
    int MODE_RUN_APP = 0;
    /**
     * 程序运行模式：网络加载
     */
    int MODE_RUN_WEB = 1;
    int VIEW_MAIN = 0;
    int VIEW_NORM = 1;
    int VIEW_MINI = 2;
    String CHAR_ALT_KEY = "@";
    String SKIN_DEFAULT = "default";
    String SKIN_SYSTEM = "system";
    String SKIN_LOOK_FILE = "look.aml";
    String SKIN_FEEL_FILE = "feel.amf";
    String[] USER_SALT =
    {
        "Winshine.biz", "Amonsoft.com", "Magicpwd.com", "MyIM.im"
    };
}
