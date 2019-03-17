package seminar_project.stock;

public class GraphicCard extends Stock {
    private String hardware = "";
    private String performance = "";
    private int price;

    public void allSet(String hardware, String performance, int price) {
        setHadrware(hardware);
        setPerformance(performance);
        setPrice(price);
    }

    @Override
    public void setHadrware(String hadrware) {
        this.hardware = hadrware;
    }

    @Override
    public void setPerformance(String performance) {
        this.performance = performance;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String  getHadrware() {
        return hardware;
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
