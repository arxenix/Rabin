package com.ankursundara.rabin;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class RabinTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        int counter = 0;
        int trials = 1000;
        for(int i=0;i<trials;i++) {
            BigInteger[] key = Rabin.genKey(512);
            BigInteger N = key[0];
            BigInteger p = key[1];
            BigInteger q = key[2];
            //System.out.println(N);
            //System.out.println(p+","+q);

            String s = "Hello world!";
            BigInteger m = new BigInteger(s.getBytes(Charset.forName("ascii")));
            BigInteger c = Rabin.encrypt(m, N);

            boolean worked = false;
            BigInteger[] m2 = Rabin.decrypt(c, p, q);
            for(BigInteger b:m2) {
                String dec = new String(b.toByteArray(), Charset.forName("ascii"));
                if(dec.equals(s)) {
                    worked = true;
                }
            }
            if(worked)counter++;
        }
        System.out.println("worked "+counter+"/"+trials+" times");
    }
}
