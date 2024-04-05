package com.example.cache.tree_ex;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeNode
{
    private String name;
    private TreeNode parent;
    private List<TreeNode> children;

    public TreeNode(String name, TreeNode parent, List<TreeNode> children)
    {
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    public TreeNode(String name)
    {
        this.name = name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setParent(TreeNode parent)
    {
        this.parent = parent;
    }

    public void setChildren(List<TreeNode> children)
    {
        this.children = children;
    }

    public String getName()
    {
        return name;
    }

    public TreeNode getParent()
    {
        return parent;
    }

    public List<TreeNode> getChildren()
    {
        return children;
    }

    public static TreeNode root;
    static
    {
        root = new TreeNode("root", null, new ArrayList<>());

        TreeNode child1 = new TreeNode("child1", null, new ArrayList<>());
        TreeNode child1_1 = new TreeNode("child1_1", null, new ArrayList<>());
        TreeNode child1_2 = new TreeNode("child1_2", null, new ArrayList<>());

        child1_1.setParent(child1);
        child1_2.setParent(child1);

        child1.getChildren().add(child1_1);
        child1.getChildren().add(child1_2);

        TreeNode child2 = new TreeNode("child2", null, new ArrayList<>());
        TreeNode child2_1 = new TreeNode("child2_1", null, new ArrayList<>());
        TreeNode child2_2 = new TreeNode("child2_2", null, new ArrayList<>());

        child2_1.setParent(child2);
        child2_2.setParent(child2);

        child2.getChildren().add(child2_1);
        child2.getChildren().add(child2_2);

        child1.setParent(root);
        child2.setParent(root);

        root.getChildren().add(child1);
        root.getChildren().add(child2);
    }

    public static class TreeNodeSerialize
    {
        private String name;
        private String parent;
        private List<String> children;

        public TreeNodeSerialize(String name, String parent, List<String> children)
        {
            this.name = name;
            this.parent = parent;
            this.children = children;
        }
    }

    public static void start() {
        Gson gson = new Gson();


        List<TreeNodeSerialize> list = new ArrayList<>();
        fill(root, list);

        String json = gson.toJson(list);
        System.out.println(json);

        List<TreeNodeSerialize> desiralizeList = gson.fromJson(json, new TypeToken<List<TreeNodeSerialize>>(){}.getType());

        Map<String, TreeNode> cache = new HashMap<>();

        desiralizeList.forEach(treeNodeSerialize -> cache.put(treeNodeSerialize.name, new TreeNode(treeNodeSerialize.name)));
        desiralizeList.forEach(treeNodeSerialize -> {
            TreeNode treeNode = cache.get(treeNodeSerialize.name);
            treeNode.setParent(cache.get(treeNodeSerialize.parent));
            treeNode.setChildren(treeNodeSerialize.children.stream().map(cache::get).collect(Collectors.toList()));
        });

        TreeNode root = cache.values().stream().filter(treeNode -> treeNode.parent == null).findFirst().orElseThrow();
    }

    public static void fill(TreeNode parent, List<TreeNodeSerialize> treeNodeSerializes)
    {
        treeNodeSerializes.add(new TreeNodeSerialize(
                parent.name,
                parent.parent == null ? null : parent.parent.name,
                parent.children.stream().map(TreeNode::getName).collect(Collectors.toList()))
        );
        parent.children.forEach(child -> fill(child, treeNodeSerializes));
    }
}
