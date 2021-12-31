package com.doo.models;

/**
 * Created by Sam Raju on 31/12/2021
 */
public class SortBody {
    private int[] numbers;

    public SortBody() {
    }

    public SortBody(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }
}
