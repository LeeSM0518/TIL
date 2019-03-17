package seminar_project.stock;

public abstract class Stock {

    public abstract void allSet(String hardware, String performance, int price);

    public abstract void setHadrware(String hadrware);

    public abstract void setPerformance(String performance);

    public abstract void setPrice(int price);

    public abstract String getHadrware();

    public abstract String getPerformance();

    public abstract int getPrice();
}