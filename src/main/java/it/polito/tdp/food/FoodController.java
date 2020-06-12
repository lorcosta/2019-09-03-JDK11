/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Correlate;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco cammino peso massimo...\n");
    	String passiString=this.txtPassi.getText();
    	Integer passi=null;
    	doCreaGrafo(event);
    	try {
    		passi=Integer.parseInt(passiString);
    	}catch (NumberFormatException e) {
    		e.printStackTrace();
    		this.txtResult.appendText("ATTTENZIONE! Il valore di passi inserito non e' numerico.");
    		throw new NumberFormatException();
    	}
    	String source=this.boxPorzioni.getValue();
    	if(source==null) {
    		this.txtResult.appendText("ATTENZIONE! Nessuno tipo di porzione scelta dal menu.");
    		return;
    	}
    	List<String> cammino=model.cercaCammino(passi,source);
    	Double pesoCammino=model.getPesoCammino();
    	if(pesoCammino==0.0 || cammino.size()==0) {
    		this.txtResult.appendText("Qualcosa e' andato storto durante la ricorsione...non sono riuscito a trovare un cammino!\n");
    		return;
    	}
    	this.txtResult.appendText("TROVATO UN CAMMINO!\n");
    	this.txtResult.appendText("I tipi di porzione incontrati sono:\n");
    	for(String vicino:cammino) {
    		this.txtResult.appendText(vicino+"\n");
    	}
    	this.txtResult.appendText("Il peso totale del cammino e': "+model.getPesoCammino());
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco porzioni correlate...\n");
    	String source=this.boxPorzioni.getValue();
    	if(source==null) {
    		this.txtResult.appendText("ATTENZIONE! Nessuno tipo di porzione scelta dal menu.");
    		return;
    	}
    	List<Correlate> correlate=model.cercaCorrelate(source);
    	if(correlate==null) {
    		this.txtResult.appendText("Nessun tipo di porzione correlato alla porzione scelta.\n");
    	}
    	for(Correlate c:correlate) {
    		this.txtResult.appendText(c.toString()+"\n");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...");
    	String calorieString=this.txtCalorie.getText();
    	Double calorie = null;
    	try {
    		calorie=Double.parseDouble(calorieString);
    	}catch (NumberFormatException e) {
    		e.printStackTrace();
    		this.txtResult.appendText("ATTTENZIONE! Il valore di calorie inserito non e' numerico.");
    		throw new NumberFormatException();
    	}
    	
    	model.creaGrafo(calorie);
    	this.txtResult.appendText("GRAFO CREATO! "+model.getNumArchi()+" ARCHI E "+model.getVertici().size()+" VERTICI.\n");
    	this.boxPorzioni.getItems().addAll(model.getVertici());
    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
