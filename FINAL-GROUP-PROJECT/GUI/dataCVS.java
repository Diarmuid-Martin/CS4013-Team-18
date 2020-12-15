import java.io.*;
import java.util.ArrayList;

/**
 * A class that is used to read information from the SystemData.csv file and is returns what ever it is asked for
 * e.g propString with the String parm of path (which shows where the SystemData file is stored) returns a String ArrayList
 * @author Hannah O'Hea
 * @version 5/12/2020
 */

public class dataCVS {

    /**
     * method to read in data from csv into an arraylist type string
     */

    public static ArrayList<String> propString(String path) {
        ArrayList<String> propString =new ArrayList<>();
        String line;
        try {
            BufferedReader br=new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null){

                propString.add(line+"\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return propString;

    }

    /**
     * method to make an array list type property of all properties in file
     */

    public static ArrayList<PropertyOb> allProperties(String path) {
        ArrayList<PropertyOb> p = new ArrayList<>();
        ArrayList<String> s = propString(path);
        s.remove(0);
        for(int i=1;i<s.size();i++){
            String[] v=s.get(i).split(",");
            // String[] v=line.split(",");
            double v01=Double.parseDouble(v[0]);
            int v0=(int)v01;
            double v4 = Double.parseDouble(v[4]);
            int v7 = Integer.parseInt(v[7]);
            double v8=Double.parseDouble(v[8]);
            double v9=Double.parseDouble(v[9]);
            double v10=Double.parseDouble(v[10]);
            double v11=Double.parseDouble(v[11]);
            double v12=Double.parseDouble(v[12]);

            PropertyOb prop = new PropertyOb(v0, v[1], v[2], v[3],v4,v[5], v[6], v7, v8, v9,v10,v11,v12);
            // System.out.println(prop.toString());
            p.add(prop);
        }
        return p;
    }

    /**
     * method to make an array list type PropertyOb that will be returned if the name and year matches the request
     */
    public static ArrayList<PropertyOb> allPropertiesName(String path, String name, int year) {
        ArrayList<PropertyOb> p = new ArrayList<>();
        ArrayList<String> s = propString(path);
        s.remove(0);
        for(int i=1;i<s.size();i++){
            String[] v=s.get(i).split(",");
            // String[] v=line.split(",");
            double v01=Double.parseDouble(v[0]);
            int v0=(int)v01;
            double v4 = Double.parseDouble(v[4]);
            int v7 = Integer.parseInt(v[7]);
            double v8=Double.parseDouble(v[8]);
            double v9=Double.parseDouble(v[9]);
            double v10=Double.parseDouble(v[10]);
            double v11=Double.parseDouble(v[11]);
            double v12=Double.parseDouble(v[12]);

            PropertyOb prop = new PropertyOb(v0, v[1], v[2], v[3],v4,v[5], v[6], v7, v8, v9,v10,v11,v12);
            // System.out.println(prop.toString());
            if(prop.getName().equals(name) && prop.getYear()==year)
                p.add(prop);
        }
        return p;
    }

    /**
     * method to get array properties of selected name
     * method to add property to csv and adds property to array
     */

    public static void addPropCSV(ArrayList<PropertyOb> p, PropertyOb prop, String path) throws IOException {
        String cols="Property,Owner,Address,Eircode,Value,Principal,Location,Year,Current Year Tax,OverDueTax,Total Tax Due,Amount Paid,Balance";
        File f = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(f,false));
        PrintWriter pw = new PrintWriter(bw);
        pw.println(cols);
        for(int i=0;i<p.size();i++){
            pw.println(p.get(i).toStringCsv());
        }
        pw.println(prop.toStringCsv());
        pw.flush();
        bw.close();
        pw.close();
        p.add(prop);
    }
 
    /**
     * method to write an arraylist type property to file
     */
    
    public static void writeCSV(ArrayList<PropertyOb> p, String path) throws IOException {
        String cols="Property,Owner,Address,Eircode,Value,Principal,Location,Year,Current Year Tax,OverDueTax,Total Tax Due,Amount Paid,Balance";
        File f = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(f,false));
        PrintWriter pw = new PrintWriter(bw);
        pw.println(cols);
        for(int i=0;i<p.size();i++){
            pw.println(p.get(i).toStringCsv());
        }
        pw.flush();
        bw.close();
        pw.close();
    }

    //so to add a property to the csv file.
    //1.create an arrayList of type property called eg.prop and let equal allProperties(path).
    //2.Add a property to the array list...prop.add(Property)
    //3.declare the method writeCSV(prop,path).

}
