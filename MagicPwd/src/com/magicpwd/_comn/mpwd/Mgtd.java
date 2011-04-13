/*
 *  Copyright (C) 2011 Aven
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd._comn.mpwd;

import java.io.Serializable;

/**
 *
 * @author Aven
 */
public class Mgtd implements Serializable
{

    /**任务类型*/
    private int P30F0701;
    /**任务级别*/
    private int P30F0702;
    /**任务状态*/
    private int P30F0703;
    /**可否共用*/
    private int P30F0704;
    /**提醒周期*/
    private int P30F0705;
    /**提示方式*/
    private int P30F0706;
    /**完成度*/
    private int P30F0707;
    /**任务索引*/
    private String P30F0708;
    /**上级任务*/
    private String P30F0709;
    /**前置任务*/
    private String P30F070A;
    /**任务名称*/
    private String P30F070B;
    /**起始时间*/
    private Long P30F070C;
    /**结束时间*/
    private Long P30F070D;
    /**执行时间*/
    private Long P30F070E;
    /**指定时间*/
    private Long P30F070F;
    /**提醒间隔*/
    private Integer P30F0710;
    /**表达式*/
    private String P30F0711;
    /**执行参数*/
    private String P30F0712;
    /**相关说明*/
    private String P30F0713;

    /**
     * 任务类型
     * @return the P30F0701
     */
    public int getP30F0701()
    {
        return P30F0701;
    }

    /**
     * 任务类型
     * @param P30F0701 the P30F0701 to set
     */
    public void setP30F0701(int P30F0701)
    {
        this.P30F0701 = P30F0701;
    }

    /**
     * 任务级别
     * @return the P30F0702
     */
    public int getP30F0702()
    {
        return P30F0702;
    }

    /**
     * 任务级别
     * @param P30F0702 the P30F0702 to set
     */
    public void setP30F0702(int P30F0702)
    {
        this.P30F0702 = P30F0702;
    }

    /**
     * @return the P30F0703
     */
    public int getP30F0703()
    {
        return P30F0703;
    }

    /**
     * @param P30F0703 the P30F0703 to set
     */
    public void setP30F0703(int P30F0703)
    {
        this.P30F0703 = P30F0703;
    }

    /**
     * @return the P30F0704
     */
    public int getP30F0704()
    {
        return P30F0704;
    }

    /**
     * @param P30F0704 the P30F0704 to set
     */
    public void setP30F0704(int P30F0704)
    {
        this.P30F0704 = P30F0704;
    }

    /**
     * @return the P30F0705
     */
    public int getP30F0705()
    {
        return P30F0705;
    }

    /**
     * @param P30F0705 the P30F0705 to set
     */
    public void setP30F0705(int P30F0705)
    {
        this.P30F0705 = P30F0705;
    }

    /**
     * @return the P30F0706
     */
    public int getP30F0706()
    {
        return P30F0706;
    }

    /**
     * @param P30F0706 the P30F0706 to set
     */
    public void setP30F0706(int P30F0706)
    {
        this.P30F0706 = P30F0706;
    }

    /**
     * @return the P30F0707
     */
    public int getP30F0707()
    {
        return P30F0707;
    }

    /**
     * @param P30F0707 the P30F0707 to set
     */
    public void setP30F0707(int P30F0707)
    {
        this.P30F0707 = P30F0707;
    }

    /**
     * @return the P30F0708
     */
    public String getP30F0708()
    {
        return P30F0708;
    }

    /**
     * @param P30F0708 the P30F0708 to set
     */
    public void setP30F0708(String P30F0708)
    {
        this.P30F0708 = P30F0708;
    }

    /**
     * @return the P30F0709
     */
    public String getP30F0709()
    {
        return P30F0709;
    }

    /**
     * @param P30F0709 the P30F0709 to set
     */
    public void setP30F0709(String P30F0709)
    {
        this.P30F0709 = P30F0709;
    }

    /**
     * @return the P30F070A
     */
    public String getP30F070A()
    {
        return P30F070A;
    }

    /**
     * @param P30F070A the P30F070A to set
     */
    public void setP30F070A(String P30F070A)
    {
        this.P30F070A = P30F070A;
    }

    /**
     * @return the P30F070B
     */
    public String getP30F070B()
    {
        return P30F070B;
    }

    /**
     * @param P30F070B the P30F070B to set
     */
    public void setP30F070B(String P30F070B)
    {
        this.P30F070B = P30F070B;
    }

    /**
     * 起始时间
     * @return the P30F070C
     */
    public Long getP30F070C()
    {
        return P30F070C;
    }

    /**
     * 起始时间
     * @param P30F070C the P30F070C to set
     */
    public void setP30F070C(Long P30F070C)
    {
        this.P30F070C = P30F070C;
    }

    /**
     * 结束时间
     * @return the P30F070D
     */
    public Long getP30F070D()
    {
        return P30F070D;
    }

    /**
     * 结束时间
     * @param P30F070D the P30F070D to set
     */
    public void setP30F070D(Long P30F070D)
    {
        this.P30F070D = P30F070D;
    }

    /**
     * 执行时间
     * @return the P30F070E
     */
    public Long getP30F070E()
    {
        return P30F070E;
    }

    /**
     * 执行时间
     * @param P30F070E the P30F070E to set
     */
    public void setP30F070E(Long P30F070E)
    {
        this.P30F070E = P30F070E;
    }

    /**
     * 指定时间
     * @return the P30F070F
     */
    public Long getP30F070F()
    {
        return P30F070F;
    }

    /**
     * 指定时间
     * @param P30F070F the P30F070F to set
     */
    public void setP30F070F(Long P30F070F)
    {
        this.P30F070F = P30F070F;
    }

    /**
     * @return the P30F0710
     */
    public Integer getP30F0710()
    {
        return P30F0710;
    }

    /**
     * @param P30F0710 the P30F0710 to set
     */
    public void setP30F0710(Integer P30F0710)
    {
        this.P30F0710 = P30F0710;
    }

    /**
     * 表达式
     * @return the P30F0711
     */
    public String getP30F0711()
    {
        return P30F0711;
    }

    /**
     * 表达式
     * @param P30F0711 the P30F0711 to set
     */
    public void setP30F0711(String P30F0711)
    {
        this.P30F0711 = P30F0711;
    }

    /**
     * @return the P30F0712
     */
    public String getP30F0712()
    {
        return P30F0712;
    }

    /**
     * @param P30F0712 the P30F0712 to set
     */
    public void setP30F0712(String P30F0712)
    {
        this.P30F0712 = P30F0712;
    }

    /**
     * @return the P30F0713
     */
    public String getP30F0713()
    {
        return P30F0713;
    }

    /**
     * @param P30F0713 the P30F0713 to set
     */
    public void setP30F0713(String P30F0713)
    {
        this.P30F0713 = P30F0713;
    }
}
