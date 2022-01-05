public class Duke {
    private int id;
    private String name;
    private double height;
    private char category;
    private boolean hasGadgets;

    public Duke(int id, String name, double height, char category, boolean hasGadgets) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.category = category;
        this.hasGadgets = hasGadgets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public char getCategory() {
        return category;
    }

    public void setCategory(char category) {
        this.category = category;
    }

    public boolean isHasGadgets() {
        return hasGadgets;
    }

    public void setHasGadgets(boolean hasGadgets) {
        this.hasGadgets = hasGadgets;
    }
}
