import java.io.IOException;
import java.net.URL;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws IOException {
        GitConnect git = new GitConnect();

        HashMap<String,User> arrayListUser = new HashMap<String,User>();
        //String staff = git.ReadFile(new URL("https://raw.githubusercontent.com/Loukade/MSPR-JAVA/main/Staff/aboulet.txt"));
        String staff = git.ReadFile(new URL("https://raw.githubusercontent.com/Loukade/MSPR-JAVA/main/Staff/Personel/staff.txt"));

        for (String prenom : staff.split("\n")){
            URL currentStaffUrl = new URL("https://raw.githubusercontent.com/Loukade/MSPR-JAVA/main/Staff/"+prenom+".txt");
            WebsiteCreator creator = new WebsiteCreator();
            User user = new User();
            Integer i = 0;

            for (String details:  git.ReadFile(currentStaffUrl).split("\n\n")){
                Equipements equipements = new Equipements();
                if(i == 0){
                    Integer k = 0;
                    for (String data: details.split("\n")){
                        if (k == 0){
                            user.setNom(data);
                        }
                        if (k == 1){
                            user.setPrenom(data);
                        }
                        if (k == 2){
                            user.setGrade(data);
                        }
                        if (k == 3){
                            user.setMotdepasse(data);
                        }
                        k++;
                    }
                    i++;
                }else{
                    for (String data : details.split("\n")){
                        equipements.addEquipements(data);
                    }
                    user.setEquipements(equipements);
                    arrayListUser.put(prenom,user);
                    creator.createAgentFile(user);
                }
            }
        }
    }
}
