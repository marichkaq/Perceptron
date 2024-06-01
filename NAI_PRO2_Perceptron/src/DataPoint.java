import java.util.List;

public class DataPoint {
    private final List<Double> attributes;
    private final String label;

    public DataPoint(List<Double> attributes, String label) {
        this.attributes = attributes;
        this.label = label;
    }

    public List<Double> getAttributes() {
        return attributes;
    }

    public String getLabel() {
        return label;
    }
}