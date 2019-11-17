package entity;

public class ExtraSection extends Entity {
    private String name;

    public ExtraSection() {
        super();
    }
    public ExtraSection(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "%s@%h {super:%s, name:'%s'}",
                getClass().getSimpleName(),
                this,
                super.toString(),
                name
        );
    }

    @Override
    public int hashCode() {
        final int PRIME_NUMBER = 31;
        int hashCode = super.hashCode();
        hashCode = PRIME_NUMBER * hashCode + (name == null ? 0 : name.hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExtraSection other = (ExtraSection) o;
        return name.equals(other.name);
    }

    public int compareTo(ExtraSection o) {
        return name.compareTo(o.name);
    }
}
