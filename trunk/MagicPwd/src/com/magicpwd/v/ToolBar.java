/**
 * 
 */
package com.magicpwd.v;

import javax.swing.JToolBar;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.c.ToolEvt;
import com.magicpwd.m.UserCfg;

/**
 * @author Amon
 */
public class ToolBar extends JToolBar
{

    private UserCfg userCfg;

    public ToolBar(UserCfg userCfg)
    {
        this.userCfg = userCfg;
    }
/*
    public void initView()
    {
        bt_ApndData = new javax.swing.JButton();
        bt_ApndData.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_APND));
        bt_ApndData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                te_ToolEvent.fileApndActionPerformed(evt);
            }
        });
        add(bt_ApndData);

        bt_SaveData = new javax.swing.JButton();
        bt_SaveData.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_SAVE));
        bt_SaveData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                te_ToolEvent.fileSaveActionPerformed(evt);
            }
        });
        add(bt_SaveData);

        addSeparator();

        bt_MovePrev = new javax.swing.JButton();
        bt_MovePrev.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_PREV));
        bt_MovePrev.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                te_ToolEvent.editPrevActionPerformed(evt);
            }
        });
        add(bt_MovePrev);

        bt_MoveNext = new javax.swing.JButton();
        bt_MoveNext.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_NEXT));
        bt_MoveNext.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                te_ToolEvent.editNextActionPerformed(evt);
            }
        });
        add(bt_MoveNext);

        addSeparator();

        bt_HistBack = new javax.swing.JToggleButton();
        bt_HistBack.setIcon(Util.getIcon(ConsEnv.ICON_TOOL_HIST));
        bt_HistBack.setSelected(true);
        bt_HistBack.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                te_ToolEvent.histBackActionPerformed(evt);
            }
        });
        add(bt_HistBack);

        bt_PropSide = new javax.swing.JToggleButton();
        bt_PropSide.setIcon(Util.getIcon(ConsEnv.ICON_TOOL_TAIL));
        bt_PropSide.setSelected(userCfg.isEditWnd());
        bt_PropSide.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                te_ToolEvent.viewSideActionPerformed(evt);
            }
        });
        add(bt_PropSide);

        addSeparator();

        bt_HelpHelp = new javax.swing.JButton();
        bt_HelpHelp.setIcon(Util.getIcon(ConsEnv.ICON_SOFT_HELP));
        bt_HelpHelp.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                te_ToolEvent.helpHelpActionPerformed(evt);
            }
        });
        add(bt_HelpHelp);
    }

    public void initLang()
    {
        Lang.setWTips(bt_ApndData, LangRes.P30F7B01, "添加数据");
        Lang.setWTips(bt_SaveData, LangRes.P30F7B02, "保存数据");

        Lang.setWTips(bt_MovePrev, LangRes.P30F7B03, "向上移动");
        Lang.setWTips(bt_MoveNext, LangRes.P30F7B04, "向下移动");

        Lang.setWTips(bt_HistBack, LangRes.P30F7B05, "保存历史记录");
        Lang.setWTips(bt_PropSide, LangRes.P30F7B06, "分离属性窗口");

        Lang.setWTips(bt_HelpHelp, LangRes.P30F7B07, "帮助");
    }

    public void initData()
    {
    }

    public void setToolEvent(ToolEvt event)
    {
        te_ToolEvent = event;
    }

    public void setHistBackSelected(boolean selected)
    {
        bt_HistBack.setSelected(selected);
    }

    public boolean isHistBackSelected()
    {
        return bt_HistBack.isSelected();
    }

    public void setPropSideSelected(boolean selected)
    {
        bt_PropSide.setSelected(selected);
    }
    private javax.swing.JButton bt_ApndData;
    private javax.swing.JButton bt_SaveData;
    private javax.swing.JButton bt_MovePrev;
    private javax.swing.JButton bt_MoveNext;
    private javax.swing.JToggleButton bt_HistBack;
    private javax.swing.JToggleButton bt_PropSide;
    private javax.swing.JButton bt_HelpHelp;*/
}
