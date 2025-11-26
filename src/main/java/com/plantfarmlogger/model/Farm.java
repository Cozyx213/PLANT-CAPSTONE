package  com.plantfarmlogger.model;

import java.util.ArrayList;

public class Farm {
    private String name;
    private int size;
    private ArrayList<CropBed> cropBeds;

    public Farm(String name, int size) {
        this.name = name;
        this.size = size;
        this.cropBeds = new ArrayList<>();
    }

    public void addCropBed(CropBed cropBed){cropBeds.add(cropBed);
    }
    public int getSize() {
        return cropBeds.size();
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<CropBed> getCropBeds() {
        return cropBeds;
    }

    public void setCropBed(CropBed cropBed) {
        this.cropBeds = cropBeds;
    }
}
