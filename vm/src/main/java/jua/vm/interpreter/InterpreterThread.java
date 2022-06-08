package jua.vm.interpreter;

import jua.util.API;
import jua.util.Preconditions;
import jua.vm.runtime.JuaFunction;

public final class InterpreterThread {

    @API.Unimplementable
    public interface Messages {

        /**
         * The thread has just been created.
         */
        byte NEW = 0;

        /**
         * The thread is running right now.
         */
        byte RUNNING = 1;

        /**
         * The thread is sending a frame.
         */
        byte SENDING_FRAME = 2;

        /**
         * The thread is popping a frame.
         */
        byte POPPING_FRAME = 3;

        /**
         * An error occurred in the thread.
         */
        byte CRASHED = 4;

        /**
         * The thread has been interrupted.
         */
        byte INTERRUPTED = 5;

        static String mnemonicOf(byte msg) {
            switch (msg) {
                case NEW:
                    return "NEW";
                case RUNNING:
                    return "RUNNING";
                case SENDING_FRAME:
                    return "SENDING";
                case POPPING_FRAME:
                    return "POPPING";
                case CRASHED:
                    return "CRASHED";
                case INTERRUPTED:
                    return "INTERRUPTED";
                default:
                    return "<UNKNOWN>";
            }
        }
    }


    public static InterpreterThread currentThread() {
        throw new Error();
    }

    private Thread nativeThread;
    private Address[] memory;

    private byte msg;
    private JuaFunction callee;
    private int calleeNumArgs;
    private String errorText;
    private InterpreterFrame frame;

    private short offset;

    public short getOffset() {
        return offset;
    }

    public void setOffset(short offset) {
        this.offset = offset;
    }

    public Address[] getMemory() {
        return memory;
    }

    public Thread getNativeThread() {
        return nativeThread;
    }

    public byte getMsg() {
        return msg;
    }

    public void setMsg(byte msg) {
        this.msg = msg;
    }

    public JuaFunction getCallee() {
        return callee;
    }

    public void setCallee(JuaFunction callee) {
        this.callee = callee;
    }

    public int getCalleeNumArgs() {
        return calleeNumArgs;
    }

    public void setCalleeNumArgs(int calleeNumArgs) {
        this.calleeNumArgs = calleeNumArgs;
    }

    public InterpreterFrame getFrame() {
        return frame;
    }

    public void setFrame(InterpreterFrame frame) {
        this.frame = frame;
    }

    public void start() {

    }

    public void interrupt() {

    }

    public void error(String msg) {
        Preconditions.ensureNotNull(msg, "error msg");
        doError(msg);
    }

    public void error(String fmt, Object... args) {
        Preconditions.ensureNotNull(fmt, "error msg format");
        doError(String.format(fmt, args));
    }

    private void doError(String text) {
        setMsg(Messages.CRASHED);
        errorText = text;
    }
}
