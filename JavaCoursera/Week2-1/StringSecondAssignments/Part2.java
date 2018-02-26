
/**
 * Write a description of Part2 here.
 * 
 * @author vaisakhs 
 * @version 26 Feb
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
	int startIndex = 0;
	int count = 0;
	while(true){
		startIndex = stringb.indexOf(stringa, startIndex);
		if(startIndex == -1) break;
		startIndex += stringa.length();
		count++;
	}
	return count;
     }
	
	
    public void testHowMany(){
    	String[][] testCases = {
    			{"GAA","ATGAACGAATTGAATC"},
    			{"AA","ATAAAA"},
    			{"ATA", "TGATATATATGGA"},
    			{"TTT", "TTTTTTTTTTTTTT"}
    	};
    	for(String[] test : testCases){
    		System.out.println(howMany(test[0], test[1]));
    	}
    }
}
