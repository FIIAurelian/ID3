package decisiontree;

import instance.Dataset;
import instance.Observation;

/**
 * @author FIIAurelian
 */
public class Tree {
	
	private Node root;
	
	private Tree() {}
	
	public String evaluate( Observation observation ) {
		//TODO: stub method
		return null;
	}
	
	public static Tree construct( String path, String labelName ) {
		Tree tree = new Tree();
		Dataset dataset = Dataset.loadFromCsv( path, "," );
	}

}
