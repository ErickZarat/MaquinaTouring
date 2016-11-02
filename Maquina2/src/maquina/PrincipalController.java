package maquina;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

public class PrincipalController implements Initializable {
    
    @FXML private TextArea textAreaTira;
    
    //Declaracion de constantes
    private final String salto = "S";
    private final String termina = "T";
    private final int cantidadTira = 100;
    private final int numMatriz = 3;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            maquina();
        } catch(Exception e){
            maquina();
        }
    }
    
    @Override public void initialize(URL url, ResourceBundle rb) {}
    
    private void mostrarAlerta(String binario, String decimal) {
        Alert alert = new Alert(AlertType.INFORMATION);
        String titulo = "Resultado";
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);
        alert.setContentText("BINARIO: " + binario.substring(0, 64) + "...\nDECIMAL: " + decimal);
        alert.show();
    }

    private void maquina() {
        //Declaracion de variables
        String strTira = "", strBinario = "", strDecimal = "";
        List<String[][]> listaMatrices = new ArrayList<>();
        String[][] matriz = null;
        
        //Genera la tira aleatorea
        int contador = 0;
        while(contador < cantidadTira){
            strTira += "" + new Random().nextInt(2) + "";
            contador += 1;
        }
        textAreaTira.setText(strTira);
        
        
        char[] matrizChar = strTira.toCharArray();
        contador = 0;
        int segundo = 0;
        for(int primero = 0; primero < numMatriz; primero++ ){
            for(segundo = 0; segundo < numMatriz; segundo++){
                if(contador == cantidadTira){
                    break;
                }
                if(matriz == null){
                    matriz = new String[numMatriz][numMatriz];
                }
                if(matriz[numMatriz-1][numMatriz-1] == null){
                    matriz[primero][segundo]= String.valueOf(matrizChar[contador]);
                }
                
                contador++;
            }
            if (matriz != null){
                if(matriz[numMatriz-1][numMatriz-1] != null){
                    listaMatrices.add(matriz);
                    matriz = null;
                    primero = -1;
                    segundo = -1;
                }
            }
        }
        
        int randomTermina = new Random().nextInt(listaMatrices.size());
        listaMatrices.get(randomTermina)
                    [new Random().nextInt(numMatriz)]
                    [new Random().nextInt(numMatriz)] = termina;
        
        int randomSalto = new Random().nextInt(randomTermina);
        if(randomSalto > 0)
        for(int x = 0; x < randomSalto; x++){
            listaMatrices.get(x)
                    [new Random().nextInt(numMatriz)]
                    [new Random().nextInt(numMatriz)] = salto;
        }
        
        
        
        //Genera el binario a traves de las matrices ordenadas
        boolean booltermina = false;
        for (Iterator<String[][]> iter = listaMatrices.iterator(); iter.hasNext(); ) {
            matriz = iter.next();
            if(booltermina == true) break;
            for(contador = 0; contador < numMatriz; contador++ ){
                for(segundo = 0; segundo < numMatriz; segundo++){
                    String s = matriz[contador][segundo];
                    if (s == null || s.isEmpty()){
                        s = "0";
                    }
                    else if(s.equals(termina) || booltermina != false){
                        booltermina = true;
                        break;
                    } else if(!s.equals(salto)){
                        strBinario += s;
                    } else if (s.equals(salto)){
                        if (contador < 2) contador++;
                        else break;
                        segundo = -1;
                    }
                }
                
            }
        }
        
        strDecimal = "" + new BigInteger(strBinario, 2) + "";
        System.out.println(strDecimal);
        mostrarAlerta(strBinario, strDecimal);
        
    }
    
}
