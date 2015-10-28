package decisiontree;

import instance.Attribute;
import instance.Dataset;
import instance.Observation;

import java.util.HashMap;

import utils.Pair;

/**
 * Created by virgil on 27.10.2015.
 */
public class ConfusionMatrix {
	
	private HashMap<Pair<String, String>, Integer> matrix;
	private HashMap<String, Integer> columnTotal;
	private HashMap<String, Integer> rowTotal;
	private String columnLabel;
	private String rowLabel;

    public ConfusionMatrix( String rowLabel, String columnLabel ) {
        matrix = new HashMap<Pair<String, String>, Integer>();
        columnTotal = new HashMap<String, Integer>();
        rowTotal = new HashMap<String, Integer>();
        this.rowLabel = rowLabel;
        this.columnLabel = columnLabel;
    }
    
    public ConfusionMatrix( Dataset dataset, String rowLabel, String columnLabel ) {
    	this( rowLabel, columnLabel );
    	this.setDataset( dataset );
    }
    
    public void setDataset( Dataset dataset ) {
    	for( Observation observation: dataset.getObservations() ) {
    		String rowValue = observation.getAttributeByName( rowLabel ).getValue();
    		String columnValue = observation.getAttributeByName( columnLabel ).getValue();
    		addValueToMatrix( rowValue, columnValue );
    		addValueToTotal( rowTotal, rowValue );
    		addValueToTotal( columnTotal, columnValue );
    	}
    }
   
    public Integer getConditionalCount( String row, String column ) {
    	Pair<String, String> key = new Pair<String, String>( row, column );
    	return matrix.getOrDefault( key, 0 );
    }
    
    public Integer getCount( Attribute attribute ) {
    	return getCount( attribute.getName(), attribute.getValue() );
    }
    
    public Integer getCount( String attributeName, String attributeValue ) {
    	if( rowLabel.equals( attributeName ) )
    		return rowTotal.getOrDefault( attributeValue, 0 );
    	if( columnLabel.equals( attributeName ) )
    		return columnTotal.getOrDefault( attributeValue, 0 );
    	return 0;
    }
    
    private void addValueToMatrix( String rowValue, String columnValue ) {
    	Pair<String, String> key = new Pair<String, String>( rowValue, columnValue );
    	Integer value = matrix.getOrDefault( key, 0 );
    	value = value + 1;
    	matrix.put( key, value );
    }
    
    private void addValueToTotal( HashMap<String, Integer> hashMap, String key ) {
    	Integer value = hashMap.getOrDefault( key, 0 );
    	hashMap.put( key, value );
    }

}
