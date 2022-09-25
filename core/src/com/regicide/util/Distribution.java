package com.regicide.util;

import java.util.ArrayList;
import java.util.Random;

public class Distribution<T> {
    private int numelems;
    private ArrayList<T> elems;
    private ArrayList<Float> probabilities;

    private Distribution(ArrayList<T> elems, ArrayList<Float> probabilities, int numelems) {
        this.numelems = numelems;
        this.elems = elems;
        this.probabilities = probabilities;
    }

    public static <U> Distribution<U> createUniform(ArrayList<U> elems) {
        int numelems = elems.size();
        Float prob = 1.0f / numelems;
        ArrayList<Float> probabilities = new ArrayList<Float>(numelems);
        for (int _ = 0; _ < numelems; _++)
            probabilities.add(prob);

        return new Distribution<U>(elems, probabilities, numelems);
    }

    public static <U> Distribution<U> createFromLikelyhoods(ArrayList<U> elems, ArrayList<Integer> likelyhoods) {
        int numelems = likelyhoods.size();
        if (elems.size() != numelems)
            return null;

        Float total = 0.f;
        for (Integer x : likelyhoods)
            total += x;

        ArrayList<Float> probabilities = new ArrayList<Float>(numelems);
        for (int i = 0; i < numelems; i++)
            probabilities.add(likelyhoods.get(i) / total);

        return new Distribution<U>(elems, probabilities, numelems);
    }

    public T sample(Random rng) {
        if (numelems == 0)
            return null;

        float psample = rng.nextFloat();
        float count = 0.f;
        int ind = 0;
        for (; ind < numelems; ind++) {
            count += probabilities.get(ind);
            if (count >= psample)
                break;
        }

        return elems.get(ind);
    }
}
