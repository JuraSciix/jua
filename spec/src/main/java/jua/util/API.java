package jua.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@API.Unimplementable
public interface API {
    
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.SOURCE)
    @interface Unimplementable {
    }
}
