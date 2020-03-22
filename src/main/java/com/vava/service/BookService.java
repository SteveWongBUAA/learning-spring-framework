package com.vava.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vava.dao.BookDao;

/**
 *
 */
@Service
public class BookService {
    //    @Qualifier("bookDao2")
    //    @Autowired(required = false)
    //    @Resource(name = "bookDao2")
    @Inject
    private BookDao bookDao;

    public void print() {
        System.out.println(bookDao);
    }
}
