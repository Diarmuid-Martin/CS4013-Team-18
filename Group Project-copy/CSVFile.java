import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

/**
 * Write a description of class CSVFile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CSVFile
{
    public static void main(String [] args)
    {
        generateCsvFile("d:\\test.csv"); 
    }

    private static void generateCsvFile(String sFileName)
    {
        try
        {
            FileWriter writer = new FileWriter(sFileName);

            writer.append("Name");
            writer.append(',');
            writer.append("email");
            writer.append('\n');

            writer.append("sample");
            writer.append(',');
            writer.append("sample@sample.com");
            writer.append('\n');

            writer.append("demo");
            writer.append(',');
            writer.append("demo@demo.com");
            writer.append('\n');

            //generate whatever data you want

            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } 
    }

    // public static void main(String args[]) throws Exception {
    // //Instantiating the CSVWriter class
    // CSVWriter writer = new CSVWriter(new FileWriter("D://output.csv"));
    // //Writing data to a csv file
    // String line1[] = {"id", "name", "salary", "start_date", "dept"};
    // String line2[] = {"1", "Krishna", "2548", "2012-01-01", "IT"};
    // String line3[] = {"2", "Vishnu", "4522", "2013-02-26", "Operations"};
    // String line4[] = {"3", "Raja", "3021", "2016-10-10", "HR"};
    // String line5[] = {"4", "Raghav", "6988", "2012-01-01", "IT"};
    // //Writing data to the csv file
    // writer.writeNext(line1);
    // writer.writeNext(line2);
    // writer.writeNext(line3);
    // writer.writeNext(line4);
    // //Flushing data from writer to file
    // writer.flush();
    // System.out.println("Data entered");
    // }
}
// List<String[]> dataLines = new ArrayList<>();
// dataLines.add(new String[]
// {getName() + "," + getValue() + "," + getAddress() + "," + getPoscode() + "," + getYearCreated() + "," + 
// getLocation() + "," + getPpr()});

    
// public String convertToCSV(String[] data)
// {
// return Stream.of(data).map(this::escapeSpecialCharacters).collect(Collectors.joining(","));   
// }
// }
