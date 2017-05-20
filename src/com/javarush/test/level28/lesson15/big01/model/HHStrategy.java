package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lollipop on 04.07.2016.
 */
public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        Document doc;
        int i = 0;
        List<Vacancy> vacancies = new ArrayList<>();
        try
        {
            while (true)
            {
                doc = getDocument("Kiev", i++);
                Elements elements = (Elements) doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.isEmpty()) break;

                    for (Element element : elements)
                    {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setUrl(element.select("[data-qa=vacancy-serp__vacancy-title]").first().attr("href"));
                        vacancy.setTitle(element.select("[data-qa=vacancy-serp__vacancy-title]").first().text());
                        vacancy.setSiteName("http://hh.ua/");
                        vacancy.setCity(element.select("[data-qa=vacancy-serp__vacancy-address]").first().text());
                        vacancy.setCompanyName(element.select("[data-qa=vacancy-serp__vacancy-employer]").first().text());
                        Element salaryElement = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                        String salary = "";
                        if (salaryElement != null)
                        {
                            salary = salaryElement.text();
                        }
                        vacancy.setSalary(salary);
                        vacancies.add(vacancy);
                    }
                }

        }
        catch (IOException e)
        {
        }
        return vacancies;
    }
    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")
                .referrer("http://google.ru")
                .get();
    }
}
