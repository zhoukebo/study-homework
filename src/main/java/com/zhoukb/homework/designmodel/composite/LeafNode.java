package com.zhoukb.homework.designmodel.composite;

public class LeafNode extends Node{

    public LeafNode(String nodeName) {
        super(nodeName);
    }

    @Override
    public void showNodeName() {
        System.out.println(nodeName);
    }

}
