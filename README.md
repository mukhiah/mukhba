mukhba
======

Problem:
We need to iterate through the tree. We need to call every node and its sub-tree recursively.
Here is an Example:
We have a function getChildernNodes(VNode node). We want to pass every node recursively in this function and want to iterate through the childeren with for loop:

i.e -> we habe 3 nodes:
V1 V2 V3 where V2 and V3 are the left and right children of the tree.
First, we will pass V1 to the function getChildernNodes and in this function we have a for loop, which will iterate through V2 and V3 and call getChildernNodes on V1 and V2.

Question:
How can we get the Sub-Tree of a node. node.getFlow gives the whole Tree.

