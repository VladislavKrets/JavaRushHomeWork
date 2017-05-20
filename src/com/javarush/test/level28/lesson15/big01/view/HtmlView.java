package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by lollipop on 05.07.2016.
 */
public class HtmlView implements View
{
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";
    @Override
    public void update(List<Vacancy> vacancies)
    {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }
    }
    private String getUpdatedFileContent(List<Vacancy> vacancies) throws IOException{

            Document document = getDocument();
            Element template = document.getElementsByClass("template").first();
            Element clone = template.clone();
            clone.removeClass("template").removeAttr("style");
            document.getElementsByAttributeValue("class", "vacancy").remove();
            for (Vacancy vacancy : vacancies)
            {
                Element vac = clone.clone();
                vac.getElementsByAttributeValue("class", "city").get(0).text(vacancy.getCity());
                vac.getElementsByAttributeValue("class", "companyName").get(0).text(vacancy.getCompanyName());
                vac.getElementsByAttributeValue("class", "salary").get(0).text(vacancy.getSalary());
                Element link = vac.getElementsByTag("a").get(0);
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                template.before(vac.outerHtml());
            }
            return document.html();
    }
    private void updateFile(String s) {
        try
        {
            FileWriter writer = new FileWriter(filePath);
            writer.write(s);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    protected Document getDocument() throws IOException{
       return Jsoup.parse(new File(filePath),"UTF-8");

    }
    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }
}
