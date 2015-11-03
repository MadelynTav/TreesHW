package nyc.c4q.madelyntav;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by c4q-madelyntavarez on 10/25/15.
 */
public class ExpressionTree
{
    static String expression =" a b + c d e + * *";

    public static void main(String[] args)
    {

        Scanner input= new Scanner(expression);
        Stack <Node> stack= new Stack<Node>();

        while(input.hasNext()){
            String symbol=input.next();
            Node node= new Node(symbol);
            if(isOperator(symbol))
            {
                node.right = stack.pop();
                node.left = stack.pop();
            }
            stack.push(node);
        }

        if(stack.size()!=1){
            throw new IllegalStateException("Invalid Stack");
        }
        Node expressionTreeRoot=stack.pop();
        System.out.println("PostFix: ");
        printPostFix(expressionTreeRoot);
        System.out.println();
        System.out.println("PREFIX");
        printPreFix(expressionTreeRoot);
        System.out.println();
        System.out.println("INFIX");
        printInFix(expressionTreeRoot);
        System.out.println();
        System.out.println();
        printBreadth(expressionTreeRoot);

        Node BST = formBST();
        System.out.println("Exists? "+find(BST,"7"));
        System.out.println("Exists? "+find(BST,"12"));

        System.out.println(findMin(BST));
        System.out.println(findMax(BST));

        // insertNum(BST,data);
    }

    private static void insertNumBTS(Node node, String data)
    {

        if(asInt(data)<asInt(node.symbol)){
            if(node.left!=null)
            {
                insertNumBTS(node.left, data);
            } else {
            }
        }
        else if (asInt(data)>asInt(node.symbol)){
            insertNumBTS(node.right,data);
        }
    }

    private static int findMax(Node node)
    {
        if(node==null){ throw new IllegalArgumentException("NULL MODE");
        }

        if(node.right==null){
            return asInt(node.symbol);
        }

        return findMax(node.right);
    }

    private static int findMin(Node node)
    {
        if(node==null){ throw new IllegalArgumentException("NULL MODE");}

        if(node.left==null){
            return asInt(node.symbol);
        }

        return findMin(node.left);
    }

    private static boolean find(Node node, String data){
        if(node==null){
            return false;
        }
        int symbol= asInt(node.symbol);

       if(symbol > Integer.parseInt(data)){
           boolean ans=find(node.left,data);
           return ans;
           // return false;
       }
        if(symbol < Integer.parseInt(data)){
            boolean ans=find(node.right,data);
            return ans;
           // return false;
        }
        else {
            return true;
        }
    }

    private static int asInt(String symbol)
    {
        return Integer.parseInt(symbol);
    }

    private static  Node formBST()
    {
        //This is for binary search tree
        Node ten= new Node("10");
        Node five= new Node("5");
        Node twelve= new Node("12");
        Node two= new Node("2");
        Node six= new Node("6");
        Node three= new Node("3");
        Node four= new Node("4");
        three.right=four;
        two.right=three;
        five.left=two;
        five.right=six;
        ten.left=five;
        ten.right=twelve;

        return ten;

    }

    private static void printPostFix(Node node)
    {
        if(node.left!=null){
            printPostFix(node.left);
        }
        if(node.right!=null){
            printPostFix(node.right);
        }

        System.out.print(node.symbol);
    }

    private static void printPreFix(Node node)
    {
        System.out.print(node.symbol);

        if(node.left!=null){
            printPostFix(node.left);
        }
        if(node.right!=null)
        {
            printPostFix(node.right);
        }

    }

    private static void printInFix(Node node)
    {
        if(node.left!=null){
            printPostFix(node.left);
        }

        System.out.print(node.symbol);

        if(node.right!=null)
        {
            printPostFix(node.right);
        }
    }

    private static void printBreadth(Node root){
        ArrayDeque<Node> queue= new ArrayDeque<Node>();

        queue.add(root);

        while(!queue.isEmpty()){
            Node node=queue.remove();
            System.out.print(node.symbol);
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }

        //if you're here then you've printed all the nodes
    }



    private static boolean isOperator(String symbol)
    {

        return "-/*+".contains(symbol);
    }

    static class Node{
        Node left;
        Node right;
        String symbol;

        public Node(String s){
            this.symbol=s;
        }

    }
}
