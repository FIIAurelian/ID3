import instance.Attribute;
import instance.Dataset;
import instance.Observation;
import purityfunction.Entropy;
import decisiontree.ConfusionMatrix;

/**
 * Created by virgil on 28.10.2015.
 */
public class MainClass {

    /*public static void main(String[] args) {
        ConfusionMatrix confusionMatrix;
        Dataset dataset = new Dataset();
        Observation observation1 = new Observation();
        Observation observation2 = new Observation();
        Observation observation3 = new Observation();
        Observation observation4 = new Observation();
        observation1.addAttribute(new Attribute("A", "0"));
        observation1.addAttribute(new Attribute("B", "0"));
        observation1.addAttribute(new Attribute("Y", "0"));
        observation2.addAttribute(new Attribute("A", "0"));
        observation2.addAttribute(new Attribute("B", "1"));
        observation2.addAttribute(new Attribute("Y", "0"));
        observation3.addAttribute(new Attribute("A", "1"));
        observation3.addAttribute(new Attribute("B", "0"));
        observation3.addAttribute(new Attribute("Y", "1"));
        observation4.addAttribute(new Attribute("A", "1"));
        observation4.addAttribute(new Attribute("B", "1"));
        observation4.addAttribute(new Attribute("Y", "1"));
        dataset.addObservation(observation1);
        dataset.addObservation(observation2);
        dataset.addObservation(observation3);
        dataset.addObservation(observation4);
        confusionMatrix = new ConfusionMatrix(dataset, "A", "Y");
        System.out.println(confusionMatrix);

        Entropy entropy = new Entropy();
        double entropyValue = entropy.calculate(confusionMatrix);
        System.out.println(entropyValue);
    }*/

}
