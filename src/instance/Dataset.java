package instance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	
	public static Dataset loadFromCsv( String path, String delimiter ) {
		Dataset result = new Dataset();
		try {
			BufferedReader br = new BufferedReader( new FileReader( path ) );
			String line = "";
			String[] header = null;
			while( ( line = br.readLine() ) != null ) {
				String[] lineSplitting = line.split( delimiter );
				if( header == null ) { //if it's the first line(i.e header line)
					header = lineSplitting;
					continue;
				}
				
				Observation observation = new Observation();
				for( int i = 0; i < header.length; i++ ) {
					observation.addAttribute( new Attribute( header[i], lineSplitting[i] ) );
				}
				result.addObservation( observation );
			}
			br.close();
		} catch( IOException exception ) {
			System.err.println( "Exception with message: " + exception.getMessage() );
		}
		return result;
	}
	
	public static Dataset splitDatasetByAttribute( Dataset dataset, Attribute attribute ) {
		Dataset result = new Dataset();
		for( Observation observation: dataset.getObservations() )
			if( observation.matchAttribute( attribute ) )
				result.addObservation( observation );
		return result;
	}
	
}
