package instance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author FIIAurelian
 */
public class Dataset {
	
	private List<Observation> observations;
	
	public Dataset() {
		observations = new ArrayList<Observation>();
	}
	
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
	
	public String majorityValueForAttribute( String attributeName ) {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		for( Observation observation: observations ) {
			Attribute attribute = observation.getAttributeByName( attributeName );
			if( attribute != null ) {
				Integer value = hashMap.getOrDefault( attribute.getValue(), 0 );
				value = value + 1;
				hashMap.put( attribute.getValue(), value );
			}
		}
		
		String result = "";
		int count = -1;
		for( Map.Entry<String, Integer> entry: hashMap.entrySet() )
			if( entry.getValue() > count ) {
				count = entry.getValue();
				result = entry.getKey();
			}
		return result;
	}
	
	public List<String> getDistinctValuesForAttribute( String attributeName ) {
		HashSet<String> hashSet = new HashSet<String>();
		List<String> result = new ArrayList<String>();
		for( Observation observation: observations ) {
			Attribute attribute = observation.getAttributeByName( attributeName );
			if( attribute != null ) {
				if( hashSet.contains( attribute.getValue() ) == false ) {
					hashSet.add( attribute.getValue() );
					result.add( attribute.getValue() );
				}
			}
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

	@Override
	public String toString() {
		StringBuffer returnString = new StringBuffer();

		for (Observation observation : observations)
			returnString.append(observation.toString() + " ");

		return "Dataset{" +
				"observations=" + observations +
				'}';
	}
}
