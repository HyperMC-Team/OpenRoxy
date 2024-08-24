package Rename;

public enum Class473 {
    FORWARDS,
    BACKWARDS;


    public Class473 opposite() {
        return this == FORWARDS ? BACKWARDS : FORWARDS;
    }

    public boolean forwards() {
        return this == FORWARDS;
    }

    public boolean backwards() {
        return this == BACKWARDS;
    }
}

