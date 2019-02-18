package com.owen.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class IPAddressConverterTest {

    @Test
    public void testLegalIPv4Address()
    {
        // test null, length
        Assert.assertFalse(IPAdressConverter.legalIPv4Address(null));
        Assert.assertFalse(IPAdressConverter.legalIPv4Address(""));
        Assert.assertFalse(IPAdressConverter.legalIPv4Address("1.1.1"));
        Assert.assertFalse(IPAdressConverter.legalIPv4Address("255.255.255.255."));

        // test blank
        Assert.assertTrue(IPAdressConverter.legalIPv4Address("196 .168.0.1"));
        Assert.assertTrue(IPAdressConverter.legalIPv4Address("196. 168.0.1 "));
        Assert.assertTrue(IPAdressConverter.legalIPv4Address("196. 168 . 0.1 "));
        Assert.assertFalse(IPAdressConverter.legalIPv4Address("196 1.168.0.1 "));

        // test range
        Assert.assertTrue(IPAdressConverter.legalIPv4Address("196.168.0.1"));
        Assert.assertTrue(IPAdressConverter.legalIPv4Address("0.0.0.0"));
        Assert.assertTrue(IPAdressConverter.legalIPv4Address("255.255.255.255"));
        Assert.assertFalse(IPAdressConverter.legalIPv4Address("0.10.100.1000"));

        // test other char
        Assert.assertFalse(IPAdressConverter.legalIPv4Address("163.a.100.5"));
        Assert.assertFalse(IPAdressConverter.legalIPv4Address("163.$.100.5"));
        Assert.assertFalse(IPAdressConverter.legalIPv4Address("163.10.100.5."));
    }
}
