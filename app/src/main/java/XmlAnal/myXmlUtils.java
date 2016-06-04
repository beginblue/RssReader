package XmlAnal;



import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A class  to analyse the Xml files.
 * Created by  He Junchao on 16-5-29.
 */
public class myXmlUtils {
    public  static  Channel parserXml(InputStream in)throws Exception{
        //声明集合对象
        Channel channel=null;
        //临时储存的Items队列
        List<Item> items=new ArrayList<>();
        //临时变量
        Item tempItem=null;
        //获取XmlPullParser解析的实例
        XmlPullParser parser= Xml.newPullParser();
        //设置XmlPullParser的参数
        parser.setInput(in,"utf-8");
        //获取文档的事件类型
        int type=parser.getEventType();
        while(type!=XmlPullParser.END_DOCUMENT){
            //具体判断解析到了哪个结点
            switch (type){
                case XmlPullParser.START_TAG:       //解析开始标签
                    //具体判断解析到是哪个开始标签
                    if("channel".equals(parser.getName())){
                        //创建一个集合对象
                        //rssLists=new ArrayList<Channel>();
                        channel= new Channel();
                    }

                    else if ("title".equals(parser.getName())){
                        //获取title的数据
                        String title=parser.nextText();
                        channel.setTitle(title);
                    }else if ("link".equals(parser.getName())){
                        //获取link的数据
                        String link=parser.nextText();
                        channel.setLink(link);
                    }else if ("description".equals(parser.getName())){
                        //获取description的数据
                        String description=parser.nextText();
                        channel.setDescription(description);
                    }else if ("language".equals(parser.getName())){
                        //获取language的数据
                        String language=parser.nextText();
                        channel.setLanguage(language);
                    }else if ("pubDate".equals(parser.getName())){
                        //获取pubDate的数据
                        String pubDate=parser.nextText();
                        channel.setPubDate(pubDate);
                    }else if ("lastBuildDate".equals(parser.getName())){
                        //获取lastBuildDate的数据
                        String lastBuildDate=parser.nextText();
                        channel.setLastBuildDate(lastBuildDate);
                    }else if ("docs".equals(parser.getName())){
                        //获取docs的数据
                        String docs=parser.nextText();
                        channel.setDocs(docs);
                    }else if ("generator".equals(parser.getName())){
                        //获取generator的数据
                        String generator=parser.nextText();
                        channel.setGenerator(generator);
                    }else if ("managingEditor".equals(parser.getName())){
                        //获取managingEditor的数据
                        String managingEditor=parser.nextText();
                        channel.setManagingEditor(managingEditor);
                    }else if ("WebMaster".equals(parser.getName())){
                        //获取WebMaster的数据
                        String WebMaster=parser.nextText();
                        channel.setWebMaster(WebMaster);
                    }else if ("ttl".equals(parser.getName())){
                        //获取ttl的数据
                        String ttl=parser.nextText();
                        channel.setTtl(ttl);
                    }else if("item".equals(parser.getName())){
                        tempItem = new Item();
                        if ("title".equals(parser.getName())){
                            //获取title的数据
                            String title=parser.nextText();
                            tempItem.setTitle(title);
                        }else if ("link".equals(parser.getName())){
                            //获取link的数据
                            String link=parser.nextText();
                            tempItem.setLink(link);
                        }else if ("description".equals(parser.getName())){
                            //获取description的数据
                            String description=parser.nextText();
                            tempItem.setDescription(description);
                        }else if ("language".equals(parser.getName())){
                            //获取language的数据
                            String language=parser.nextText();
                            tempItem.setLanguage(language);
                        }else if ("pubDate".equals(parser.getName())){
                            //获取pubDate的数据
                            String pubDate=parser.nextText();
                            tempItem.setPubDate(pubDate);
                        }else if ("category".equals(parser.getName())){
                            //获取category的数据
                            String category=parser.nextText();
                            tempItem.setCategory(category);
                        }else if ("author".equals(parser.getName())){
                            //获取author的数据
                            String author=parser.nextText();
                            tempItem.setAuthor(author);
                        }else if ("guid".equals(parser.getName())){
                            //获取guid的数据
                            String guid=parser.nextText();
                            tempItem.setGuid(guid);
                        }
                    }

                    break;
                case XmlPullParser.END_TAG:      //解析结束标志
                    //判断要解析的结束标签
                    if("channel".equals(parser.getName())){
                        //读完了所有items ， 把他们添加到channel里面
                        if(channel!=null) channel.setmItems(items);
                    }
                    //一个item结束了 把 它添加到临时储存的items里面
                    if("item".equals(parser.getName())){
                        if(tempItem!=null)   items.add(tempItem);
                        tempItem=null;
                    }
            }
            //不停向下解析
            type=parser.next();
        }
        //把读取晚的channel返回
        return channel;
    }
}
