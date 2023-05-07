package com.example.example1;

import java.util.List;

public interface ProductDAO {

    List<Product> findAllWithCriteria();

    ProductStats fetchStats();

}
