package jua.vm.runtime;

import jua.util.Preconditions;
import jua.vm.runtime.code.CodeSegment;

import java.net.URL;

public final class JuaFunction {

    public static JuaFunction fromCode(String name,
                                       int minNumArgs,
                                       int maxNumArgs,
                                       CodeSegment code,
                                       URL location) {
        return new JuaFunction(name, minNumArgs, maxNumArgs, code, null, location);
    }

    public static JuaFunction fromNativeHandler(String name,
                                                int minNumArgs,
                                                int maxNumArgs,
                                                JuaNativeHandler nativeHandler,
                                                URL location) {
        return new JuaFunction(name, minNumArgs, maxNumArgs, null, nativeHandler, location);
    }

    private final String name;

    private final int minNumArgs;

    private final int maxNumArgs;

    private final CodeSegment code;

    private final JuaNativeHandler nativeHandler;

    private final URL location;

    private JuaFunction(String name, int minNumArgs, int maxNumArgs, CodeSegment code, JuaNativeHandler nativeHandler, URL location) {
        Preconditions.ensureNotNull(name, "function name");
        Preconditions.ensureTrue(minNumArgs >= 0, "minNumArgs < 0");
        Preconditions.ensureTrue(maxNumArgs >= minNumArgs, "maxNumArgs < minNumArgs");
        Preconditions.ensureTrue(code != null || nativeHandler != null, "neither fish nor fowl");
        Preconditions.ensureNotNull(location, "function location");
        this.name = name;
        this.minNumArgs = minNumArgs;
        this.maxNumArgs = maxNumArgs;
        this.code = code;
        this.nativeHandler = nativeHandler;
        this.location = location;
    }

    public String name() {
        return name;
    }

    public int minNumArgs() {
        return minNumArgs;
    }

    public int maxNumArgs() {
        return maxNumArgs;
    }

    public CodeSegment codeSegment() {
        ensureCode();
        return code;
    }

    private void ensureCode() {
        if (isNative()) {
            throw new IllegalStateException("trying access to code in native function");
        }
    }

    public JuaNativeHandler nativeHandler() {
        ensureNativeHandler();
        return nativeHandler;
    }

    private void ensureNativeHandler() {
        if (!isNative()) {
            throw new IllegalStateException("trying access to native handler in non-native function");
        }
    }

    public URL location() {
        return location;
    }

    public boolean isNative() {
        return nativeHandler != null;
    }
}
