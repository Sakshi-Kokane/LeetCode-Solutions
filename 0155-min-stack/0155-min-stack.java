class MinStack 
{
    Stack<Integer> st;
    Stack<Integer> minst;
    public MinStack() 
    {
        st = new Stack<>();
        minst = new Stack<>();
    }
    
    public void push(int value) 
    {
        if(minst.isEmpty())
        {
            minst.push(value);
        }
        else if(minst.peek()>=value)
        {
            minst.push(value);
        }
        st.push(value);
    }
    
    public void pop() 
    {
        if(!st.isEmpty())
        {
            if(!minst.isEmpty() && st.peek().equals(minst.peek()))
            {
                minst.pop();
            }
            st.pop();
        }
    }
    
    public int top() 
    {
        if(!st.isEmpty())
        {
            return st.peek();
        }
        return -1;
    }
    
    public int getMin() 
    {
        if(!minst.isEmpty())
        {
            return minst.peek();
        }
        return -1;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */