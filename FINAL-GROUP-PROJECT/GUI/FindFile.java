import java.io.File;
import java.util.StringTokenizer;
/**
 * This is used to find the file path of a file in an unknown location
 * @author Diarmuid Martin 
 * @version 12/12/2020
 */
public class FindFile
{
    public static String FindPath(String file){
         File pfile = new File(file);
         
         String absPath = pfile.getAbsolutePath();
         StringTokenizer tokenizer = new StringTokenizer(absPath,"\\");
         System.out.println(absPath);
         String path = "";
         while(tokenizer.hasMoreTokens()){            
           path = path +  tokenizer.nextToken() + "//" ;
        }
        return path;
    }
}
