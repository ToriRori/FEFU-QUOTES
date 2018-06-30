package com.viktoria.temp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        Quotes quotes = new Quotes();
        quotes.getMore();
        quotes.getMore();
        quotes.getMore();
        quotes.getMore();
        System.out.println(quotes.list.size());
 /*       quotes.list.forEach(q -> System.out.println(
                            String.format(
                                    "%s\n%s %s %s\n===============",
                                    q.quote,
                                    q.name,
                                    q.school,
                                    q.subject
                            )
                    )
            );*/
    }
}

