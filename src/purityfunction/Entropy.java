package purityfunction;

import decisiontree.ConfusionMatrix;
import instance.Attribute;

/**
 * Created by virgil on 27.10.2015.
 */
public class Entropy implements PurityFunction {

    @Override
    public double calculate(ConfusionMatrix confusionMatrix) {
        //TODO: Based on the confusionMatrix and on the attributes, calculate H(Y | A), where Y is the columnLabel in confusionMatrix and A is the rowLabel
        //H(Y|A) = Sum((totalRow_i/total) * H(Y|A=val_i))

        //for each row in the confusion matrix we calculate H(Y|A=val_i) = getConditionalCount(val_i, j)/rowTotal, j in the list of all columns

        double entropy = 0.0;

        for (String row : confusionMatrix.getRowValues()) {
            double rowEntropy = 0.0;
            for (String column : confusionMatrix.getColumnValues()) {
                System.out.println("Entropy.calculate: " + row + " " + column + " " + confusionMatrix.getConditionalCount(row, column) + " / " + confusionMatrix.getRowTotal(row));
                if (confusionMatrix.getConditionalCount(row, column) != 0) {
                    rowEntropy += (confusionMatrix.getConditionalCount(row, column) + 0.0) / (confusionMatrix.getRowTotal(row) + 0.0) * (Math.log((confusionMatrix.getRowTotal(row) + 0.0) / (confusionMatrix.getConditionalCount(row, column) + 0.0)) / Math.log(2));
                }
                else
                    rowEntropy += 0;
            }
            entropy += rowEntropy * (confusionMatrix.getRowTotal(row) + 0.0) / (confusionMatrix.getTotalCount() + 0.0);
        }

        return entropy;
    }
}
