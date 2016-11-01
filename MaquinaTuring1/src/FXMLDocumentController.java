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
            if(!token.equals("F"))
                cinta = cinta.substring(0, random) + token +cinta.substring(random+1, cinta.length());
            else cinta = cinta.substring(0, random) + token ;
        }else {
            ponerMarcadores(token);
        }
        //System.out.println(cinta);
    }
    
    @FXML
    private void btnAction() {
        //Crea una cinta aleatorea
        crearNuevaCinta();
        //Muestra la cinta al usuario
        txtCinta.setText(cinta);
        
        //Determina el fin y los saltos de las matrices
        ponerMarcadores("F");
        for (int j = 0; j < new Random().nextInt(11); j++){
            ponerMarcadores("2");
        }
        
        //Asigna los numeros aleatorios a las Matrices
        ponerEnMatrices();
        
        //Obtiene el Binario final y lo convierte a decimal
        obtenerBinario();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    private void obtenerBinario(){
        String bin = "";
        boolean ok = false;
        
        for(int[][] matriz: arreglo){
            ok = false;
            for(int x = 0; x < 4; x ++){
                if(ok == true){
                    continue;
                }
                for(int y = 0; y < 4; y ++){
                    if(matriz[x][y]==2 || ok == true){
                        ok = true;
                        continue;
                    }
                    bin += matriz[x][y];
                }
                
            }
        }
        
        if(bin == ""){
            bin = "0";
        }
        
        txtBinario.setText(bin);
        System.out.println(bin);
        txtDecimal.setText(String.valueOf(Long.parseLong(bin, 2)));
    }

    private void ponerEnMatrices() {
        try{
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
        } catch (Exception e){
            System.err.println("Error al separar por Tokens");
        }
        
    }
    
    private void crearNuevaCinta() {
        arreglo.clear();
        cinta = "";
        Random ran = new Random();
        for (int x = 0; x < 300; x++)
            cinta += "|" + String.valueOf(ran.nextInt(2));
    }   
}
