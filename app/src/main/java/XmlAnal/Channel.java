package XmlAnal;

import java.util.Arrays;
import java.util.List;

/**
 * Created by blue on 16-6-4.
 */
public class Channel {
    private String title;
    private String link;
    private String description;
    private String language;
    private String pubDate;
    private String lastBuildDate;
    private String docs;
    private String generator;
    private String managingEditor;
    private String WebMaster;
    private String ttl;

    private List<Item> mItems;


    public void setmItems(List<Item> mItems) {
        this.mItems=mItems;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    public String getLastBuildDate() {
        return lastBuildDate;
    }
    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }
    public String getDocs() {
        return docs;
    }
    public void setDocs(String docs) {
        this.docs = docs;
    }
    public String getGenerator() {
        return generator;
    }
    public void setGenerator(String generator) {
        this.generator = generator;
    }
    public String getManagingEditor() {
        return managingEditor;
    }
    public void setManagingEditor(String managingEditor) {
        this.managingEditor = managingEditor;
    }
    public String getWebMaster() {
        return WebMaster;
    }
    public void setWebMaster(String webMaster) {
        WebMaster = webMaster;
    }
    public String getTtl() {
        return ttl;
    }
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
//    @Override
//    public String toString() {
//        return "Item [title=" + title + ", link=" + link + ", description="
//                + description + ", language=" + language + ", pubDate="
//                + pubDate + ", lastBuildDate=" + lastBuildDate + ", docs="
//                + docs + ", generator=" + generator + ", managingEditor="
//                + managingEditor + ", WebMaster=" + WebMaster + ", ttl="
//                + ttl + ", mItems=" + Arrays.toString(mItems) + "]";
//    }

    public List<Item> getItems(){
        return mItems;
    }


}
