package jua.vm.runtime;

import jua.vm.interpreter.Address;

public class JuaEnvironment {

    private JuaFunction[] functions;

    private Address[] constants;

    public JuaFunction getFunction(int id) {
        return functions[id];
    }
}
