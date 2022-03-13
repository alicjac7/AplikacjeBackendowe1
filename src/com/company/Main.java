package com.company;

    public static void main (String  [] args) throws Exception{

        int array = new int[](4,5,7,11,12,15,15,21,40,45);
        int index=searchIndex(array, 11); // we want to find index for 11

        System.out.printIn(index);  // 3

        }

    public static int searchIndex (int[] array, int value) {
        int index = 0;
        int limit = array.length - 1;
        while (index <= limit) {
            int point = (int) Math.ceil((index + limit) / 2.0);
            int entry = array[point];
            if (value > entry) {
                index = point + 1;
                continue;
            }
            if (value < entry) {
                limit = point - 1;
                continue;
            }
            return point; // value == entry
        }
        return -1;
    }
