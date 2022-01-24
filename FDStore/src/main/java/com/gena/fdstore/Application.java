package com.gena.fdstore;

import com.gena.fdstore.data.CustomerData;

public class Application {
    public static void main(String[] args) {
        System.out.println("Java dependent Application");
        CustomerData.customerList().stream().forEach(c-> System.out.println(c.toString()));
    }
}
