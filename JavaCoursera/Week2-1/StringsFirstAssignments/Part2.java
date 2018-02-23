package StringsFirstAssignments;


/**
 * Two_Occurences
 * 
 * @author vaisakhs 
 * @version Feb 2018
 */
public class Part2 {
    public boolean twoOccurrences(String stringa,String stringb)
    {
         int count=0;
         int index= stringb.indexOf(stringa);
         if(index==-1) return false;
         while(index!=-1)
         {
             count=count+1;
             index = stringb.indexOf(stringa,index+1);
            }
         System.out.println(count);
         if(count>=2) return true;
         else return false;
    }
    public String lastPart(String stringa,String stringb)
    {
        int index=stringb.indexOf(stringa);
        if(index==-1) return stringb;
        String sub=stringb.substring(index+stringa.length());
        return sub;
    }
    public void testing(){
        String a="an";
        String b="banana";
        boolean ans1=twoOccurrences(a,b);
        String ans2=lastPart(a,b);
        System.out.println("Two Occurance " + ans1);
        System.out.println("The part of the string after " + a + " is " + ans2);
    }
    
        
        
}
