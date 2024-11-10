// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable {
	ArrayList<Double> pricesList;

	public Task() {
		pricesList = new ArrayList<>();
	}

	public ArrayList<Double> getPricesList() {
		return pricesList;
	}

	public void setPricesList(ArrayList<Double> pricesList) {
		this.pricesList = pricesList;
	}

	public void addPriceToList(Double price) {
		pricesList.add(price);
	} 
}
