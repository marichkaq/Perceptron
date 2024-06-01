import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {
    public static List<DataPoint> loadData(String filePath) throws IOException{
        List<DataPoint> dataPoints = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null){
            String[] tokens = line.split(",");
            List<Double> attributes = new ArrayList<>();
            for(int i = 0; i < tokens.length - 1; i++){
                attributes.add(Double.parseDouble(tokens[i]));
            }
            String label = tokens[tokens.length - 1];
            dataPoints.add(new DataPoint(attributes, label));
        }
        br.close();
        return dataPoints;
    }
}