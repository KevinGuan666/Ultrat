package com.yihang.ultrat.constant.algorithm.ac;

import java.util.*;
import java.util.stream.Collectors;

public class ACTrie {

    private ACTrieNode root;

    public ACTrie(List<String> words) {
        words = words.stream().distinct().collect(Collectors.toList());
        root = new ACTrieNode();
        for (String word : words) {
            addWord(word);
        }
        initFailOver();
    }

    public void addWord(String word) {
        ACTrieNode node = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            node.addChildIfAbsent(chars[i]);
            node = node.childOf(chars[i]);
            node.setDepth(i + 1);
        }
        node.setLeaf(true);
    }

    private void initFailOver() {
        Queue<ACTrieNode> queue = new LinkedList<>();
        Map<Character, ACTrieNode> children = root.getChildren();
        for (ACTrieNode node : children.values()) {
            node.setFailover(root);
            queue.offer(node);
        }
        while(!queue.isEmpty()) {
            ACTrieNode parentNode = queue.poll();
            for (Map.Entry<Character, ACTrieNode> entry : parentNode.getChildren().entrySet()) {
                ACTrieNode childNode = entry.getValue();
                ACTrieNode failOver = parentNode.getFailover();
                while(failOver != null && !failOver.hasChild(entry.getKey())) {
                    failOver = failOver.getFailover();
                }
                if(failOver == null) {
                    childNode.setFailover(root);
                } else {
                    childNode.setFailover(failOver.childOf(entry.getKey()));
                }
                queue.offer(childNode);
            }
        }
    }

    public List<MatchResult> matches(String txt) {
        List<MatchResult> results = new ArrayList<>();
        ACTrieNode curNode = root;
        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);
            while (!curNode.hasChild(c) && curNode.getFailover() != null) {
                curNode = curNode.getFailover();
            }
            if (curNode.hasChild(c)) {
                curNode = curNode.childOf(c);
                if (curNode.isLeaf()) {
                    results.add(new MatchResult(i - curNode.getDepth() + 1, i + 1));
                    curNode = curNode.getFailover();
                }
            }
        }
        return results;
    }
}
