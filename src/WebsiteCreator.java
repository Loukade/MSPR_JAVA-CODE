import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebsiteCreator extends Thread{
    private String Allequipements;
    private User agent;

    public WebsiteCreator(User agent, String allEquipment) {
        this.agent = agent;
        this.Allequipements = allEquipment;
    }

    public void run() {
        System.out.println(
                "Thread " + Thread.currentThread().getId()
                        + " is running");
        String html = configureAgentPage();
        try {
            FileWriter myWriter = new FileWriter("./websites/" + this.agent.getNom() + ".html");
            myWriter.write(html);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(
                "Thread " + Thread.currentThread().getId()
                        + " is ended");
    }

    private String configureAgentPage() {
        String html = createAgentHtmlPage();
        String linkPicture = "https://raw.githubusercontent.com/Loukade/MSPR-JAVA/6992f3206ce4c3490c92d7aa910fd73d6ca5de1f/Staff/Personel/CarteID/" + agent.getPrenom() + "" + agent.getNom() + ".png";
        String listOfEquipements = this.configureEquipement(this.agent.getEquipements().getEquipements());

        html = html.replace("%agentname%", this.agent.getPrenom() + " " + this.agent.getNom());
        html = html.replace("%agentEquipments%", listOfEquipements);
        html = html.replace("%logoImageLink%", "../assets/img/logoGoSecuri.png");
        html = html.replace("%userAgentPictureLink%", linkPicture);
        return html;
    }

    private HashMap<String,String> getExistingEquipiement(){
        List<String> allEquipements = List.of(new String[]{"talky", "taser", "mousqueton", "lacrymo", "lampe", "cyno", "menottes", "brassard", "gants", "kit"});
        HashMap<String,String> listEquipements = new HashMap<String,String>();
        int compteur = 0;
        for (String equipementComplet: this.Allequipements.split("\n")) {
            List<String> temporaryList = new ArrayList<String>();
            compteur = 0;
            for (String equipementDetaille: equipementComplet.split("\t")) {
                temporaryList.add(equipementDetaille);
                if(compteur == 1){
                    listEquipements.put(temporaryList.get(0), temporaryList.get(1));
                    compteur = 0;
                }
                compteur++;
            }
        }

        return listEquipements;
    }

    private String configureEquipement(List<String> agentEquipements) {
        HashMap<String,String>  allEquipements = getExistingEquipiement();
        String out = "";

        for(Map.Entry<String, String> entry : allEquipements.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (agentEquipements.contains(key)) {
                out += formatEquipementHtml(true, value);
            } else {
                out += formatEquipementHtml(false, value);
            }
        }

        return out;
    }

    private String formatEquipementHtml(boolean isChecked, String equipementName) {
        String ischeck = isChecked ? "checked" : "";
        return "<div class=\"form-check m-2\">\n" +
                "                        <input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"flexCheckDisabled\" disabled " + ischeck + " >\n" +
                "                            <label class=\"form-check-label\" for=\"flexCheckDisabled\">\n " + equipementName + " </label>\n" +
                "                    </div>\n";
    }

    private String createAgentHtmlPage() {
        return "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n" +
                "      integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\" />\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset='utf-8'>\n"+
                "    <title>%agentname%</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"navbar text-left\" style=\"background-color: #379EC1; color: white; justify-content:flex-start\">\n" +
                "        <a class=\"navbar-brand\"><img src=\"%logoImageLink%\" style=\"width : 5em\"></a>\n" +
                "        <a href=\"\" style=\"color:black\">Liste agents</a>\n" +
                "    </div>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"row\">\n" +
                "            <div class=\"col align-self-center\">\n" +
                "                %agentname%\n" +
                "\n" +
                "\n" +
                "            </div>\n" +
                "            <div class=\"col align-self-end m-2\">\n" +
                "                <img src=\"%userAgentPictureLink%\" />\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"row\">\n" +
                "            <div class=\"col-3\">\n" +
                "                <div class=\"card\">\n" +
                "                    <div class=\"card-header\">\n" +
                "                        Liste objets\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                   %agentEquipments%\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

}
