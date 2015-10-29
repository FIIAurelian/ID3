package decisiontree;

import utils.Pair;
import instance.Attribute;
import instance.Dataset;
import instance.Observation;

/**
 * @author FIIAurelian
 */
public class Tree {
	
	private Node root;
	
	private Tree() {}
	
	public String evaluate( Observation observation ) {
		Node node = root;
		while( node.isTerminal() == false ) {
			for( Pair<Attribute, Node> pair: node.getChilds() ) {
				Attribute attribute = pair.getFirst();
				Attribute observationAttribute = observation.getAttributeByName( attribute.getName() );
				if( attribute.equals( observationAttribute ) ) {
					node = pair.getSecond();
					break;
				}
			}
		}
		return node.getLabel();
	}
	
	public void showTree() {
		root.showNode("");
	}
	
	public static Tree construct( String path, String labelName ) {
		Tree tree = new Tree();
		Dataset dataset = Dataset.loadFromCsv( path, "," );
		tree.root = Node.createNode( dataset, labelName );
		return tree;
	}

}
