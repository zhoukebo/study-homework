package com.zhoukb.homework.designmodel.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeNode extends Node {

    private Integer level;

    private List<Node> nodes = new ArrayList<>();

    public CompositeNode(String nodeName, Integer level) {
        super(nodeName);
        this.level = level;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void deleteNode(Node node) {
        nodes.remove(node);
    }

    @Override
    public void showNodeName() {
        System.out.println("-" + nodeName);
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < level; i++) {
            prefix.append("\t");
        }
        nodes.forEach(node -> {
            System.out.print(prefix);
            node.showNodeName();
        });
    }
}
