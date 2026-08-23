class MinStack {

    Stack<Long> st;
    long mini;

    public MinStack() {
        st = new Stack<>();
    }

    public void push(int value) {

        long val = value;

        if (st.isEmpty()) {
            mini = val;
            st.push(val);
            return;
        }

        if (val > mini) {
            st.push(val);
        } 
        else {
            st.push(2 * val - mini);
            mini = val;
        }
    }

    public void pop() {

        if (st.isEmpty()) {
            return;
        }

        long x = st.pop();

        if (x < mini) {
            mini = 2 * mini - x;
        }
    }

    public int top() {

        if (st.isEmpty()) {
            return -1;
        }

        long x = st.peek();

        if (x < mini) {
            return (int) mini;
        }

        return (int) x;
    }

    public int getMin() {
        return (int) mini;
    }
}