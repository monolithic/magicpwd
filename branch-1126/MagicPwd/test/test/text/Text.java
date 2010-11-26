/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.text;

import com.magicpwd._comp.WTextBox;
import com.magicpwd._util.Lang;
import com.magicpwd.m.UserMdl;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author aven
 */
public class Text
{

    public Text()
    {
        UserMdl cfg = new UserMdl();
        cfg.loadCfg();

        Lang.loadLang(cfg);

        JFrame frame = new JFrame();
        JTextArea ta = new JTextArea();
        WTextBox tb = new WTextBox(ta);
        tb.initView();
        tb.initLang();
        tb.initData();
        frame.getContentPane().add(ta);
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        new Text();
    }
}
