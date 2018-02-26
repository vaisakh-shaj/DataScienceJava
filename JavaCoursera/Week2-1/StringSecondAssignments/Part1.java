
/**
 * Write a description of Part1 here.
 * 
 * @author vaisakhs 
 * @version Feb 2018
 */
public class Part1 {
    public int findStopCodon(String DNA,int startIndex,String stopCodon)
    {
        int index = DNA.indexOf(stopCodon, startIndex);
	if(index == -1 || (index - startIndex)%3 != 0) return -1;
	return index;
     }
    public void testFindStopCodon(){
	String[] testCases = {
			"GAATGCTATAATCACA",
			"TCAATGCTATATTTACA",
			"ATGTAATTA"
			};
	System.out.println("Testing TAA");
	for(String dna : testCases){
		int start = dna.indexOf("ATG");
		System.out.println("TAA index: "+ findStopCodon(dna, start, "TAA"));
	}
	System.out.println("Testing TTA");
	for(String dna : testCases){
		int start = dna.indexOf("ATG");
		System.out.println("TTA index: "+ findStopCodon(dna, start, "TTA"));
	}
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
    public void testFindGenes(){
	String[] testCases = {
			"GAATGCTATACTCACAGTAGTTAGGGTAA",
			"TCAATGCCATATTGACAATAGGG",
			"ATGTAATTATAG",
			"AATATTGTTAATAGATGAATATA",
			"AGATGAAATAATAGATGGTTATTTAAGCTACACCATGAGGTTAAGGATGA",
			"GCTACACCATGAGGTTAAGGTGA"
			};
	for(String dna : testCases){
		System.out.println("Gene: "+ findGene(dna));
	}
    }

    public void printAllGenes(String DNA)
    {
        while(true){
            int startIndex = DNA.indexOf("ATG");
            String gene = findGene(DNA);
            if(gene.isEmpty()) break;
            System.out.println(gene);
            DNA=DNA.substring(startIndex+gene.length());
            }
    }
    public void testPrintAllGenes(){
        String[] testCases = {
    		"GAATGCTATACTCACAGTAGTTAGGGTAAATGTTTTAA",
    		"TCAATGCCATATTGACAATAGGG",
    		"ATGTAATTATAG",
    		"AATATTGTTAATAGATGAATATA",
    		"AGATGAAATAATAGATGGTTATTTAAGCTACACCATGAGGTTAAGGTGA"
    		};
    		int i = 0;
    		for(String dna : testCases){
    		System.out.println("Test case "+ i);
    		printAllGenes(dna);
    		i++;
                }
    }
}
