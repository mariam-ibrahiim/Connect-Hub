package connecthub.backend;

import java.util.List;

public interface ContentManagement extends ContentFactory{
   //public void addContent(Content content);
    //public void deleteContent(Content content);
    public void saveToDatabase();
    public void reloadDatabase();
  //  public List<Content> search(String authorId);
}
