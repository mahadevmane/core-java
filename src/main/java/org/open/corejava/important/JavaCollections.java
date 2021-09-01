package org.open.corejava.important;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class JavaCollections {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new TreeMap<String, Integer>();
        List<Pair> list = new ArrayList<Pair>();
        Pair p;

        String[] line;
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");

            map.put(line[0], Integer.parseInt(line[1]));

            p = new Pair(line[0], line[1]);
            list.add(p);
        }

        Map<String, Integer> sortedMap = sortByValues(map);

        Set<Entry<String, Integer>> set = sortedMap.entrySet();
        Iterator<Entry<String, Integer>> itr = set.iterator();

        while (itr.hasNext()) {
            Entry<String, Integer> entry = itr.next();

            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        Collections.sort(list);

        for (Pair pair : list) {
            System.out.println(pair.name + " " + pair.mark);
        }
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator = new Comparator<K>() {
            @Override
            public int compare(K k1, K k2) {
                int a = (Integer) map.get(k1);
                int b = (Integer) map.get(k2);

                return a > b ? -1 : 1;
            }
        };

        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);

        return sortedByValues;
    }
}

class Pair implements Comparable<Pair> {
    String name;
    int mark;

    public Pair(String s1, String s2) {
        name = s1;
        mark = Integer.parseInt(s2);
    }

    @Override
    public int compareTo(Pair obj) {
        if (this.mark == obj.mark) {
            return this.name.compareTo(obj.name);
        }

        return obj.mark - this.mark;
    }
}