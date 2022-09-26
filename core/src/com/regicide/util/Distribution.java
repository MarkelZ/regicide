package com.regicide.util;

import java.util.ArrayList;
import java.util.Random;

public class Distribution<T> {
    private int numelems;
    private ArrayList<T> elems;
    private ArrayList<Float> probabilities;

    // Private constructor because there are several checks that need to be done,
    // such as elems and probabilities having the same size, or the sum of
    // probabilities being 1.
    private Distribution(ArrayList<T> elems, ArrayList<Float> probabilities, int numelems) {
        this.numelems = numelems;
        this.elems = elems;
        this.probabilities = probabilities;
    }

    /**
     * Create a uniform distribution given a list of elements.
     * That is, a distribution where all elements are equally likely to be sampled.
     * 
     * @param <U>   Type of the elements in the list/distribution.
     * @param elems List of elements from which to create the distribution.
     * @return A uniform distribution of the given elements.
     */
    public static <U> Distribution<U> createUniform(ArrayList<U> elems) {
        int numelems = elems.size();
        Float prob = 1.0f / numelems;
        ArrayList<Float> probabilities = new ArrayList<Float>(numelems);
        for (int _ = 0; _ < numelems; _++)
            probabilities.add(prob);

        return new Distribution<U>(new ArrayList<U>(elems), probabilities, numelems);
    }

    /**
     * Create a distribution given the elements of the list and their likelyhoods.
     * The probability assigned to an element is equal to its 'likelyhood' value,
     * divided by the total sum of all likelyhoods.
     * 
     * @param <U>         Type of the elements in the list/distribution.
     * @param elems       List of elements from which to create the distribution.
     * @param likelyhoods Integer valued likelyhoods of the elements.
     * @return A distribution that corresponds to the given elements and
     *         likelyhoods.
     */
    public static <U> Distribution<U> createFromLikelyhoods(ArrayList<U> elems, ArrayList<Integer> likelyhoods) {
        // Verify that the sizes of the lists match.
        int numelems = likelyhoods.size();
        if (elems.size() != numelems)
            return null;

        // Calculate the sum of all likelyhoods.
        Float total = 0.f;
        for (Integer x : likelyhoods)
            total += x;

        // Assign corresponding probability to each element.
        ArrayList<Float> probabilities = new ArrayList<Float>(numelems);
        for (int i = 0; i < numelems; i++)
            probabilities.add(likelyhoods.get(i) / total);

        return new Distribution<U>(new ArrayList<U>(elems), probabilities, numelems);
    }

    /**
     * Generate a sample.
     * Method nextFloat() of rng is called exactly once.
     * 
     * @param rng Random number generator used for sampling.
     * @return Sampled element.
     */
    public T sample(Random rng) {
        float psample = rng.nextFloat();

        if (numelems == 0)
            return null;

        float count = 0.f;
        int ind = 0;
        for (; ind < numelems; ind++) {
            count += probabilities.get(ind);
            if (count >= psample)
                break;
        }

        return elems.get(ind);
    }

    /**
     * Get a copy of the list of elements of the distribution.
     * Since this is just a copy, mutating it does not affect the distribution.
     * 
     * @return A copy of the list of elements of the distribution.
     */
    public ArrayList<T> getElements() {
        return new ArrayList<>(elems);
    }

    /**
     * Get a copy of the list of probabilities of the distribution.
     * Since this is just a copy, mutating it does not affect the distribution.
     * 
     * @return A copy of the list of probabilities of the distribution.
     */
    public ArrayList<Float> getProbabilities() {
        return new ArrayList<>(probabilities);
    }

    /**
     * Get the number of elements of the distribution.
     * 
     * @return Number of elements of the distribution.
     */
    public int getSize() {
        return numelems;
    }
}
