/**
* Created by nawed2010 on 07/02/17.
*/
public class Node {

    private Node next;
    private int data;

    public Node(){
        next = null;
        data = 0;
    }

    public Node(Node node, int data){
        this.next = node;
        this.data = data;
    }


    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

}
