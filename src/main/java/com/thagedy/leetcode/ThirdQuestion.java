package com.thagedy.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kaijia Wei on 2017/5/17.
 */
public class ThirdQuestion {
   /*
   假设现在有一个有向无环图，每个节点上都带有正数权重。我们希望找到一条最优路径，使得这个路径上经过的节点的权重之和最大。
    输入：n个节点，m个路径，起点
    输出：最优路径的权重值之和
    举例：
            3个节点：
    A 1
    B 2
    C 2
            3条路径：
    A->B
    B->C
    A->C
    起点：
    A
    输出：5  （最优路径是 A->B->C ， 权重之和是 1+2+2=5）
            •	附加问题：我们要求的输入是有向无环图，但是没人知道实际使用的时候会有什么数据输入进来，如何避免输入了带环路的图导致的死循环呢？
            */

    /**
     * 判断当前输入是否有环
     *
     * @return
     */
    public int findMaxPath(List<Node> nodes) {
        Node root = null;
        //将node放入map中，避免获取元素时遍历结合   Node.name --》 Node
        Map<String, Node> mapNodes = new HashMap<String, Node>();
        for (Node node : nodes) {
            mapNodes.put(node.getName(), node);
            if (node.isStart()) {
                root = node;
            }
        }
        if (root == null) {
            //此处应该抛出其实节点不存在异常
            throw new RuntimeException("起始节点不存在");
        }
        //深度遍历辅助数组
        List<Node> depthList = new ArrayList<Node>();
        depthList.add(root);
        MaxPath maxPath = depthFirst(root, depthList, new MaxPath());
        System.out.println(maxPath.getPath());
        return maxPath.getValue();
    }

    private MaxPath depthFirst(Node root, List<Node> nodes, MaxPath max) {
        List<Node> childs = root.getChilds();
        List<Node> nodePath = new ArrayList<Node>();  // 存储遍历过的node
        nodePath.addAll(nodes);
        StringBuffer flag = new StringBuffer(); //作为判断是否有回路的标志
        int count = 0;
        for (Node node : nodePath) {
            flag.append(node.getName());
            flag.append(",");
            count += node.getValue();
        }
        for (Node node : childs) {
            //如果节点没有子孩子，说明此路径执行完毕
            if (node.getChilds() != null) {
                //如果遍历的路径中此节点的名称包含在flag中，说明含有环
                if (flag.toString().contains(node.getName())) {
                    throw new RuntimeException("该有向无环图中含有环");
                }
                //遍历到该节点说明目前没有环，继续深度遍历
                nodePath.add(node);
                depthFirst(node, nodePath, max);
            } else {
                flag.append(node.getName());
                nodePath.add(node);
                //当前遍历路径结束，记录权值
                count += node.getValue();
                if (count > max.getValue()) {
                    max.setNodes(nodePath);
                    max.setValue(count);
                    max.setPath(flag.toString());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Node nodeA = new Node();
        nodeA.setName("A");
        nodeA.setStart(true);
        nodeA.setValue(1);

        Node nodeB = new Node();
        nodeB.setName("B");
        nodeB.setStart(false);
        nodeB.setValue(2);

        Node nodeC = new Node();
        nodeC.setName("C");
        nodeC.setStart(false);
        nodeC.setValue(2);

        List<Node> nodes = new ArrayList<Node>();
        nodes.add(nodeB);
        nodes.add(nodeC);
        nodeA.setChilds(nodes);

        ArrayList<Node> add = new ArrayList<Node>();
        add.add(nodeC);
        nodeB.setChilds(add);

//        ArrayList<Node> add1 = new ArrayList<Node>();
//        add.add(nodeA);
//        nodeC.setChilds(add1);

        List<Node> nodeList = new ArrayList<Node>();
        nodeList.add(nodeA);
        nodeList.add(nodeB);
        nodeList.add(nodeC);
        ThirdQuestion t = new ThirdQuestion();
        int maxPath = t.findMaxPath(nodeList);
        System.out.println(maxPath);

    }

    class MaxPath {
        int value;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        String path;
        List<Node> nodes;

        public List<Node> getNodes() {
            return nodes;
        }

        public void setNodes(List<Node> nodes) {
            this.nodes = nodes;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }


}

class Node {
    private String name;  //节点名称
    private Integer value; //节点权重
    private boolean start; //是否是其实节点
    private List<Node> childs;  //子节点

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public List<Node> getChilds() {
        return childs;
    }

    public void setChilds(List<Node> childs) {
        this.childs = childs;
    }
}


