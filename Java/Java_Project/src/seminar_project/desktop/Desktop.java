package seminar_project.desktop;

import seminar_project.parts.CPU;
import seminar_project.parts.GraphicCard;
import seminar_project.parts.RAM;

public class Desktop {

    private String desktopName;
    private CPU cpu;
    private RAM ram;
    private GraphicCard graphicCard;
    private int price;

    public Desktop(String desktopName, CPU cpu, RAM ram, GraphicCard graphicCard, int price) {

        this.desktopName = desktopName;
        this.cpu = cpu;
        this.ram = ram;
        this.graphicCard = graphicCard;
        this.price = price;

    }

    public String getDesktopName() {
        return desktopName;
    }

    public int getPrice() {
        return price;
    }

    public CPU getCpu() {
        return cpu;
    }

    public RAM getRam() {
        return ram;
    }

    public GraphicCard getGraphicCard() {
        return graphicCard;
    }

}
