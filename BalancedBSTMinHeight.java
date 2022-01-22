package gfg.BalancedBSTMinHeight;

import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.io.*;

class Node
{
    int data;
    Node right, left;
    Node(int item)
    {
        data = item;
        left = right = null;
    }
}

public class BalancedBSTMinHeight
{
    static Node insert(Node node, int data)
    {
        if (node == null)
        {
            return (new Node(data));
        } else
        {

            /* 2. Otherwise, recur down the tree */
            if (data <= node.data)
            {
                node.left = insert(node.left, data);
            } else
            {
                node.right = insert(node.right, data);
            }

            /* return the (unchanged) node pointer */
            return node;
        }

    }

    int height(Node node)
    {
        if (node==null) return 0;
        else
        {
            int lDepth = height(node.left);
            int rDepth = height(node.right);
            if (lDepth > rDepth)
                return(lDepth+1);
            else
                return(rDepth+1);
        }
    }
    static Node buildTree(String str)
    {
        if(str.length()==0 || str.charAt(0)=='N'){
            return null;
        }

        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        // Starting from the second element

        int i = 1;
        while(queue.size()>0 && i < ip.length) {

            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if(!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if(i >= ip.length)
                break;

            currVal = ip[i];

            // If the right child is not null
            if(!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    public static void main (String[] args) throws IOException
    {



        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t=Integer.parseInt(br.readLine());

        while(t > 0){
            String s = br.readLine();
            Node root = buildTree(s);


            GfG gfg = new GfG();

            root =  gfg.buildBalancedTree(root);
            // preOrder(root);
            BalancedBSTMinHeight bst = new BalancedBSTMinHeight();
            System.out.println(bst.height(root));
            t--;
        }
    }

    void preOrder(Node node)
    {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }
}

// } Driver Code Ends


/*class Node
{
    int data;
    Node right, left;
    Node(int item)
    {
        data = item;
        left = right = null;
    }
}*/

class GfG
{
    Node buildBalancedTree(Node root)
    {
        List<Integer> ls = new ArrayList<>();
        inorderTraverse(root,ls);
        //int arr[] =new int[ls.size()];
        System.out.println("List created is");
        ls.forEach(System.out::println);
        int arr[] =ls.stream().mapToInt(i->i).toArray();
        Node balancedRoot = chooseNext(null,arr,0,arr.length-1);
        System.out.println("After completion of balancing");
        inorderTraverse(balancedRoot,new ArrayList<Integer>());
        return balancedRoot;
    }

    private void inorderTraverse(Node root, List<Integer> ls) {
        if(root==null)
            return;
        inorderTraverse(root.left,ls);
        ls.add(root.data);
        System.out.println(root.data);
        inorderTraverse(root.right,ls);
    }

    Node chooseNext(Node root,int[] arr,int beg,int end){
        if(beg<=end) {
            int mid = beg + (end - beg) / 2;
            root=insert(root, arr[mid]);
            if (beg == end)
                return root;
            chooseNext(root, arr, beg, mid - 1);
            chooseNext(root, arr, mid + 1, end);
        }
        return root;
    }

    Node insert(Node root,int key){
        if(root==null)
            return new Node(key);
        else if(key>root.data)
            root.right=insert(root.right,key);
        else
            root.left=insert(root.left,key);
        return root;
    }
}

