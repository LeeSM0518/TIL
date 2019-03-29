package seminar_project.desktop;

import seminar_project.parts.CPU;
import seminar_project.parts.GraphicCard;
import seminar_project.parts.RAM;

public class Desktop {
    private CPU cpu;
    private RAM ram;
    private GraphicCard graphicCard;
    private int price;

    public Desktop(CPU cpu, RAM ram, GraphicCard graphicCard, int price) {
        this.cpu = cpu;
        this.ram = ram;
        this.graphicCard = graphicCard;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public GraphicCard getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(GraphicCard graphicCard) {
        this.graphicCard = graphicCard;
    }
}
