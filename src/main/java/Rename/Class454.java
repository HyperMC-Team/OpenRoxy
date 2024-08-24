package Rename;

import java.util.HashMap;
import java.util.List;

public class Class454 {
    private final int A;
    private final String sid;
    private final String name;
    private final List<String> desc;
    private final Class459 hoverAnim = new Class474(300, 1.0);
    private Class470 animation;
    private Runnable runnable;

    public Class454(int index, String sid, String name, List<String> desc) {
        this.A = index;
        this.sid = sid;
        this.name = name;
        this.desc = desc;
        this.animation = new Class470();
    }

    public void joinGame(String guiName) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("entry", this.A);
        data.put("sid", this.sid);
        String json = Class478.toJson(data);
        String message = new StringBuilder().insert(0, "GUI$").append(guiName).append("@").append("entry/").append(this.A).toString();
        Class482.INSTANCE.setLastGameElement(this);
        Class482.INSTANCE.sendPacket(new Class429(message, json));
    }

    public int getIndex() {
        return this.A;
    }

    public void setAnimation(Class470 animation) {
        this.animation = animation;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public int getA() {
        return this.A;
    }

    public String getSid() {
        return this.sid;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getDesc() {
        return this.desc;
    }

    public Class459 getHoverAnim() {
        return this.hoverAnim;
    }

    public Class470 getAnimation() {
        return this.animation;
    }

    public Runnable getRunnable() {
        return this.runnable;
    }
}

