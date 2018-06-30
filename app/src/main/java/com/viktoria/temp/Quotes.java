package com.viktoria.temp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Quotes {

    private int offset = 0;
    private boolean downloaded = false;
    public List<Quote> list = new ArrayList<>();

    public enum Status {
        OK,
        NOTHING,
        ERROR
    }

    public Status getMore() {
        System.setProperty("javax.net.ssl.trustStore", "C:\\programming\\-vkcom.jks");
        if (downloaded)
            return Status.NOTHING;
        try {
            Document html = Jsoup.connect(
                    String.format("https://vk.com/wall-102752537?offset=%s&own=1", offset)
            ).get();
            Elements elements = html.body().getElementsByClass("wall_post_text");
            if (elements.size() == 0) {
                downloaded = true;
                return Status.NOTHING;
            }
            /*elements
                    .forEach(
                            e -> {
                                Quote quote = new Quote(e.ownText());
                                Elements a = e.getElementsByTag("a");
                                if (a.size() > 0) {
                                    String[] tmp = a.get(0).ownText().substring(1).split("_");
                                    quote.surname = tmp[0];
                                    if (tmp.length > 1)
                                        quote.name = tmp[1];
                                    if (tmp.length > 2)
                                        quote.patronymic = tmp[2];
                                }
                                if (a.size() > 1)
                                    quote.school = a.get(1).ownText().substring(1);
                                if (a.size() > 2)
                                    quote.subject = a.get(2).ownText().substring(1);
                                list.add(quote);
                            }
                    );*/
			
			for(Element e:elements) {
				Quote quote = new Quote(e.ownText());
                Elements a = e.getElementsByTag("a");
                if (a.size() > 0) {
                    String[] tmp = a.get(0).ownText().substring(1).split("_");
                    quote.surname = tmp[0];
                    if (tmp.length > 1)
						quote.name = tmp[1];
                    if (tmp.length > 2)
						quote.patronymic = tmp[2];
                }
                if (a.size() > 1)
					quote.school = a.get(1).ownText().substring(1);
                if (a.size() > 2)
					quote.subject = a.get(2).ownText().substring(1);
                list.add(quote);
			}
            offset += 20;
        } catch (IOException e) {
            e.printStackTrace();
            return Status.ERROR;
        }
        return Status.OK;
    }

    public Status getWall() {
        Status r;
        do {
            r = getMore();
        } while (r == Status.OK);
        return r;
    }
}
