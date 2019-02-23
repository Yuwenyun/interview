package com.owen.algorithm.nomura;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class _6GetLongestTimeSlot {
    public static void main(String[] args) throws IOException
    {
        String input = "Sun 10:00-20:00\n" +
                "Fri 05:00-10:00\n" +
                "Sat 10:00-24:00\n" +
                "Sun 01:00-04:00\n" +
                "Sat 02:00-06:00\n" +
                "Tue 03:30-18:15\n" +
                "Tue 19:00-20:00\n" +
                "Wed 04:25-15:14\n" +
                "Wed 15:14-22:40\n" +
                "Thu 00:00-23:59\n" +
                "Mon 05:00-13:00\n" +
                "Mon 15:00-21:00";
        System.out.println(new _6GetLongestTimeSlot().getLongestSlot(input));
    }

    public int getLongestSlot(String schedual)
    {
        String[] meetings = Arrays.stream(schedual.split("\\n")).sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                WeekDay day1 = WeekDay.valueOf(o1.substring(0, 3));
                WeekDay day2 = WeekDay.valueOf(o2.substring(0, 3));
                if(day1.ordinal() > day2.ordinal()){
                    return 1;
                }
                else if(day1.ordinal() < day2.ordinal()){
                    return -1;
                }
                else{
                    String time1 = o1.substring(3);
                    String time2 = o2.substring(3);
                    return time1.compareTo(time2);
                }
            }
        }).collect(Collectors.toList()).toArray(new String[0]);

        int max = datePeriod("Mon 00:00-00:00", meetings[0]);
        for(int i = 0; i < meetings.length - 1; i++){
            int result = datePeriod(meetings[i], meetings[i + 1]);
            System.out.println(meetings[i] + " -> " + meetings[i + 1] + " : " + result);
            if(max < result){
                max = result;
            }
        }
        return max;
    }

    /**
     * calculate the minutes during the give time
     * @param from from string like "Mon 05:00-13:00"
     * @param to to string like "Tue 15:00-21:00"
     * @return return the minutes result
     */
    private int datePeriod(String from, String to)
    {
        String[] froms = getDateAndTime(from);
        String[] tos = getDateAndTime(to);
        if(froms[0].equals(tos[0])){
            return timePeriod(froms[2], tos[1]);
        }
        else{
            int days = WeekDay.valueOf(tos[0]).ordinal() - WeekDay.valueOf(froms[0]).ordinal();
            return timePeriod(froms[2], "24:00") +
                    timePeriod("00:00", tos[1]) +
                    timePeriod("00:00", "24:00") * (days - 1);
        }
    }

    /**
     * split the give date time string into three parts
     * @param item the provided string
     * @return the tokens like {"Mon", "05:00", "13:00"}
     */
    private String[] getDateAndTime(String item)
    {
        return Arrays.stream(item.split(" "))
                .map(str -> str.split("-"))
                .flatMap(strs -> Arrays.stream(strs))
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

    /**
     * calculate the minutes of the given time
     * @param from from string like "05:00"
     * @param to to string like "13:00"
     * @return the minute result
     */
    private int timePeriod(String from, String to)
    {
        Integer[] froms = (Integer[])Arrays.stream(from.split(":"))
                .map(str -> convertToInt(str))
                .collect(Collectors.toList())
                .toArray(new Integer[0]);
        Integer[] tos = (Integer[])Arrays.stream(to.split(":"))
                .map(str -> convertToInt(str))
                .collect(Collectors.toList())
                .toArray(new Integer[0]);
        int result = 0;
        if(tos[1] >= froms[1]){
            result = tos[1] - froms[1];
            result += (tos[0] - froms[0]) * 60;
        }
        else{
            result = (tos[0] - froms[0]) * 60 - (froms[1] - tos[1]);
        }
        return result;
    }

    /**
     * convert a string number to an integer
     * @param value string number like "13"
     * @return the integer type value
     */
    private static int convertToInt(String value){
        int result = 0;
        for(int i = 0; i < value.length(); i++){
            int num = value.charAt(i) - '0';
            result = result * 10 + num;
        }
        return result;
    }

    enum WeekDay{
        Mon, Tue, Wed, Thu, Fri, Sat, Sun
    }
}
