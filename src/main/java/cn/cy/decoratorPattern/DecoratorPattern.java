package cn.cy.decoratorPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhaohui on 2017/8/10.
 */
//新闻实体类
class News {
    String name;
    String content;

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
//抽象构件类(被装饰类的抽象)
abstract class  ComponentNews {
//    取新闻
    abstract List<News> getListNews();
}
//基于数据库的取新闻
class DBNews extends  ComponentNews {

    @Override
    List<News> getListNews() {
        List<News> newsList = new ArrayList<>();
        System.out.println("从数据库读取新闻。。。。");
        return newsList;
    }
}
//基于XML中取新闻
class XMLNews extends ComponentNews {

    @Override
    List<News> getListNews() {
        List<News> newsList = new ArrayList<>();
        System.out.println("从XML读取新闻。。。。");
        return newsList;
    }
}
//装饰类抽象
abstract  class DecoratorNews extends ComponentNews {
    public ComponentNews componentNews;

    public DecoratorNews(ComponentNews componentNews) {
        this.componentNews = componentNews;
    }
}
//动态添加功能的拓展组件类
class AddAmount extends  DecoratorNews {

    public AddAmount(ComponentNews componentNews) {
        super(componentNews);
    }

    private void addAmount() {
        System.out.println("新闻人气加1");
    }

    @Override
    List<News> getListNews() {
        addAmount();
        return this.componentNews.getListNews();
    }
}

class AddRss extends DecoratorNews {
    public AddRss(ComponentNews componentNews) {
        super(componentNews);
    }
    private void addRss() {
        System.out.println("新闻标题已经加入到RSS中");
    }
    @Override
    List<News> getListNews() {
        addRss();
        return componentNews.getListNews();
    }
}
public class DecoratorPattern {
    public static void main(String[] args) {
        ComponentNews componentNews = new DBNews();
        DecoratorNews decoratorNews = new AddAmount(componentNews);
        decoratorNews.getListNews();

    }
}
