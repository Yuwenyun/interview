package com.owen.techs.collections;

import com.google.common.collect.Range;

public class RangeDemo {

    public static void main(String[] args)
    {
        Range<Integer> range1 = Range.open(0, 10); // (0, 10)
        Range<Integer> range2 = Range.openClosed(0, 10); // (0, 10]
        Range<Integer> range3 = Range.closed(0, 10); // [0, 10]
        Range<Integer> range4 = Range.closedOpen(0, 10); // [0, 10)
        Range<Integer> range5 = Range.atLeast(0); // [0, n)
        Range<Integer> range6 = Range.greaterThan(0); // (0, n)
        Range<Integer> range7 = Range.atMost(10); // (n, 10]
        Range<Integer> range8 = Range.lessThan(10); // (n, 10)

        System.out.println(range1.contains(5));
        System.out.println(range2.hasLowerBound());
        System.out.println(range3.hasLowerBound());
        System.out.println(range5.hasUpperBound());

        System.out.println(range4.isConnected(range6)); // two ranges have element in common
        System.out.println(range7.intersection(range1)); // get common elements
        System.out.println(range8);
    }
}
