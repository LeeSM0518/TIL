package seminar_project.parts;

public class GraphicCard implements Part {
    private String productName;
    private String performance;
    private int price;

    @Override
    public void setProductName(final String productName) {
        this.productName = productName;
    }

    @Override
    public void setPerformance(final String performance) {
        this.performance = performance;
    }

    @Override
    public void setPrice(final int price) {
        this.price = price;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public String getPerformance() {
        return performance;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
