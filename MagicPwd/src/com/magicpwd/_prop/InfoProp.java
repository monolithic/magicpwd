/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._prop;

import com.magicpwd._comp.LnkLabel;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IPropBean;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import java.util.Calendar;

/**
 *
 * @author Amon
 */
public class InfoProp extends javax.swing.JPanel implements IPropBean
{

    public void initView()
    {
        lt_Soft = new javax.swing.JLabel();
        lc_Soft = new javax.swing.JLabel();
        lt_Vers = new javax.swing.JLabel();
        lc_Vers = new javax.swing.JLabel();
        lt_Plat = new javax.swing.JLabel();
        lc_Plat = new javax.swing.JLabel();
        lt_Site = new javax.swing.JLabel();
        lc_Site = new LnkLabel();
        lt_Copy = new javax.swing.JLabel();
        lc_Copy = new javax.swing.JLabel();
        javax.swing.JScrollPane sp = new javax.swing.JScrollPane();
        ta_Note = new javax.swing.JTextArea();

        lc_Site.setLinkUrl(ConsEnv.HOMEPAGE);
        lc_Site.setAutoOpenLink(true);

        ta_Note.setColumns(20);
        ta_Note.setEditable(false);
        ta_Note.setRows(5);
        sp.setViewportView(ta_Note);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lt_Site)
                    .addComponent(lt_Copy)
                    .addComponent(lt_Plat)
                    .addComponent(lt_Vers)
                    .addComponent(lt_Soft))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lc_Soft)
                    .addComponent(lc_Vers)
                    .addComponent(lc_Plat)
                    .addComponent(lc_Site)
                    .addComponent(lc_Copy))
                .addContainerGap(250, Short.MAX_VALUE))
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lt_Soft)
                    .addComponent(lc_Soft))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lt_Vers)
                    .addComponent(lc_Vers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lt_Plat)
                    .addComponent(lc_Plat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lt_Site)
                    .addComponent(lc_Site))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lt_Copy)
                    .addComponent(lc_Copy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
        );
    }

    public void initLang()
    {
        Lang.setWText(lt_Soft, LangRes.P30F83D1, "软件：");

        Lang.setWText(lt_Vers, LangRes.P30F83D2, "版本：");

        Lang.setWText(lt_Plat, LangRes.P30F83D3, "平台：");

        Lang.setWText(lt_Site, LangRes.P30F83D4, "首页：");

        Lang.setWText(lt_Copy, LangRes.P30F83D5, "版权：");

        //Lang.setWText(null, LangRes.P30FA50A, "确定(&O)");
    }

    public void initData()
    {
        lc_Soft.setText(ConsEnv.SOFTNAME);

        lc_Vers.setText(ConsEnv.VERSIONS + " Build " + ConsEnv.BUILDER);

        lc_Plat.setText(new StringBuilder(System.getProperty("os.name")).append(' ').append(System.getProperty("os.version")).append(' ').append(System.getProperty("os.arch")).toString());

        lc_Site.setText(ConsEnv.HOMEPAGE);

        lc_Copy.setText(Util.format(ConsEnv.SOFTCOPY, "" + Calendar.getInstance().get(Calendar.YEAR)));

        ta_Note.setText(Lang.getLang("", ""));
    }

    public javax.swing.JPanel getPanel()
    {
        return this;
    }

    private javax.swing.JLabel lc_Copy;
    private javax.swing.JLabel lc_Plat;
    private LnkLabel lc_Site;
    private javax.swing.JLabel lc_Soft;
    private javax.swing.JLabel lc_Vers;
    private javax.swing.JLabel lt_Copy;
    private javax.swing.JLabel lt_Plat;
    private javax.swing.JLabel lt_Site;
    private javax.swing.JLabel lt_Soft;
    private javax.swing.JLabel lt_Vers;
    private javax.swing.JTextArea ta_Note;
}
