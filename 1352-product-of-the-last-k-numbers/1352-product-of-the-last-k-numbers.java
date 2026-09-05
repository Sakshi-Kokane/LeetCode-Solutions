import java.util.*;

class ProductOfNumbers {

    private List<Integer> prefixProduct;

    public ProductOfNumbers() {
        prefixProduct = new ArrayList<>();
        prefixProduct.add(1);
    }

    public void add(int num) {

        // Reset because any product including this zero is 0
        if (num == 0) {
            prefixProduct.clear();
            prefixProduct.add(1);
        } else {
            int lastProduct = prefixProduct.get(prefixProduct.size() - 1);
            prefixProduct.add(lastProduct * num);
        }
    }

    public int getProduct(int k) {
        int n = prefixProduct.size();

        // A zero exists within the last k numbers
        if (k >= n) {
            return 0;
        }

        return prefixProduct.get(n - 1) / prefixProduct.get(n - 1 - k);
    }
}