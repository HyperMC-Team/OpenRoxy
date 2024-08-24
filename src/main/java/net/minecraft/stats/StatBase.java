package net.minecraft.stats;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import net.minecraft.event.HoverEvent;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.stats.IStatType;
import net.minecraft.stats.ObjectiveStat;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IJsonSerializable;

public class StatBase {
    public final String statId;
    private final IChatComponent statName;
    public boolean isIndependent;
    private final IStatType type;
    private final IScoreObjectiveCriteria objectiveCriteria;
    private Class<? extends IJsonSerializable> field_150956_d;
    private static NumberFormat numberFormat = NumberFormat.getIntegerInstance(Locale.US);
    public static IStatType simpleStatType = new IStatType(){

        @Override
        public String format(int number) {
            return numberFormat.format(number);
        }
    };
    private static DecimalFormat decimalFormat = new DecimalFormat("########0.00");
    public static IStatType timeStatType = new IStatType(){

        @Override
        public String format(int number) {
            double d0 = (double)number / 20.0;
            double d1 = d0 / 60.0;
            double d2 = d1 / 60.0;
            double d3 = d2 / 24.0;
            double d4 = d3 / 365.0;
            return d4 > 0.5 ? decimalFormat.format(d4) + " y" : (d3 > 0.5 ? decimalFormat.format(d3) + " d" : (d2 > 0.5 ? decimalFormat.format(d2) + " h" : (d1 > 0.5 ? decimalFormat.format(d1) + " m" : d0 + " s")));
        }
    };
    public static IStatType distanceStatType = new IStatType(){

        @Override
        public String format(int number) {
            double d0 = (double)number / 100.0;
            double d1 = d0 / 1000.0;
            return d1 > 0.5 ? decimalFormat.format(d1) + " km" : (d0 > 0.5 ? decimalFormat.format(d0) + " m" : number + " cm");
        }
    };
    public static IStatType field_111202_k = new IStatType(){

        @Override
        public String format(int number) {
            return decimalFormat.format((double)number * 0.1);
        }
    };

    public StatBase(String statIdIn, IChatComponent statNameIn, IStatType typeIn) {
        this.statId = statIdIn;
        this.statName = statNameIn;
        this.type = typeIn;
        this.objectiveCriteria = new ObjectiveStat(this);
        IScoreObjectiveCriteria.INSTANCES.put(this.objectiveCriteria.getName(), this.objectiveCriteria);
    }

    public StatBase(String statIdIn, IChatComponent statNameIn) {
        this(statIdIn, statNameIn, simpleStatType);
    }

    public StatBase initIndependentStat() {
        this.isIndependent = true;
        return this;
    }

    public StatBase registerStat() {
        if (StatList.oneShotStats.containsKey(this.statId)) {
            throw new RuntimeException("Duplicate stat id: \"" + String.valueOf(StatList.oneShotStats.get((Object)this.statId).statName) + "\" and \"" + String.valueOf(this.statName) + "\" at id " + this.statId);
        }
        StatList.allStats.add(this);
        StatList.oneShotStats.put(this.statId, this);
        return this;
    }

    public boolean isAchievement() {
        return false;
    }

    public String format(int p_75968_1_) {
        return this.type.format(p_75968_1_);
    }

    public IChatComponent getStatName() {
        IChatComponent ichatcomponent = this.statName.createCopy();
        ichatcomponent.getChatStyle().setColor(EnumChatFormatting.GRAY);
        ichatcomponent.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ACHIEVEMENT, new ChatComponentText(this.statId)));
        return ichatcomponent;
    }

    public IChatComponent createChatComponent() {
        IChatComponent ichatcomponent = this.getStatName();
        IChatComponent ichatcomponent1 = new ChatComponentText("[").appendSibling(ichatcomponent).appendText("]");
        ichatcomponent1.setChatStyle(ichatcomponent.getChatStyle());
        return ichatcomponent1;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            StatBase statbase = (StatBase)p_equals_1_;
            return this.statId.equals(statbase.statId);
        }
        return false;
    }

    public int hashCode() {
        return this.statId.hashCode();
    }

    public String toString() {
        return "Stat{id=" + this.statId + ", nameId=" + String.valueOf(this.statName) + ", awardLocallyOnly=" + this.isIndependent + ", formatter=" + String.valueOf(this.type) + ", objectiveCriteria=" + String.valueOf(this.objectiveCriteria) + "}";
    }

    public IScoreObjectiveCriteria getCriteria() {
        return this.objectiveCriteria;
    }

    public Class<? extends IJsonSerializable> func_150954_l() {
        return this.field_150956_d;
    }

    public StatBase func_150953_b(Class<? extends IJsonSerializable> p_150953_1_) {
        this.field_150956_d = p_150953_1_;
        return this;
    }
}

