package com.zhoukb.homework.designmodel.composite;

/**
 * 抽象节点
 *
 * @author zhoukb
 * @date 2020/3/8 18:37
 */
public abstract class Node {

    protected String nodeName;

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }

    abstract void showNodeName();
}
