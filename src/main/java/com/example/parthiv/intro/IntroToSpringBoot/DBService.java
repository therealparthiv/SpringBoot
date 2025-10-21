package com.example.parthiv.intro.IntroToSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    //field injection

//    @Autowired
//    private DB db;

    final private DB db;

    //constructor injection- Better
    public DBService(DB db)
    {
        this.db=db;
    }

    String getData(){
        return db.getData();
    }
}
