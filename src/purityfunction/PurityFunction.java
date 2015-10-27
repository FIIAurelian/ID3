package purityfunction;

import decisiontree.ConfusionMatrix;

/**
 * Created by virgil on 27.10.2015.
 */
public abstract class PurityFunction {

    private ConfusionMatrix confusionMatrix;

    public void setConfusionMatrix(ConfusionMatrix confusionMatrix) {
        this.confusionMatrix = confusionMatrix;
    }

    public ConfusionMatrix getConfusionMatrix() {
        return this.confusionMatrix;
    }

    /**
     * This function calculates the value of the purity function for the given variables
     * It is implemented in the classes that extend this class, as only they know how to calculate each function
     * @return the value of the purity function for the variables
     */
    abstract double calculate(/* Variables given how???*/);

}
