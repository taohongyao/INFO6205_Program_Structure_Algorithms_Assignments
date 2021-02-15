/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyDateTest {

    /**
     * Test method for MyDate constructor and getters
     */
    @SuppressWarnings("EqualsWithItself")
    @Test
    public void testMyDate() {
        MyDate target = new MyDate(2018, 9, 11);
        assertEquals("day", 11, target.getDay());
        assertEquals("month", 9, target.getMonth());
        assertEquals("year", 2018, target.getYear());
    }

    public int func(List<Float> list){
        int startIndex = 0;
        int endIndex=list.size()-1;
        int groupNumber =0;
        Collections.sort(list);
        while (startIndex <=endIndex){
            if(startIndex==endIndex){
                groupNumber++;
                break;
            }
            if(list.get(startIndex)+list.get(endIndex)<=3){
                startIndex++;
                endIndex--;
                groupNumber++;
            }else {
                endIndex--;
                groupNumber++;
            }
        }
        return groupNumber;
    }

    /**
     * Test method for MyData.getDayOfTheWeek
     */
    @SuppressWarnings("EqualsWithItself")
    @Test
    public void testDayOfWeek() {
        assertEquals("dayOfWeek", 7, new MyDate(2018, 9, 9).getDayOfWeek());
        assertEquals("dayOfWeek", 1, new MyDate(2018, 9, 10).getDayOfWeek());
        assertEquals("dayOfWeek", 2, new MyDate(2018, 9, 11).getDayOfWeek());
        assertEquals("dayOfWeek", 3, new MyDate(2018, 9, 12).getDayOfWeek());
        assertEquals("dayOfWeek", 4, new MyDate(2018, 9, 13).getDayOfWeek());
        assertEquals("dayOfWeek", 5, new MyDate(2018, 9, 14).getDayOfWeek());
        assertEquals("dayOfWeek", 6, new MyDate(2018, 9, 15).getDayOfWeek());
        assertEquals("dayOfWeek", 7, new MyDate(2018, 9, 16).getDayOfWeek());
        assertEquals("dayOfWeek", 3, new MyDate(2018, 12, 12).getDayOfWeek());
        assertEquals("dayOfWeek", 4, new MyDate(2019, 12, 12).getDayOfWeek());
        assertEquals("dayOfWeek", 6, new MyDate(2020, 12, 12).getDayOfWeek());
        assertEquals("dayOfWeek", 7, new MyDate(2021, 12, 12).getDayOfWeek());
    }
}
