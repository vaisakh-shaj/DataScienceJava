
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String DNA,int startIndex,String stopCodon)
    {
        int index = DNA.indexOf(stopCodon, startIndex);
	if(index == -1 || (index - startIndex)%3 != 0) return -1;
	return index;
     }
  
    public String findGene(String DNA)
    {
        int startIndex = DNA.indexOf("ATG");
		
	if(startIndex == -1) return "";
	
	int taaIndex = findStopCodon(DNA, startIndex, "TAA");
	int tagIndex = findStopCodon(DNA, startIndex, "TAG");
	int tgaIndex = findStopCodon(DNA, startIndex, "TGA");
	// System.out.println(startIndex+ " " + taaIndex + " " + tagIndex + " " + tgaIndex);
	String minGene=""; int minLen=Integer.MAX_VALUE;
	
        int temp=taaIndex-startIndex;
        if(temp%3==0 && taaIndex>-1 && minLen>temp+3) {
            minGene=DNA.substring(startIndex,taaIndex+3);
            minLen=temp+3;
        }
        
        temp=tagIndex-startIndex;
        if(temp%3==0 && tagIndex>-1 && minLen>temp+3) {
            minGene=DNA.substring(startIndex,tagIndex+3);
            minLen=temp+3;
        }
        temp=tgaIndex-startIndex;
        if(temp%3==0 && tgaIndex>-1 && minLen>temp+3) {
            minGene=DNA.substring(startIndex,tgaIndex+3);
            minLen=temp+3;
        }
	
	return minGene;
        
    }
    public int countGenes(String DNA){
		int startIndex = 0;
		int count = 0;
		while(true){
			String gene = findGene(DNA);
			if(gene.isEmpty()) break;
			count++;
			DNA=DNA.substring(startIndex+gene.length());;
		}
		return count;
    }
	
    public void testCountGenes(){
    	String[] testCases = {
    			"GAATGCTATACTCACAGTAGTTAGGGTAA",
    			"TCAATGCCATATTGACAATAGGG",
    			"ATGTAATTATAG",
    			"AATATTGTTAATAGATGAATATA",
    			"AGATGAAATAATAGATGGTTATTTAAGCTACACCATGAGGTTAAGGTGA",
    			"GCTACACCATGAGGTTAAGGTGA"
    			};
    	for(String dna : testCases){
    		System.out.println(dna);
    		System.out.println("Gene count: " + countGenes(dna));
    	}
    }
}

