import edu.duke.*;
import java.io.File;

public class PerimeterRunner {
    public int getNumPoints (Shape s ) {
        int count=0;
        for (Point currPt : s.getPoints()) {
            count = count +1;
        }
        return count;
    }
     public double getAverageLength (Shape s ) {
        double avg=0;
        double count=getNumPoints(s);
        double tPeri=getPerimeter(s);
        avg=tPeri/count;
        return avg;
    }
    public double getLargestSide (Shape s) {
        // Start with totalPerim = 0
        double lSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update lSide by currDist
            if(currDist>lSide)
            {
                lSide=currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return lSide;
    }
    public int getLargestX (Shape s) {
        // Start with totalPerim = 0
        int lX = 0;
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            int currX = currPt.getX();
            // Update lSide by currDist
            if(currX>lX)
            {
                lX=currX;
            }
        }
        // totalPerim is the answer
        return lX;
    }
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int count = getNumPoints(s);
        System.out.println("number of points = " + count);
        double avg = getAverageLength(s);
        System.out.println("average length = " + avg);
        double lSide = getLargestSide(s);
        System.out.println("largest Side = " + lSide);
        int lX = getLargestX(s);
        System.out.println("largest Side = " + lX);
    }
    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double Largest=0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length > Largest){
                Largest=length;
            }
        }
        return Largest;
    }
    public File getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double Largest=0.0;
        File frL = new File("dummy.txt");
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length > Largest){
                Largest=length;
                frL = f;
            }
        }
        return frL;
    }
    public void testPerimeterMultipleFiles () {
        double length = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter = " + length);
        File f = getFileWithLargestPerimeter();
        System.out.println("Largest File Name = " + f.getName());
    }

  

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}

