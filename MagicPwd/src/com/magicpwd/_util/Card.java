/*
 *
 */
package com.magicpwd._util;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._face.IEditItem;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.UserMdl;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Amon
 */
public class Card
{

    public static java.io.File exportHtm(java.io.File src, java.io.File dst) throws Exception
    {
        Document doc = new SAXReader().read(src);

        Node node = doc.selectSingleNode("/magicpwd/card/template-res");
        String text;
        if (node != null)
        {
            text = node.getText();
            if (Util.isValidate(text))
            {
                File.copy(new java.io.File(text), dst, true);
            }
        }

        node = doc.selectSingleNode("/magicpwd/card/template-uri");
        if (node == null)
        {
            return null;
        }
        text = node.getText();
        if (!Util.isValidate(text))
        {
            return null;
        }

        return text(text, dst, ".htm");
    }

    public static java.io.File exportTxt(java.io.File src, java.io.File dst) throws Exception
    {
        Document doc = new SAXReader().read(src);
        Node node = doc.selectSingleNode("/magicpwd/card/template-uri");
        if (node == null)
        {
            return null;
        }
        String text = node.getText();
        if (!Util.isValidate(text))
        {
            return null;
        }

        return text(text, dst, ".txt");
    }

    public static java.io.File exportPng(java.io.File src, java.io.File dst) throws Exception
    {
        java.io.InputStream stream = new java.io.FileInputStream(src);
        StringBuffer buffer = trim(stream);
        return draw(DocumentHelper.parseText(buffer.toString()), dst);
    }

    public static java.io.File exportSvg(java.io.File src, java.io.File dst) throws Exception
    {
        Document doc = new SAXReader().read(src);
        Node node = doc.selectSingleNode("/magicpwd/card/template-uri");
        if (node == null)
        {
            return null;
        }
        String text = node.getText();
        if (!Util.isValidate(text))
        {
            return null;
        }

        return text(text, dst, ".svg");
    }

    public static java.io.File exportAll(java.io.File src, java.io.File dst)
    {
        return null;
    }

    private static StringBuffer trim(java.io.InputStream stream) throws Exception
    {
        java.io.InputStreamReader reader = new java.io.InputStreamReader(stream);

        StringBuffer buffer = new StringBuffer();
        char[] buf = new char[1024];
        int len = reader.read(buf);
        while (len >= 0)
        {
            buffer.append(buf, 0, len);
            len = reader.read(buf);
        }

        reader.close();
        stream.close();

        GridMdl gm = UserMdl.getGridMdl();
        IEditItem item;
        for (int i = ConsEnv.PWDS_HEAD_SIZE, j = gm.getRowCount(); i < j; i += 1)
        {
            item = gm.getItemAt(i);
            replace(buffer, '#' + item.getName() + '#', item.getData());
        }

        return buffer;
    }

    private static java.io.File text(String src, java.io.File dst, String ext) throws Exception
    {
        java.io.InputStream stream = File.open4Read(src);
        if (stream == null)
        {
            return null;
        }

        StringBuffer buffer = trim(stream);

        IEditItem item = UserMdl.getGridMdl().getItemAt(ConsEnv.PWDS_HEAD_META);
        dst = new java.io.File(dst, item.getName() + ext);
        if (!dst.exists())
        {
            dst.createNewFile();
        }

        java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(dst));
        writer.write(buffer.toString());
        writer.flush();
        writer.close();

        return dst;
    }

    private static java.io.File draw(Document doc, java.io.File dst) throws Exception
    {
        int w = 400;
        int h = 240;
        Node node = doc.selectSingleNode("/magicpwd/card/size");
        if (node != null)
        {
            w = getInteger(node, "width", w);
            h = getInteger(node, "height", h);
        }

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        node = doc.selectSingleNode("/magicpwd/card/background");
        String text;
        if (node != null)
        {
            Color paint = getColor(node, "color", null);
            if (paint != null)
            {
                g2d.setPaint(paint);
                g2d.fillRect(0, 0, w, h);
            }

            text = getString(node, "image", "");
            if (Util.isValidate(text))
            {
                BufferedImage img = ImageIO.read(File.open4Read(text));
                text = getString(node, "type", "stretch");
                if ("stretch".equalsIgnoreCase(text))
                {
                    g2d.drawImage(img, 0, 0, w, h, null);
                }
                else if ("scale".equalsIgnoreCase(text))
                {
                    int w1 = img.getWidth();
                    int h1 = img.getHeight();
                    double dw = w / (double) w1;
                    double dh = h / (double) h1;
                    double d = dw < dh ? dw : dh;
                    w1 *= d;
                    h1 *= d;
                    g2d.drawImage(img, (w - w1) >> 1, (h - h1) >> 1, w1, h1, null);
                }
                else if ("fixed".equalsIgnoreCase(text))
                {
                    int w1 = img.getWidth();
                    int h1 = img.getHeight();
                    g2d.drawImage(img, (w - w1) >> 1, (h - h1) >> 1, w1, h1, null);
                }
                else if ("repeat".equalsIgnoreCase(text))
                {
                    int w1 = img.getWidth();
                    int h1 = img.getHeight();
                    for (int y1 = 0; y1 < h; y1 += h1)
                    {
                        for (int x1 = 0; x1 < w; x1 += w1)
                        {
                            g2d.drawImage(img, x1, y1, w1, h1, null);
                        }
                    }
                }
            }
        }

        List list = doc.selectNodes("/magicpwd/card/draw");
        if (list != null)
        {
            for (int i = 0, j = list.size(); i < j; i += 1)
            {
                node = (Node) list.get(i);
                text = getString(node, "method", "").toLowerCase();
                if (!Util.isValidate(text))
                {
                    continue;
                }
                if ("text".equals(text))
                {
                    drawString(node, g2d);
                    continue;
                }
                if ("image".equals(text))
                {
                    drawImage(node, g2d);
                    continue;
                }
                if ("line".equals(text))
                {
                    drawLine(node, g2d);
                    continue;
                }
                if ("rect".equals(text))
                {
                    drawRect(node, g2d);
                    continue;
                }
                if ("round-rect".equals(text))
                {
                    drawRoundRect(node, g2d);
                    continue;
                }
                if ("arc".equals(text))
                {
                    drawArc(node, g2d);
                    continue;
                }
                if ("polygon".equals(text))
                {
                    drawPolygon(node, g2d);
                    continue;
                }
            }
        }

        IEditItem item = UserMdl.getGridMdl().getItemAt(ConsEnv.PWDS_HEAD_META);
        dst = new java.io.File(dst, item.getName() + ".png");
        if (!dst.exists())
        {
            dst.createNewFile();
        }

        ImageIO.write(image, "png", dst);

        return dst;
    }

    private static void drawString(Node node, Graphics2D g2d) throws Exception
    {
        if (node == null)
        {
            return;
        }
        String text = node.getText();
        if (!Util.isValidate(text))
        {
            return;
        }

        g2d.setFont(getFont(node));
        g2d.setPaint(getPaint(node));
        g2d.drawString(text, getInteger(node, "x", 0), getInteger(node, "y", 0));
    }

    private static void drawImage(Node node, Graphics2D g2d) throws Exception
    {
        if (node == null)
        {
            return;
        }
        String text = node.getText();
        if (!Util.isValidate(text))
        {
            return;
        }

        BufferedImage img = ImageIO.read(File.open4Read(text));
        g2d.drawImage(img, getInteger(node, "x", 0), getInteger(node, "y", 0), getInteger(node, "width", img.getWidth()), getInteger(node, "height", img.getHeight()), null);
    }

    private static void drawLine(Node node, Graphics2D g2d)
    {
        if (node == null)
        {
            return;
        }

        g2d.setPaint(getPaint(node));
        g2d.setStroke(new BasicStroke(getInteger(node, "width", 1)));
        g2d.drawLine(getInteger(node, "x1", 0), getInteger(node, "y1", 0), getInteger(node, "x2", 0), getInteger(node, "y2", 0));
    }

    private static void drawRect(Node node, Graphics2D g2d)
    {
        if (node == null)
        {
            return;
        }

        int x = getInteger(node, "x", 0);
        int y = getInteger(node, "y", 0);
        int w = getInteger(node, "width", 2);
        int h = getInteger(node, "height", 2);
        Color c = getColor(node, "color", null);
        if (c != null)
        {
            g2d.setPaint(c);
            g2d.fillRect(x, y, w, h);
        }

        g2d.setPaint(getColor(node, "border-color", Color.black));
        g2d.setStroke(new BasicStroke(getInteger(node, "border-width", 1)));
        g2d.drawRect(x, y, w, h);
    }

    private static void drawRoundRect(Node node, Graphics2D g2d)
    {
        if (node == null)
        {
            return;
        }

        int x = getInteger(node, "x", 0);
        int y = getInteger(node, "y", 0);
        int w = getInteger(node, "width", 2);
        int h = getInteger(node, "height", 2);
        int aw = getInteger(node, "arc-width", 1);
        int ah = getInteger(node, "arc-height", 1);
        Color c = getColor(node, "color", null);
        if (c != null)
        {
            g2d.setPaint(c);
            g2d.fillRoundRect(x, y, w, h, aw, ah);
        }

        g2d.setPaint(getColor(node, "border-color", Color.black));
        g2d.setStroke(new BasicStroke(getInteger(node, "border-width", 1)));
        g2d.drawRoundRect(x, y, w, h, aw, ah);
    }

    private static void drawArc(Node node, Graphics2D g2d)
    {
        if (node == null)
        {
            return;
        }

        int x = getInteger(node, "x", 0);
        int y = getInteger(node, "y", 0);
        int w = getInteger(node, "width", 2);
        int h = getInteger(node, "height", 2);
        int sa = getInteger(node, "start-angle", 1);
        int aa = getInteger(node, "arc-angle", 1);
        Color c = getColor(node, "color", null);
        if (c != null)
        {
            g2d.setPaint(c);
            g2d.fillArc(x, y, w, h, sa, aa);
        }

        g2d.setPaint(getColor(node, "border-color", Color.black));
        g2d.setStroke(new BasicStroke(getInteger(node, "border-width", 1)));
        g2d.drawArc(x, y, w, h, sa, aa);
    }

    private static void drawPolygon(Node node, Graphics2D g2d)
    {
        if (node == null)
        {
            return;
        }

        String sx = getString(node, "x-array", "");
        String sy = getString(node, "y-array", "");
        if (!Util.isValidate(sx) || !Util.isValidate(sy))
        {
            return;
        }

        String[] ax = sx.split("[^\\d]");
        String[] ay = sy.split("[^\\d]");
        if (ax == null || ay == null || ax.length < 1 || ax.length != ay.length)
        {
            return;
        }

        Polygon p = new Polygon();
        for (int i = 0; i < ax.length; i += 1)
        {
            p.addPoint(Integer.parseInt(ax[i]), Integer.parseInt(ay[i]));
        }

        Color c = getColor(node, "color", null);
        if (c != null)
        {
            g2d.setPaint(c);
            g2d.fillPolygon(p);
        }

        g2d.setPaint(getColor(node, "border-color", Color.black));
        g2d.setStroke(new BasicStroke(getInteger(node, "border-width", 1)));
        g2d.drawPolygon(p);
    }

    private static void replace(StringBuffer buf, String src, String dst)
    {
        int i = buf.indexOf(src);
        int k = src.length();
        while (i > -1)
        {
            buf.replace(i, i + k, dst);
            i = buf.indexOf(src, i + k);
        }
    }

    private static int getInteger(Node node, String prop, int dval)
    {
        int v = dval;
        if (node != null)
        {
            if (node instanceof Element)
            {
                Element element = (Element) node;
                prop = (element.attributeValue(prop) + "").trim();
                if (Pattern.matches("^\\d+$", prop))
                {
                    v = Integer.parseInt(prop);
                }
            }
        }
        return v;
    }

    private static String getString(Node node, String prop, String dval)
    {
        String t = dval;
        if (node != null)
        {
            if (node instanceof Element)
            {
                Element element = (Element) node;
                prop = element.attributeValue(prop);
                if (Util.isValidate(prop))
                {
                    t = prop;
                }
            }
        }
        return t;
    }

    private static Color getColor(Node node, String prop, Color dval)
    {
        Color t = dval;
        if (node != null)
        {
            if (node instanceof Element)
            {
                Element element = (Element) node;
                prop = (element.attributeValue(prop) + "").toLowerCase();
                if (Pattern.matches("^[#]?[0123456789abcdef]{6}$", prop))
                {
                    if (prop.charAt(0) == '#')
                    {
                        prop = prop.substring(1);
                    }
                    t = new Color(Integer.parseInt(prop, 16));
                }
            }
        }
        return t;
    }

    public static Paint getPaint(Node node)
    {
        return getColor(node, "color", Color.black);
    }

    private static Font getFont(Node node)
    {
        return new Font(getString(node, "font-name", "Dialog"), getInteger(node, "font-style", 0), getInteger(node, "font-size", 12));
    }
}
