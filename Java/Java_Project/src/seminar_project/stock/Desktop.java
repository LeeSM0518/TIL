package seminar_project.stock;

public class Desktop {
    private CPU cpu;
    private RAM ram;
    private GraphicCard graphicCard;

    public Desktop(CPU cpu, RAM ram, GraphicCard graphicCard) {
        this.cpu = cpu;
        this.ram = ram;
        this.graphicCard = graphicCard;
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
