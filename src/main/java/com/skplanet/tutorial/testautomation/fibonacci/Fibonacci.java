package com.skplanet.tutorial.testautomation.fibonacci;

public class Fibonacci {
    public static long fib(int n)
    {
        assert n >= 1: "Index must be greater than 1";
  
        if (n == 1)
            return n;
        else
            return fib(n - 1) + fib(n - 2);
    }
}