package decisiontree;

import instance.Attribute;
import instance.Dataset;
import instance.Observation;

import java.util.ArrayList;
import java.util.List;

import purityfunction.Entropy;
import utils.Pair;

/**
 * @author FIIAurelian
 */
public class Node {
	
	private boolean terminal;
	private String label;
	private List<Pair<Attribute, Node>> children = new ArrayList<Pair<Attribute, Node>>();
	
	public Node() {
		this.terminal = false;
	}
	
	public void setTerminal( boolean terminal ) {
		this.terminal = terminal;
	}
	
	public boolean isTerminal() {
		return terminal;
	}
	
	public void setLabel( String label ) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public void addChildren( Pair<Attribute, Node> pair ) {
		children.add(pair);
	}
	
	public void addChildren( Attribute attribute, Node node ) {
		this.addChildren( new Pair<Attribute, Node>( attribute, node ) );
	}
	
	public List<Pair<Attribute, Node>> getChilds() {
		return children;
	}
	
	public Node getChildrenNodeForAttribute( Attribute attribute ) {
		Node node = null;
		for( Pair<Attribute, Node> children: this.children) {
			if( children.getFirst().equals( attribute ) ) {
				node = children.getSecond();
			}
		}
		return node;
	}
	
	public void showNode(String prefix) {
		if( terminal == true )
			System.out.println(prefix + "Decision: " + label);
		for( Pair<Attribute, Node> child: children ) {
			System.out.println(prefix + child.getFirst().getName() + "=" + child.getFirst().getValue());
			child.getSecond().showNode(prefix + " ");
		}
	}

	public static Node createNode( Dataset dataset, String labelName ) {
		Node node = new Node();
		
		Entropy entropy = new Entropy();
		Double minimumEntropy = Double.MAX_VALUE;
		String attributeName = "";
		boolean singleLabel = false;
		boolean foundAttribute = false;
		
		Observation observation = dataset.getObservations().get( 0 );
		for( Attribute attribute: observation.getAttributes() ) {
			if( labelName.equals( attribute.getName() ) )continue;
			ConfusionMatrix cm = new ConfusionMatrix( dataset, attribute.getName(), labelName );
			if( cm.isUseless() )continue;
			foundAttribute = true;
			Double currentEntropy = entropy.calculate( cm );
			if( minimumEntropy > currentEntropy ) {
				minimumEntropy = currentEntropy;
				attributeName = attribute.getName();
				singleLabel = false | cm.isSameLabel();
			}
		}
		
		if( foundAttribute == false || singleLabel == true ) {
			node.terminal = true;
			node.label = dataset.majorityValueForAttribute( labelName );
		} else {
			List<String> attributeValues = dataset.getDistinctValuesForAttribute( attributeName );
			for( String attributeValue: attributeValues ) {
				Attribute attribute = new Attribute( attributeName, attributeValue );
				Dataset sonDataset = Dataset.splitDatasetByAttribute( dataset, attribute );
				Node son = createNode( sonDataset, labelName );
				node.children.add( new Pair<Attribute, Node>( attribute, son ) );
			}
		}
		
		return node;
	}
}
