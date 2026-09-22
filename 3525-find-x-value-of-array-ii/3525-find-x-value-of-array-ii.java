class Solution {

    static class Node {
        int prod;
        int[] pref;

        Node(int k) {
            pref = new int[k];
        }
    }

    int n, k;
    int[] nums;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.nums = nums;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            nums[index] = value;
            update(1, 0, n - 1, index, value);

            // Query [start, n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[q] = res.pref[x];
        }

        return ans;
    }

    // ---------------- BUILD ----------------

    void build(int node, int left, int right) {

        tree[node] = new Node(k);

        if (left == right) {
            int r = nums[left] % k;

            tree[node].prod = r;

            // The only non-empty prefix is this element
            tree[node].pref[r] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ---------------- MERGE ----------------

    Node merge(Node A, Node B) {

        Node C = new Node(k);

        // Product of complete segment
        C.prod = (A.prod * B.prod) % k;

        // Prefixes completely inside A
        for (int r = 0; r < k; r++) {
            C.pref[r] += A.pref[r];
        }

        // Prefixes which contain all of A
        // and then some non-empty prefix of B
        for (int r = 0; r < k; r++) {

            int newRemainder = (A.prod * r) % k;

            C.pref[newRemainder] += B.pref[r];
        }

        return C;
    }

    // ---------------- UPDATE ----------------

    void update(int node, int left, int right,
                int index, int value) {

        if (left == right) {

            int r = value % k;

            tree[node].prod = r;

            // Clear old prefix information
            for (int i = 0; i < k; i++) {
                tree[node].pref[i] = 0;
            }

            tree[node].pref[r] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2],
                           tree[node * 2 + 1]);
    }

    // ---------------- QUERY ----------------

    Node query(int node, int left, int right,
               int ql, int qr) {

        // Completely inside query range
        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        // Query only left part
        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        // Query only right part
        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        // Query both parts
        Node A = query(node * 2, left, mid, ql, qr);
        Node B = query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(A, B);
    }
}