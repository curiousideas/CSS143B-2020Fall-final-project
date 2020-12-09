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
            for (int j = 0; j < 7; j++) {
                if (!index.get(words[i]).get(j).isEmpty()) {
                    list.add(j);
                }
            }
            if (!list.isEmpty()) {
                lists.add(list);
            }
        }

        // get common number
        List<Integer> commonIndexes = new ArrayList<>();
        if (lists.size() == 1) {
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
        // seattle hello  CN = 2
        Map<Integer, List<List<Integer>>> docs = new HashMap<>();
        for (int i = 0; i < commonIndexes.size(); i++) {
            List<List<Integer>> locationIndex = new ArrayList<>();
            for (int j = 0; j < words.length; j++) {
                locationIndex.add(index.get(words[j]).get(commonIndexes.get(i)));
            }
            docs.put(i, locationIndex);
        }
        // 2: [[2],[0, 1, 3], [4,5,6]]

        // subtract 1 from all lists except at 0
        for (int i = 0; i < docs.size(); i++) {
            for (int j = 0; j < docs.get(i).size(); j++) {
                for (int k = 0; k < docs.get(i).get(j).size(); k++) {
                    int val = docs.get(i).get(j).get(k);
                    docs.get(i).get(j).set(k, (val-j));

                    if (docs.get(i).get(j).get(k) == docs.get(i).get(j + 1).get(k)) {

                    }
                }
            }
            /*
            for (int j = 0; j < docs.get(i).size() - 1; j++) {
                for (int k = 0; k < docs.get(i).get(j).size(); k++) {
                    if (docs.get(i).get(j).get(k) == docs.get(i).get(j + 1).get(k)) {

                    }
                }
            }

             */
        }

        return result;
    }

    /*public static void main(String[] args) {

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<Integer>(Arrays.asList(-1, 3, 5, 11, 100)));
        lists.add(new ArrayList<Integer>(Arrays.asList(-1, 3, 5, 11)));
        lists.add(new ArrayList<Integer>(Arrays.asList(-1, 3, 5)));
        lists.add(new ArrayList<Integer>(Arrays.asList(-1, 3)));
        lists.add(new ArrayList<Integer>(Arrays.asList(-1, 3)));


        List<Integer> commonIndexes = new ArrayList<>();
        commonIndexes.add(3);
        commonIndexes.add(4);
        commonIndexes.add(2);

        String[] words = {"hello", "hi", "and"};

        List<List<String>> lists = new ArrayList<>();
        lists.add(new ArrayList<String>(Arrays.asList("hello", "hi", "and")));
        lists.add(new ArrayList<String>(Arrays.asList("hello", "man", "hi", "and")));
        lists.add(new ArrayList<String>(Arrays.asList("hello", "hi", "and")));

        Map<String, List<List<Integer>>> index = new HashMap<>();
        index.put("hello", )

        Map<Integer, List<List<Integer>>> docs = new HashMap<>();
        for (int i = 0; i < commonIndexes.size(); i++) {
            List<List<Integer>> locationIndex = new ArrayList<>();
            for (int j = 0; j < words.length; j++) {
                locationIndex.add(index.get(words[j]).get(commonIndexes.get(i)));
            }
            docs.put(commonIndexes.get(i), locationIndex);
        }
    }
    */
}