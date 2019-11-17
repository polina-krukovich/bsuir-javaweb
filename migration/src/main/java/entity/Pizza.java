package entity;

public class Pizza extends Entity {
    private String name;
    private int weightS;
    private int weightM;
    private int weightL;
    private int sectionId;

    public Pizza() {
        super();
    }
    public Pizza(int id, String name, int weightS, int weightM, int weightL, int sectionId) {
        super(id);
        this.name = name;
        this.weightS = weightS;
        this.weightM = weightM;
        this.weightL = weightL;
        this.sectionId = sectionId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWeightS() {
        return weightS;
    }
    public void setWeightS(int weightS) {
        this.weightS = weightS;
    }
    public int getWeightM() {
        return weightM;
    }
    public void setWeightM(int weightM) {
        this.weightM = weightM;
    }
    public int getWeightL() {
        return weightL;
    }
    public void setWeightL(int weightL) {
        this.weightL = weightL;
    }
    public int getSectionId() {
        return sectionId;
    }
    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public String toString() {
        return String.format(
                "%s@%h {super:%s, name:'%s', weightS:%d, weightM:%d, weightL:%d, sectionId:%d}",
                getClass().getSimpleName(),
                this,
                super.toString(),
                name,
                weightS,
                weightM,
                weightL,
                sectionId
        );
    }

    @Override
    public int hashCode() {
        final int PRIME_NUMBER = 31;
        final int SHIFT_BITS_COUNT = 16;
        int hashCode = super.hashCode();
        hashCode = PRIME_NUMBER * hashCode + (name == null ? 0 : name.hashCode());
        hashCode = PRIME_NUMBER * hashCode + (weightS ^ weightS >>> SHIFT_BITS_COUNT);
        hashCode = PRIME_NUMBER * hashCode + (weightM ^ weightM >>> SHIFT_BITS_COUNT);
        hashCode = PRIME_NUMBER * hashCode + (weightL ^ weightL >>> SHIFT_BITS_COUNT);
        hashCode = PRIME_NUMBER * hashCode + (sectionId ^ sectionId >>> SHIFT_BITS_COUNT);
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pizza other = (Pizza) o;
        return name.equals(other.name)
               && Integer.compare(weightS, other.weightS) == 0
               && Integer.compare(weightM, other.weightM) == 0
               && Integer.compare(weightL, other.weightL) == 0
               && Integer.compare(sectionId, other.sectionId) == 0;
    }

    public int compareTo(Pizza o) {
        return name.compareTo(o.name);
    }
}
