package plan;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.magicpwd.v.mwiz.NormPtn;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Test
{

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex)
        {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        NormPtn norm = new NormPtn(null, null);
        norm.initView();
        norm.initLang();
        norm.initData();
        norm.setVisible(true);
        norm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.print(norm.getSize());
    }
}
