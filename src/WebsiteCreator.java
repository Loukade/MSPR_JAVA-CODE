import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WebsiteCreator {

    public WebsiteCreator() {
    }

    public void createAgentFile(User agent) {
        String html = configureAgentPage(agent);
        try {
            FileWriter myWriter = new FileWriter("/Users/mathystheolade/Dev/EPSI/MSPR/GoSecuri/websites/" + agent.getNom() + ".html");
            myWriter.write(html);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private String configureAgentPage(User agent) {
        String html = createAgentHtmlPage();
        String linkPicture = "https://raw.githubusercontent.com/Loukade/MSPR-JAVA/6992f3206ce4c3490c92d7aa910fd73d6ca5de1f/Staff/Personel/CarteID/" + agent.getPrenom() + "" + agent.getNom() + ".png";
        String listOfEquipements = this.configureEquipement(agent.getEquipements().getEquipements());

        html = html.replace("%agentname%", agent.getPrenom() + " " + agent.getNom());
        html = html.replace("%agentEquipments%", listOfEquipements);
        html = html.replace("%logoImageLink%", "/Users/mathystheolade/Dev/EPSI/MSPR/GoSecuri/assets/img/logoGoSecuri.png");
        html = html.replace("%userAgentPictureLink%", linkPicture);
        return html;
    }

    //TODO: récupérer tout les équipements disponible sur le github
    private List<String> getExistingEquipiement(){
        List<String> allEquipements = List.of(new String[]{"talky", "taser", "mousqueton", "lacrymo", "lampe", "cyno", "menottes", "brassard", "gants", "kit"});
        return allEquipements;
    }

    private String configureEquipement(List<String> agentEquipements) {
        List<String> allEquipements = getExistingEquipiement();
        String out = "";
        for (String current : allEquipements) {
            if (agentEquipements.contains(current)) {
                out += formatEquipementHtml(true, current);
            } else {
                out += formatEquipementHtml(false, current);
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
