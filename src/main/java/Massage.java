import java.util.Objects;

public class Massage {

    private final String sender;
    private String text;

    public Massage(String sender, String text) {
        this.sender = sender;
        this.text = text == null ? "" : text;
    }

    public Massage(String sender) {
        this.sender = sender;
        this.text = "";
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Massage massage = (Massage) o;
        return Objects.equals(sender, massage.sender) && Objects.equals(text, massage.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, text);
    }
}
