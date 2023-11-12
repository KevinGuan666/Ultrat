package com.yihang.ultrat.common.algorithm.ac;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ACTrieNode {
    private Map<Character, ACTrieNode> children = new HashMap<>();

    private ACTrieNode failover = null;

    private boolean isLeaf = false;

    private int depth;

    public ACTrieNode childOf(char c) {
        return children.get(c);
    }

    public boolean hasChild(char c) {
        return children.containsKey(c);
    }

    public void addChildIfAbsent(char c) {
        children.computeIfAbsent(c, (key) -> new ACTrieNode());
    }

    @Override
    public String toString() {
        return "ACTrieNode{" +
                "failover=" + failover +
                ", isLeaf=" + isLeaf +
                ", depth=" + depth +
                '}';
    }
}
