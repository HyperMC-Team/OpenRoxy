package tech.skidonion.obfuscator.annotations.verification;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface LoadAfterLogin {
    public String value();

    public int priority() default 0;
}

