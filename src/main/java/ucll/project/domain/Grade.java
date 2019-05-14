package ucll.project.domain;

public enum Grade {
    Grade1(1),
    Grade2(2),
    Grade3(3);

    private final int value;
    private Grade(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
