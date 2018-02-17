package home.work.pcconfig.business.models;


public class OrderItem {
    private int id;
    private String motherBoard;
    private int cpuInterType;
    private String gpu;
    private int ramSize;
    private int stogareSize;
    private boolean ssd;
    private boolean gaming;

    public OrderItem(int id,
                     String motherBoard,
                     int cpuInterType,
                     String gpu,
                     int ramSize,
                     int stogareSize,
                     boolean ssd,
                     boolean gaming) {
        this.id = id;
        this.motherBoard = motherBoard;
        this.cpuInterType = cpuInterType;
        this.gpu = gpu;
        this.ramSize = ramSize;
        this.stogareSize = stogareSize;
        this.ssd = ssd;
        this.gaming = gaming;
    }

    public int getId() {
        return id;
    }

    public String getMotherBoard() {
        return motherBoard;
    }

    public int getCpuInterType() {
        return cpuInterType;
    }

    public String getGpu() {
        return gpu;
    }

    public int getRamSize() {
        return ramSize;
    }

    public int getStogareSize() {
        return stogareSize;
    }

    public boolean isSsd() {
        return ssd;
    }

    public boolean isGaming() {
        return gaming;
    }


    @Override
    public String toString() {
        return "OrderItem{" + "id=" + id + ", motherBoard='" + motherBoard + '\'' + ", cpuInterType=" + cpuInterType + ", gpu='" + gpu + '\'' + ", ramSize=" + ramSize + ", stogareSize=" + stogareSize + ", ssd=" + ssd + ", gaming=" + gaming + '}';
    }
}
