package instance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FIIAurelian
 */
public class Dataset {
	
	private List<Observation> observations = new ArrayList<Observation>();
	
	public Dataset() {}
	
	public void addObservation( Observation observation ) {
		observations.add( observation );
	}
	
	public List<Observation> getObservations() {
		return observations;
	}
	
	public static Dataset loadFromCsv( String path ) {
		//TODO: stub method
		return null;
	}
	
}
