import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.util.StringTokenizer;
import javafx.scene.control.TextArea;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea txtCinta;
    @FXML
    private Label txtBinario;
    @FXML
    private Label txtDecimal;
    
    ArrayList<int[][]> arreglo = new ArrayList<>();
    String cinta = "";
    
    public void ponerMarcadores(String token){
        int random = new Random().nextInt(cinta.length());
        if (!cinta.substring(random, random+1).equals("|")){
            cinta = cinta.substring(0, random) + token +cinta.substring(random+1, cinta.length());
        }else {
            ponerMarcadores(token);
        }
    }
    
    @FXML
    private void btnAction() {
        arreglo.clear();
        cinta = "";
        Random ran = new Random();
        for (int x = 0; x < 300; x++) 
            cinta += "|" + String.valueOf(ran.nextInt(2));
        
        txtCinta.setText(cinta);
        
        ponerMarcadores("F");
        for (int j = 0; j < ran.nextInt(11); j++){
            ponerMarcadores("2");
        }
        
        StringTokenizer token = new StringTokenizer(cinta, "|");        
        
        String nextToken = "";
        do {
            int[][] val = new int[4][4];
            for(int x = 0; x < 4; x++){
                for(int y = 0; y < 4; y++){
                    nextToken = token.nextToken();
                    
                    if(!nextToken.equals("F")){
                        if (!nextToken.equals(""))
                            val[x][y] = Integer.parseInt(nextToken);
                    }
                }
            }
            arreglo.add(val);
        }
        while(nextToken.equals("F"));
        
        obtenerBinario();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void obtenerBinario() {
       String bin = "";
        for(int [][] mat: arreglo){
            for(int[] x: mat){
                for(int i: x){
                    
                    if(i == 2 ){
                        break;
                    } else {
                        bin += String.valueOf(i);
                    }
                }
            }
        }
        
        txtBinario.setText(bin);
        txtDecimal.setText(String.valueOf(Long.parseLong(bin, 2)));
    }
    
}
