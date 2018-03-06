package StringsFirstAssignments;
import edu.duke.*;
import java.io.File;

/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    
   public void checkUrl(String word)
   {
       URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
       for (String wd: ur.words())
       {
           String wdTemp=wd.toLowerCase();
           int start=wd.indexOf("\"");
           int end=wd.lastIndexOf("\"");
           if(start!=-1 && end!=start) {
               String searchItem=wd.substring(start+1,end);
               String searchItemT=wdTemp.substring(start+1,end);
               if(searchItemT.contains(word)) System.out.println(searchItem);
            }
        }
    }
    public void testing()
    {
        //System.out.println('\u000C');
        checkUrl("youtube.com");
        checkUrl("youtube.com");
    }
}
