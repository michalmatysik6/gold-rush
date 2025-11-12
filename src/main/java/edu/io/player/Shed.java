package edu.io.player;

import java.util.Stack;

public class Shed {
    private Stack<Tool> tools = new Stack<>();

    public boolean isEmpty() {
        return tools.isEmpty();
    }

    public Tool getTool() {
        if (tools.isEmpty()) {
            return NoTool.getInstance();
        }
        return tools.peek();
    }

    public void add(Tool tool) {
        if (tool == null) {
            throw new IllegalArgumentException("Narzędzie nie może być puste");
        }
        tools.push(tool);
    }

    public void dropTool() {
        if (!tools.isEmpty()) {
            tools.pop();
        }
    }

    public int size() {
        return tools.size();
    }
}