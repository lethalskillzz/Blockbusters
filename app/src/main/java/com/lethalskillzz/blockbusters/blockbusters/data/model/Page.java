package com.lethalskillzz.blockbusters.blockbusters.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ibrahimabdulkadir on 12/04/2017.
 */

public class Page {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}


    class roll {

        private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        static {
            map.put(1, 1);
            map.put(2, 2);
            map.put(3, 3);
            map.put(4, 4);
            map.put(5, 5);
            map.put(6, 6);
        }

        public static int countPossibilities(int n) {
            if (map.containsKey(n))
                return map.get(n);

            int a, b, c, d, e, f;

            if (map.containsKey(n - 1))
                a = map.get(n - 1);
            else {
                a = countPossibilities(n - 1);
                map.put(n - 1, a);
            }

            if (map.containsKey(n - 2))
                b = map.get(n - 2);
            else {
                b = countPossibilities(n - 2);
                map.put(n - 2, b);
            }


            if (map.containsKey(n - 3))
                c = map.get(n - 3);
            else {
                c = countPossibilities(n - 3);
                map.put(n - 3, c);
            }


            if (map.containsKey(n - 4))
                d = map.get(n - 4);
            else {
                d = countPossibilities(n - 4);
                map.put(n - 4, d);
            }

            if (map.containsKey(n - 5))
                e = map.get(n - 5);
            else {
                e = countPossibilities(n - 5);
                map.put(e - 5, b);
            }

            if (map.containsKey(n - 6))
                f = map.get(n - 6);
            else {
                f = countPossibilities(n - 6);
                map.put(n - 6, f);
            }

            return a + b + c + d + e + f;
        }

    }