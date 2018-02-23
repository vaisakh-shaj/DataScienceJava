package StringsFirstAssignments;


/**
 * Write a description of Part1 here.
 * 
 * @author vaisakhs 
 * @version 21/2/2017
 */
public class Part1 {
    public String findSimpleGene(String DNA)
    {
        DNA=DNA.toLowerCase();
        String start="atg";
        String stop="taa";
        int index1=DNA.indexOf(start);
        int index2=DNA.indexOf(stop);
        if(index1==-1 || index2==-1) return "";
        String substring=DNA.substring(index1,index2+3);
        System.out.println("substring is " + substring);
        if(substring.length()%3==0) return substring;
        else return "";
    }
    public void testSimpleGene(){
        String test1="AAATGCCCTAACTAGATTAAGAAACC";
        String test2="cccatgaa";
        String test3="ccctaa";
        String test4="cccaaaatttgggg";
        String test5="cccatggggttctaaataataataggagagagagagagagttt";
        System.out.println("DNA is " + test1);
        String sub = findSimpleGene(test1);
        System.out.println("Gene is " + sub);
    }
}

    
