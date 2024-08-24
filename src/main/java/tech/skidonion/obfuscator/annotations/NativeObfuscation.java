package tech.skidonion.obfuscator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.CLASS)
@Target(value={ElementType.METHOD, ElementType.TYPE})
public @interface NativeObfuscation {
    public boolean obfuscated() default true;

    public boolean manualTryCatch() default false;

    public VirtualMachine virtualize() default VirtualMachine.NONE;

    public String verificationLock() default "";

    @Retention(value=RetentionPolicy.CLASS)
    @Target(value={ElementType.FIELD})
    @Deprecated
    public static @interface InlineStaticFieldAccess {
    }

    @Retention(value=RetentionPolicy.CLASS)
    @Target(value={ElementType.METHOD, ElementType.FIELD})
    public static @interface Inline {
    }

    public static enum VirtualMachine {
        NONE,
        MUTATE_ONLY,
        TIGER_WHITE,
        TIGER_RED,
        TIGER_BLACK,
        FISH_WHITE,
        FISH_RED,
        FISH_BLACK,
        PUMA_WHITE,
        PUMA_RED,
        PUMA_BLACK,
        SHARK_WHITE,
        SHARK_RED,
        SHARK_BLACK,
        DOLPHIN_WHITE,
        DOLPHIN_RED,
        DOLPHIN_BLACK,
        EAGLE_WHITE,
        EAGLE_RED,
        EAGLE_BLACK,
        LION_WHITE,
        LION_RED,
        LION_BLACK;

    }
}

