package com.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class BinaryConversion {
	StringBuilder value;
	public String toBinary(String text) throws UnsupportedEncodingException {
        
        byte[] bytes = text.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            value = binary.append(Integer.toBinaryString((int) b));
        
        }
        return value.toString();
	}
	
	public String btos(String input){
		return Arrays.stream(input.split("(?<=\\G.{8})"))/* regex to split the bits array by 8*/
                .parallel()
                .map(eightBits -> (char)Integer.parseInt(eightBits, 2))
                .collect(
                                StringBuilder::new,
                                StringBuilder::append,
                                StringBuilder::append
                ).toString();
	}
}
