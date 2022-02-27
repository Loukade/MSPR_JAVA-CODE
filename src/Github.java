import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class GitConnect {
   public final String ReadFile(URL url){
       String file = "";
       try {
           URLConnection uc;
           uc = url.openConnection();

           uc.setRequestProperty("X-Requested-With", "Curl");

           BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
           String line = null;
           while ((line = reader.readLine()) != null)
               file = file + line + "\n";
           return file;

       } catch (IOException e) {
           System.out.println("This url cannot be access due to access or this website doesn't exist");
           return "";
       }
   }
}