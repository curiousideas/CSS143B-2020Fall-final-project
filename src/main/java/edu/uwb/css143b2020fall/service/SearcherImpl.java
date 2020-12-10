package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

/*
DO NOT CHANGE
 */

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();

        // Split words by space, then puts in array
        keyPhrase = keyPhrase.trim();
        keyPhrase = keyPhrase.replaceAll("\\s+", " ");
        if (keyPhrase.isEmpty()) {
            return result;
        }
        String[] words = keyPhrase.split(" ");

        // get document index where words appear in
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (!index.containsKey(words[i])) {
                return result;
            }
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= 7; j++) {
                if (!index.get(words[i]).get(j).isEmpty()) {
                    list.add(j);
                }
            }
            if (!list.isEmpty()) {
                lists.add(list);
            }
        }
        System.out.println(lists);

        // get common number
        List<Integer> commonIndexes = new ArrayList<>();
        if (lists.size() == 0) {
            return result;
        } else if (lists.size() == 1) {
            commonIndexes = lists.get(0);
            return commonIndexes;
        } else {
            for (int nums : lists.get(1)) {
                if (lists.get(0).contains(nums)) {
                    commonIndexes.add(nums);
                }
            }
            int i = 2;
            while (i < lists.size()) {
                for (int j = 0; j < commonIndexes.size(); j++) {
                    if (!lists.get(i).contains(commonIndexes.get(j))) {
                        commonIndexes.remove(j);
                    }
                }
                i++;
            }
        }

        // gets location index for each word in doc
        Map<Integer, List<List<Integer>>> docs = new HashMap<>();
        for (int i = 0; i < commonIndexes.size(); i++) {
            List<List<Integer>> locationIndex = new ArrayList<>();
            for (int j = 0; j < words.length; j++) {
                locationIndex.add(index.get(words[j]).get(commonIndexes.get(i)));
            }
            docs.put(commonIndexes.get(i), locationIndex);
        }

        for (Map.Entry<Integer, List<List<Integer>>> entry : docs.entrySet()) {
            int k = entry.getKey();
            List<List<Integer>> v = entry.getValue();
            List<Integer> firstWordIndicies = v.get(0);
            for (int i = 0; i < firstWordIndicies.size(); i++) {
                int indexWord = firstWordIndicies.get(i);
                for (int j = 1; j < v.size(); j++) {
                    if (v.get(j).contains(indexWord + 1)) {
                        indexWord += 1;
                        if (j == v.size() - 1 && !result.contains(k)) {
                            result.add(k);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }
}