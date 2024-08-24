package net.optifine.shaders;

import java.util.ArrayDeque;
import java.util.Deque;
import net.optifine.shaders.Program;

public class ProgramStack {
    private Deque<Program> stack = new ArrayDeque<Program>();

    public void push(Program p) {
        this.stack.addLast(p);
        if (this.stack.size() > 100) {
            throw new RuntimeException("Program stack overflow: " + this.stack.size());
        }
    }

    public Program pop() {
        if (this.stack.isEmpty()) {
            throw new RuntimeException("Program stack empty");
        }
        Program program = this.stack.pollLast();
        return program;
    }
}

