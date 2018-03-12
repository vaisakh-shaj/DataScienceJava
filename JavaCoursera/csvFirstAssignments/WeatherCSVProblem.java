import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

/**
 * Created by white on 11/18/15.
 */


public class WeatherCSVProblem {

    final String filename1 = "C:/Users/administrator.VPRODEMO/Desktop/Java/csvFirstAssignments/nc_weather/2014/weather-2014-06-29.csv";
    final String filename2 = "C:/Users/administrator.VPRODEMO/Desktop/Java/csvFirstAssignments/nc_weather/2014/weather-2014-07-22.csv";                                       
    public void main() {

        FileResource fr = new FileResource(filename1);

        CSVParser parser = fr.getCSVParser();

         //test 1
        //System.out.println(lowestHumidityInFile(parser));
        FileResource fr2 = new FileResource(filename2);
        CSVParser parser2= fr2.getCSVParser();
        
        //System.out.println(lowestHumidityInFile(parser2));
        // test 2
        //fileWithLowestHumidity();
        //fileWithColdestTemperature() ;
        
        
        //test 3
        
        //test4
         final String filename3 = "C:/Users/administrator.VPRODEMO/Desktop/Java/csvFirstAssignments/nc_weather/2013/weather-2013-08-10.csv";
         FileResource fr3 = new FileResource(filename3);

        CSVParser parser3 = fr3.getCSVParser();
        final String filename4 = "C:/Users/administrator.VPRODEMO/Desktop/Java/csvFirstAssignments/nc_weather/2013/weather-2013-09-02.csv";
         FileResource fr4 = new FileResource(filename4);

        //CSVParser parser4= fr4.getCSVParser();
        CSVParser parser4= fr4.getCSVParser();
        System.out.println(averageTemperatureInFile(parser3));
        System.out.println(averageTemperatureWithHighHumidityInFile(parser4,80));
    }

    /**
     * Returns the CSVRecord with the coldest temperature in the file and
     * thus all the information about the coldest temperature
     *
     * @param parser
     */
    public CSVRecord coldestHourInFile(CSVParser parser) {

        CSVRecord resultRecord = null;

        for (CSVRecord record : parser) {

            if (resultRecord == null) {
                resultRecord = record;
            } else {
                double temperatureF = Double.parseDouble(record.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(resultRecord.get("TemperatureF"));
                resultRecord = (temperatureF < coldestTemp) ? record : resultRecord;
            }
         System.out.println((record.get("TemperatureF")));   
        }
        System.out.println((resultRecord.get("TemperatureF")));
        return resultRecord;

    }

    /**
     * should return a string that is the name of the file
     * from selected files that has the coldest temperature
     */
    public String fileWithColdestTemperature() {

        CSVRecord coldestRecord = null;
        DirectoryResource dr = new DirectoryResource();
        String filename = "C:/Users/administrator.VPRODEMO/Desktop/Java/csvFirstAssignments/nc_weather/2014";

        for (File f : dr.selectedFiles()) {

            FileResource fr = new FileResource(f);

            CSVRecord record = coldestHourInFile(fr.getCSVParser());

            double recordTemp = Double.parseDouble(record.get("TemperatureF"));

            if (recordTemp == -9999) {
                continue;
            }

            if (coldestRecord == null) {
                coldestRecord = record;
                filename = f.getAbsolutePath();
            } else {
                double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));

                if (recordTemp < coldestTemp) {
                    coldestRecord = record;
                    filename = f.getAbsolutePath();
                }
            }

        }

         //test 1
        System.out.println("Coldest Temp for 2014 is" + coldestRecord.get("TemperatureF") + "record : " +coldestRecord);
        return filename;
    }

    /**
     * This method returns the CSVRecord that has the lowest humidity
     *
     * @param parser
     */
    public CSVRecord lowestHumidityInFile(CSVParser parser) {

        CSVRecord resultRecord = null;

        for (CSVRecord record : parser) {

            if (record.get("Humidity").equals("N/A")) {
                continue;
            }

            if (resultRecord == null) {
                resultRecord = record;
            } else {

                double temperatureF = Double.parseDouble(record.get("Humidity"));
                double coldestTemp = Double.parseDouble(resultRecord.get("Humidity"));
                resultRecord = (temperatureF < coldestTemp) ? record : resultRecord;

            }

        }
        //System.out.println(resultRecord.get("Humidity"));
        //System.out.println(resultRecord.get("TimeEDT"));
        return resultRecord;
    }
    public String fileWithLowestHumidity() {

        CSVRecord coldestRecord = null;
        DirectoryResource dr = new DirectoryResource();
        String filename = "C:/Users/administrator.VPRODEMO/Desktop/Java/csvFirstAssignments/nc_weather/2014";

        for (File f : dr.selectedFiles()) {

            FileResource fr = new FileResource(f);

            CSVRecord record = lowestHumidityInFile(fr.getCSVParser());

            double recordTemp = Double.parseDouble(record.get("Humidity"));
               //System.out.println( record.get("Humidity"));
            if (recordTemp == -9999) {
                continue;
            }

            if (coldestRecord == null) {
                coldestRecord = record;
                filename = f.getAbsolutePath();
            } else {
                double coldestTemp = Double.parseDouble(coldestRecord.get("Humidity"));

                if (recordTemp < coldestTemp) {
                    coldestRecord = record;
                    filename = f.getAbsolutePath();
                }
            }
              
        }

         //test 1
        System.out.println("Lowest for 2013 is" + coldestRecord.get("Humidity"));
        
        return filename;
    }
    /**
     * @param parser
     * @return
     */
    public double averageTemperatureInFile(CSVParser parser) {

        double averageTemp = 0.0;
        double sum = 0;
        int counter = 0;
        for (CSVRecord record : parser) {


            double recordTemp = Double.parseDouble(record.get("TemperatureF"));

            if (recordTemp == -9999) {
                continue;
            } else {
                sum += Double.parseDouble(record.get("TemperatureF"));
                counter++;
            }


        }

        averageTemp = sum / counter;

        return averageTemp;
    }

    /**
     * This method returns a double that represents the average temperature of only
     * those temperatures when the humidity was greater than or equal to value
     *
     * @param parser
     * @param level
     */
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, double level) {

        double averageTemp;
        double sum = 0;
        int counter = 0;

        for (CSVRecord record : parser) {

            double humidity = Double.parseDouble(record.get("Humidity"));

            if (humidity >= level) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                counter++;
            }

        }

        averageTemp = sum / counter;

        return averageTemp;

    }

}
