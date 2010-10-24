package test;

import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.mwiz.NormPtn;
import java.io.FileInputStream;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aven
 */
public class Test
{

    public static void main(String[] args)
    {
        try
        {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UserMdl cfg = new UserMdl();
            cfg.loadCfg();
            Lang.loadLang(cfg);
            Bean.readIcon(new FileInputStream("src/res/icon/mwiz16.png"), "mwiz");
            NormPtn p = new NormPtn(null, cfg);
            p.initView();
            p.initLang();
            p.initData();
            p.setVisible(true);
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
}
