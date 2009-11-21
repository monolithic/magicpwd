/**
 * 
 */
package com.magicpwd._util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author amon
 * 
 */
public class Jzip
{
    private static byte[] buff;

    public static void zip(String zipFileName, String... srcFileList) throws IOException
    {
        File zipFile = new File(zipFileName);

        // 文件是否存在
        if (!zipFile.exists())
        {
            zipFile.getParentFile().mkdirs();
            zipFile.createNewFile();
        }

        // 文件是否文档
        if (!zipFile.isFile())
        {
            return;
        }

        // 文件是否可写
        if (!zipFile.canWrite())
        {
            return;
        }

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(zipFile));
        ZipOutputStream zos = new ZipOutputStream(bos);
        buff = new byte[2048];
        for (String name : srcFileList)
        {
            zip(zos, new File(name), "");
        }
        buff = null;
        zos.flush();
        bos.flush();
        zos.close();
        bos.close();
    }

    public static void zip(File zipFilePath, File... srcFileList) throws IOException
    {
        // 文件是否存在
        if (!zipFilePath.exists())
        {
            zipFilePath.getParentFile().mkdirs();
            zipFilePath.createNewFile();
        }

        // 文件是否文档
        if (!zipFilePath.isFile())
        {
            return;
        }

        // 文件是否可写
        if (!zipFilePath.canWrite())
        {
            return;
        }

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(zipFilePath));
        ZipOutputStream zos = new ZipOutputStream(bos);
        buff = new byte[2048];
        for (File file : srcFileList)
        {
            zip(zos, file, "");
        }
        buff = null;
        zos.flush();
        bos.flush();
        zos.close();
        bos.close();
    }

    private static void zip(ZipOutputStream zos, File file, String base) throws IOException
    {
        base += file.getName();

        if (file.isDirectory())
        {
            base += '/';

            // 顶级目录扩展
            zos.putNextEntry(new ZipEntry(base));
            zos.closeEntry();

            // 递归文件压缩
            File[] list = file.listFiles();
            for (File temp : list)
            {
                zip(zos, temp, base);
            }
        }
        else
        {
            zos.putNextEntry(new ZipEntry(base));
            saveEntry(zos, file);
            zos.closeEntry();
        }
    }

    private static void saveEntry(ZipOutputStream zos, File file) throws IOException
    {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        int size = bis.read(buff);
        while (size != -1)
        {
            zos.write(buff, 0, size);
            size = bis.read(buff);
        }
        bis.close();
    }

    public static void unZip(String zipFileName, String dstFileName) throws IOException
    {
        unZip(new File(zipFileName), new File(dstFileName));
    }

    public static void unZip(File zipFileName, File dstFilePath) throws IOException
    {
        // 文件是否存在
        if (!dstFilePath.exists())
        {
            dstFilePath.mkdirs();
        }

        // 文件是否为目录
        if (!dstFilePath.isDirectory())
        {
            return;
        }

        // 文件是否可写
        if (!dstFilePath.canWrite())
        {
            return;
        }

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFileName));
        ZipInputStream zis = new ZipInputStream(bis);
        buff = new byte[2048];
        unZip(zis, dstFilePath);
        buff = null;
        zis.close();
        bis.close();
    }

    private static void unZip(ZipInputStream zis, File dstFilePath) throws IOException
    {
        ZipEntry zip = zis.getNextEntry();
        while (zip != null)
        {
            File file = new File(dstFilePath, zip.getName());

            if (zip.isDirectory())
            {
                if (!file.exists())
                {
                    file.mkdirs();
                }
            }
            else
            {
                if (!file.exists())
                {
                    file.createNewFile();
                }
                readEntry(zis, file);
            }
            zip = zis.getNextEntry();
        }
    }

    private static void readEntry(ZipInputStream zis, File file) throws IOException
    {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        int size = zis.read(buff);
        while (size >= 0)
        {
            bos.write(buff, 0, size);
            size = zis.read(buff);
        }
        bos.close();
    }
}
