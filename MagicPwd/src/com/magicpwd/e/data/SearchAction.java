/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.data;

/**
 *
 * @author Administrator
 */
public class SearchAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        String text = mainFind.getSearchText();
        if (!com.magicpwd._util.Char.isValidate(text))
        {
            Lang.showMesg(this, LangRes.P30F7A18, "请输入您要查询的关键字，多个关键字可以使用空格或加号进行分隔！");
            mainFind.requestFocus();
            return;
        }

        boolean b = coreMdl.getListMdl().findName(text);
        if (!b)
        {
            Lang.showMesg(this, LangRes.P30F7A19, "查询不到符合您条件的数据，请用空格或加号分隔您的搜索关键字后重试！");
            mainFind.requestFocus();
        }

        queryKey = text;
        isSearch = true;
    }
}
