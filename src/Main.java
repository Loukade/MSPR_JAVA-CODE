import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        GitConnect git = new GitConnect();

        List<WebsiteCreator> website = new ArrayList<WebsiteCreator>();
        //String staff = git.ReadFile(new URL("https://raw.githubusercontent.com/Loukade/MSPR-JAVA/main/Staff/aboulet.txt"));
        String Allequipements = git.ReadFile(new URL("https://raw.githubusercontent.com/Loukade/MSPR-JAVA/main/Staff/Outil/liste.txt"));
        String staff = git.ReadFile(new URL("https://raw.githubusercontent.com/Loukade/MSPR-JAVA/main/Staff/Personel/staff.txt"));
        String listFinalUsers = "";
        for (String prenom : staff.split("\n")){
            URL currentStaffUrl = new URL("https://raw.githubusercontent.com/Loukade/MSPR-JAVA/main/Staff/"+prenom+".txt");
            User user = new User();
            Integer i = 0;

            for (String details:  git.ReadFile(currentStaffUrl).split("\n\n")){
                Equipements equipements = new Equipements();
                if(i == 0){
                    Integer k = 0;
                    for (String data: details.split("\n")){
                        if (k == 0){
                            user.setNom(data);
                            listFinalUsers += "<a href="+data+".html>"+ data + " &prenom&</a> <br/> \n\t";
                        }
                        if (k == 1){
                            user.setPrenom(data);
                            listFinalUsers = listFinalUsers.replace("&prenom&", data );
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
                    website.add((new WebsiteCreator(user,Allequipements)));
                }
            }
        }

        // Lancer le process de façon synchrone
        for (int j = 0; j < website.size(); j++) {
            WebsiteCreator current = website.get(j);
            current.start();
        }
        WebsiteCreator.configureListAgentPage(listFinalUsers);
    }
}
