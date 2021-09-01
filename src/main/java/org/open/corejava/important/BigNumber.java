package org.open.corejava.important;

public class BigNumber {
    int[] result;
    int size;
    int capacity;

    public BigNumber() {
        this.result = new int[1000];
        this.result[0] = 1;
        this.size = 1;
        this.capacity = 1000;
    }

    public void PrintResult() {
        for (int i = this.size - 1; i >= 0; i--) {
            System.out.print(this.result[i]);
        }
        System.out.println();
    }

    public void multiply(int num) {
        int carry = 0, temp;
        for (int i = 0; i < this.size; i++) {
            temp = (this.result[i] * num) + carry;

            this.result[i] = temp % 10;
            carry = temp / 10;
        }

        while (carry != 0) {
            if (this.size == this.capacity) {
                int[] tempArr = this.result;

                this.capacity *= 2;
                this.result = new int[this.capacity];

                for (int i = 0; i < this.size; i++) {
                    this.result[i] = tempArr[i];
                }
            }

            this.result[this.size++] = carry % 10;
            carry = carry / 10;
        }
    }

    public int FindTrailingZeros() {
        int count = 0;

        for (int i = 0; i < this.size; i++) {
            if (this.result[i] == 0) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }
}
