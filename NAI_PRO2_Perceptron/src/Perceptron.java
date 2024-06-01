import java.util.*;

public class Perceptron {
    protected double[] weights;
    private double bias;
    private double learningRate;
    private Map<String, Integer> labelToNumeric;
    private Map<Integer, String> numericToLAbel;

    public Perceptron(int numberOfAttributes, double learningRate) {
        this.learningRate = learningRate;
        this.weights = new double[numberOfAttributes];
        this.bias = new Random().nextDouble();
        for(int i = 0; i < weights.length; i++){
            weights[i] = new Random().nextDouble();
        }
        this.labelToNumeric = new HashMap<>();
        this.numericToLAbel = new HashMap<>();
    }

    public void train(List<DataPoint> trainData, int iterations){
        encodeLabels(trainData);
        for(int iter = 0; iter < iterations; iter++){
            for (DataPoint point : trainData){
                double output = classifyNumeric(point);
                int label = labelToNumeric.get(point.getLabel());
                double error = label - output;
                for(int i = 0; i < weights.length; i++){
                    weights[i] += learningRate * error * point.getAttributes().get(i);
                }
                bias -= learningRate * error;
            }
        }
    }

    private void encodeLabels(List<DataPoint> trainData){
        int numericLabel = 0;
        for(DataPoint point : trainData){
            String label = point.getLabel();
            if(!labelToNumeric.containsKey(label)){
                labelToNumeric.put(label, numericLabel);
                numericToLAbel.put(numericLabel, label);
                numericLabel++;
                if(numericLabel > 1){
                    break;
                }
            }
        }
    }

    public String classify(DataPoint point){
        double output = classifyNumeric(point);
        int numericOutput = (int) Math.round(output);
        return numericToLAbel.getOrDefault(numericOutput, "Unknown");
    }

    private double classifyNumeric(DataPoint point){
        double net = 0.0;
        for(int i = 0; i < weights.length; i++){
            net += weights[i] * point.getAttributes().get(i);
        }
        net -= bias;
        return activationFunction(net);
    }

    private double activationFunction(double net){
        return net >= 0 ? 1 : 0;
    }
}
