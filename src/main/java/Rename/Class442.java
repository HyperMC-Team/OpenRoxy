package Rename;

import java.util.ArrayList;
import java.util.List;

public class Class442 {
    private String text;
    private final List<Class467> buttons = new ArrayList<Class467>();

    public String getText() {
        return this.text;
    }

    public List<Class467> getButtons() {
        return this.buttons;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Class442)) {
            return false;
        }
        Class442 other = (Class442)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$text = this.getText();
        String other$text = other.getText();
        if (this$text == null ? other$text != null : !this$text.equals(other$text)) {
            return false;
        }
        List<Class467> this$buttons = this.getButtons();
        List<Class467> other$buttons = other.getButtons();
        return !(this$buttons != null ? !((Object)this$buttons).equals(other$buttons) : other$buttons != null);
    }

    protected boolean canEqual(Object other) {
        return other instanceof Class442;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $text = this.getText();
        result = result * 59 + ($text == null ? 43 : $text.hashCode());
        List<Class467> $buttons = this.getButtons();
        result = result * 59 + ($buttons == null ? 43 : ((Object)$buttons).hashCode());
        return result;
    }

    public String toString() {
        return "Class442(text=" + this.getText() + ", buttons=" + String.valueOf(this.getButtons()) + ")";
    }
}

