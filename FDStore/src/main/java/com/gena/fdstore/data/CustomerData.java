package com.gena.fdstore.data;

import com.gena.fdstore.domain.Customer;
import java.util.Arrays;
import java.util.List;

public class CustomerData {
    public static List<Customer> customerList() {
        return Arrays.asList(
                new Customer("1", "Ivan", "Ivanov"),
                new Customer("2", "Petia", "Petrov"),
                new Customer("3", "Sveta", "Svetlanova")
        );
    }
}
