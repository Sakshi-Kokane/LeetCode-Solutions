/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution 
{
    public Node copyRandomList(Node head) 
    {
        if(head == null)
        {
            return null;
        }
        insertNode(head);
        connectRandom(head);
        return getCopyLL(head);
    }
    public void insertNode(Node head)
    {
        Node temp=head;
        while(temp!=null)
        {
            Node copynode = new Node(temp.val);
            copynode.next=temp.next;
            temp.next=copynode;
            temp=temp.next.next;
        }
    }
    public void connectRandom(Node head)
    {
        Node temp=head;
        while(temp!=null)
        {
            Node copynode=temp.next;
            if(temp.random!=null)
            {
                copynode.random = temp.random.next;
            }
            else
            {
                copynode.random=null;
            }
            temp=temp.next.next;
        }
    }
    public Node getCopyLL(Node head)
    {
        Node temp=head;
        Node dummy = new Node(-1);
        Node result=dummy;
        while(temp!=null)
        {
            result.next=temp.next;
            temp.next=temp.next.next;
            temp=temp.next;
            result=result.next;
        }
        return dummy.next;
    }
}