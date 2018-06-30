package com.viktoria.temp;

public class Quote {

    public String quote;
    public String surname;
    public String name;
    public String patronymic;
    public String school;
    public String subject;

    public Quote(String quote) {
        this.quote = quote;
    }
    public Quote(String quote, String surname, String name, String  patronymic, String school, String subject)
    {
        this.name = name;
        this.patronymic = patronymic;
        this.school = school;
        this.quote = quote;
        this.subject = subject;
        this.surname = surname;
    }
}
