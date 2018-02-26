import edu.duke.*;
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part1 {
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
    public StorageResource getAllGenes(String DNA)
    {
        StorageResource geneList= new StorageResource();
        while(true){
            int startIndex = DNA.indexOf("ATG");
            String gene = findGene(DNA);
            if(gene.isEmpty()) break;
            geneList.add(gene);
            DNA=DNA.substring(startIndex+gene.length());
            }
        return geneList;
    }
    public void testGetAllGenes(){
	String[] testCases = {
			"GAATGCTATACTCACAGTAGTTAGGGTAA",
			"TCAATGCCATATTGACAATAGGG",
			"ATGTAATTATAG",
			"AATATTGTTAATAGATGAATATA",
			"AGATGAAATAATAGATGGTTATTTAAGCTACACCATGAGGTTAAGGTGA",
			"ATGTGCATGACCATATAAAGGTCCTAA"
			};
	int i = 0;
	for(String dna : testCases){
		System.out.println("Test case "+ i);
		StorageResource genes = getAllGenes(dna);
		for(String gene: genes.data()){
			System.out.println(gene);
		}
		i++;
	}
    }
    public float cgRatio(String DNA){
        int count = 0 ;
        for(int i = 0; i < DNA.length() ; i++) { 
            char d = DNA.charAt(i); 
            if(d=='C' || d=='G') count++;
        }
        return (float)count/DNA.length();
    }
    public int countCTG(String DNA){
        int count=0;
        while(true){
            int startIndex=DNA.indexOf("CTG");
            if(startIndex==-1) break;
            DNA=DNA.substring(startIndex+3);
            count++;
        }
        return count;
    }
    public void processGenes(StorageResource sr){
        int longStrings = 0; //Count strings that are longer than 9
	int greatCGRatio = 0; //count strings that have a cg ratio higher than 0.35
	String longestGene = "";
	for(String data: sr.data()){
		boolean printed = false;
		if(data.length() > 60){
			System.out.println(data);
			longStrings++;
			printed = true;
		}
		if(cgRatio(data) > 0.35){
			if(!printed){
				System.out.println(data);
			}
			greatCGRatio++;
		}
		
		if(data.length() > longestGene.length()){
			longestGene = data;
		}
		
	}
	
	System.out.println("Strings longer than 60: " + longStrings);
	System.out.println("CGRatio higher than 0.35: " + greatCGRatio);
	System.out.println("Length of Longest gene: "+ longestGene.length());
      }

    public void testProcessGenes(){
    	FileResource fr = new FileResource("brca1.fa");
    	String dna = fr.asString().toUpperCase();
    	StorageResource genes = getAllGenes(dna);
    	System.out.println("Size: "+genes.size());
    	processGenes(genes);
    }
}
    
