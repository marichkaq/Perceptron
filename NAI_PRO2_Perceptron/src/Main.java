import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            List<DataPoint> trainData = CSVLoader.loadData("data/perceptron.data");
            List<DataPoint> testData = CSVLoader.loadData("data/perceptron.test.data");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter a learning rate value: ");
            double learningRate = scanner.nextDouble();

            Perceptron perceptron = new Perceptron(trainData.get(0).getAttributes().size(), learningRate);
            perceptron.train(trainData, 1000);

            int correct = 0;
            for(DataPoint testPoint : testData){
                String predictedLabel = perceptron.classify(testPoint);
                if(predictedLabel.equals(testPoint.getLabel())){
                    correct++;
                }
            }

            int total = testData.size();
            double accuracy = correct /(double) total;
            System.out.println("Correct test examples: " + correct);
            System.out.println("Total test examples: " + total);
            System.out.printf("Accuracy = %.2f = %.2f%%\n", accuracy, accuracy * 100);


            String input;
            while (true){
                System.out.println("Enter attributes for classification separated by commas (or type 'exit' to quit): ");
                input = br.readLine();
                if ("exit".equalsIgnoreCase(input)){
                    break;
                }

                String[] inputAttributes = input.split(",");
                if (inputAttributes.length != perceptron.weights.length) {
                    System.out.println("Expected " + perceptron.weights.length + " attributes, but got " + inputAttributes.length + ".");
                    continue;
                }

                List<Double> attributes = new ArrayList<>();
                for(String attribute : input.split(",")){
                    attributes.add(Double.parseDouble(attribute.trim()));
                }
                DataPoint newPoint = new DataPoint(attributes, null);
                String label = perceptron.classify(newPoint);
                System.out.println("Predicted label: " + label);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}