
public class Farm {
    private String name;
    private int size;
    private CropBed cropBed;

    public Farm(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void addCropBed(CropBed cropBed){
        this.cropBed = cropBed;
    }
    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public CropBed getCropBed() {
        return cropBed;
    }

    public void setCropBed(CropBed cropBed) {
        this.cropBed = cropBed;
    }
}
