class PrimeNumber {
    static void checkPrime(int num) {
        int count = 0;

        if (num <= 1) {
            System.out.println(num + " is not a Prime Number");
            return;
        }

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                count++;
                break;
            }
        }

        if (count == 0) {
            System.out.println(num + " is a Prime Number");
        } else {
            System.out.println(num + " is not a Prime Number");
        }
    }

    public static void main(String[] args) {
        checkPrime(19);
        checkPrime(49);
    }
}
