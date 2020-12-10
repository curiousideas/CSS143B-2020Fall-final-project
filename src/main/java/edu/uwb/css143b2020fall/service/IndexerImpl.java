package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();

        if (docs.size() == 0) {
            return indexes;
        }

        // iterate through each string in list
        for (int i = 0; i < docs.size(); i++) {
            // Split words by space, then puts in array
            String doc = docs.get(i);
            doc = doc.trim();
            doc = doc.replaceAll("\\s+", " ");
            if (doc.isEmpty()) {
                continue;
                // do nothing id doc is empty
            }
            String[] words = doc.split(" ");

            // add to map
            for (int j = 0; j < words.length; j++) {
                if (!indexes.containsKey(words[j])) {
                    List<List<Integer>> largeList = new ArrayList<>();
                    indexes.put(words[j], largeList);
                    for (int k = 0; k < docs.size(); k++) {
                        List<Integer> smallList = new ArrayList<>();
                        largeList.add(smallList);
                    }
                }
                // get big list, get smaller list, add index in smaller list
                indexes.get(words[j]).get(i).add(j);
             }
        }
        return indexes;
    }
}