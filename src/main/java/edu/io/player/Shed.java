package edu.io.player;

public class Shed {
    private Tool tool = NoTool.getInstance();

    public boolean isEmpty() {
        return tool instanceof NoTool;
    }

    public Tool getTool() {
        return tool;
    }

    public void add(Tool tool) {
        if (tool == null) throw new IllegalArgumentException("Narzędzie nie może być puste");
        this.tool = tool;
    }

    public void dropTool() {
        this.tool = NoTool.getInstance();
    }
}