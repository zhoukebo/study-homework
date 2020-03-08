package com.zhoukb.homework.designmodel.composite;

public class Test {

    public static void main(String[] args) {
        CompositeNode rootNode = new CompositeNode("根节点", 1);
        for (int i = 0; i < 4; i++) {
            CompositeNode compositeNode = new CompositeNode("枝节点" + i, 2);
            for (int j = 0; j < 2; j++) {
                compositeNode.addNode(new LeafNode("叶结点" + j));
            }
            rootNode.addNode(compositeNode);
        }
        rootNode.showNodeName();
    }

}
