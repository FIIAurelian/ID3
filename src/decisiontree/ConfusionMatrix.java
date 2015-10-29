package decisiontree;

import instance.Attribute;
import instance.Dataset;
import instance.Observation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Pair;

/**
 *                    Predicted
 *                A               B
 *         A
 * Actual --
 *         B
 * Created by virgil on 27.10.2015.
 */
public class ConfusionMatrix {
	
	private HashMap<Pair<String, String>, Integer> matrix;
	private HashMap<String, Integer> columnTotal;
	private HashMap<String, Integer> rowTotal;
    private Integer totalCount;
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

        totalCount = dataset.getObservations().size();
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public List<String> getRowValues() {
        List<String> rowValues = new ArrayList<>();

        for (Pair<String, String> key : matrix.keySet()) {
            if (!rowValues.contains(key.getFirst()))
                rowValues.add(key.getFirst());
        }

        return rowValues;
    }

    public List<String> getColumnValues() {
        List<String> columnValues = new ArrayList<>();

        for (Pair<String, String> key : matrix.keySet()) {
            if (!columnValues.contains(key.getSecond()))
                columnValues.add(key.getSecond());
        }

        return columnValues;
    }

    public Integer getRowTotal(String rowName) {
        return rowTotal.getOrDefault(rowName, 0);
    }

    public Integer getColumnTotal(String columnName) {
        return columnTotal.getOrDefault(columnName, 0);
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
        matrix.put(key, value);
    }
    
    private void addValueToTotal( HashMap<String, Integer> hashMap, String key ) {
    	Integer value = hashMap.getOrDefault( key, 0 );
        value = value + 1;
    	hashMap.put( key, value );
    }
    
    public boolean isUseless() {
    	int count = 0;
    	for( Map.Entry<String, Integer> entry: rowTotal.entrySet() ) {
    		if( entry.getValue() > 0 )
    			count = count + 1;
    	}
    	return ( count == 1 );
    }
    
    public boolean isSameLabel() {
    	int count = 0;
    	for( Map.Entry<String, Integer> entry: columnTotal.entrySet() ) {
    		if( entry.getValue() > 0 )
    			count = count + 1;
    	}
    	return ( count == 1 );
    }
    
    public String majorityLabel() {
    	int count = -1;
    	String label = "";
    	for( Map.Entry<String, Integer> entry: columnTotal.entrySet() ) {
    		if( entry.getValue() > count ) {
    			count = entry.getValue();
    			label = entry.getKey();
    		}
    	}
    	return label;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();

        List<String> rowValues = getRowValues();
        List<String> columnValues = getColumnValues();
        for (int i = 0; i < rowValues.size(); ++i) {
            String row = rowValues.get(i);
            for (int j = 0; j < columnValues.size(); ++j) {
                String column = columnValues.get(j);
                Integer count = getConditionalCount(row, column);
                returnString.append(count + " ");
            }
            returnString.append(", ");
        }

        return "ConfusionMatrix{" +
                "matrix=" + returnString.toString() +
                "columnTotal=" + columnTotal +
                ", rowTotal=" + rowTotal +
                ", totalCount=" + totalCount +
                ", columnLabel='" + columnLabel + '\'' +
                ", rowLabel='" + rowLabel + '\'' +
                '}';
    }
}
