package pl.nordea.recruitment.SentenceApp.enums;

public enum FileType {
    CSV("CSV"),
    XML("XML");

    private String value;

    FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FileType fromValue(String text) {
        for (FileType b : FileType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}