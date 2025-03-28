package com.example.demo.classes;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleTree {

	class TreeNode {
		int data, order;
		TreeNode leftParent, rightParent, leftChild, rightChild;

		public TreeNode(int n, TreeNode leftParent, TreeNode rightParent) {
			// initializing the values
			if (leftParent == null && rightParent == null) {
				this.order = 1;
			} else if (leftParent != null && rightParent == null) {
				this.order = leftParent.order + 1;
			} else if (leftParent == null && rightParent != null) {
				this.order = rightParent.order + 1;
			} else {
				this.order = Math.max(leftParent.order, rightParent.order) + 1;
			}

			this.data = n;
			this.leftParent = leftParent;
			this.rightParent = rightParent;
			this.leftChild = null;
			this.rightChild = null;
		}

		@Override
		public String toString() {
			String str = String.format("{v=%d, order=%d", this.data, this.order);
//			if (this.leftParent != null) {
//				str += String.format(", leftParent=%d", this.leftParent.data);
//			}
//			if (this.rightParent != null) {
//				str += String.format(", rightParent=%d", this.rightParent.data);
//			}
			if (this.leftChild != null) {
				str += String.format(", leftChild=%d", this.leftChild.data);
			}
			if (this.rightChild != null) {
				str += String.format(", rightChild=%d", this.rightChild.data);
			}
			str += "}";
			return str;
		}
	}

	private TreeNode root;
	private int size, order;

	public SimpleTree(int n) {
		this.size = 1;
		this.order = 1;
		this.root = new TreeNode(n, null, null);
	}

	// method to get the order of tree
	public int getOrder() {
		return order;
	}

	public TreeNode getRoot() {
		return root;
	}

	// method to get the size of tree
	public int getSize() {
		return size;
	}

	// public method to insert an element in tree
	public void insert(int val) {
		TreeNode n = search(this.root, this.order);

		TreeNode newNode = new TreeNode(val, n.leftParent, n.rightParent);
		if (n.rightParent != null) {
			n.rightParent.leftChild = newNode;
		}
		if (n.leftParent != null) {
			n.leftParent.rightChild = newNode;
		}
		this.size++;
	}

	// method to check tree is empty or not
	public boolean isEmpty() {
		return size == 0;
	}

	// method to insert an element in tree which is called internally
	private TreeNode search(TreeNode root, int max_depth) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		TreeNode node = null;
		while (!queue.isEmpty()) {
			node = queue.poll();
			if (node.order < max_depth) {
				if (node.leftChild == null) {
					return new TreeNode(-1, null, node);
				} else if (node.rightChild == null) {
					if (node.rightParent != null && node.rightParent.rightChild.leftChild == null) {
						return new TreeNode(-1, node, node.rightParent.rightChild);
					} else {
						return new TreeNode(-1, node, null);
					}
				}
			}

			if (node.leftChild != null && !queue.contains(node.leftChild)) {
				queue.add(node.leftChild);
			}
			if (node.rightChild != null && !queue.contains(node.rightChild)) {
				queue.add(node.rightChild);
			}
		}

		// If the tree is full, new child starts the new order
		this.order++;
		node = this.root;
		while (node.leftChild != null) {
			node = node.leftChild;
		}
		return new TreeNode(-1, null, node);
	}

	// public method to print a tree
	@Override
	public String toString() {
		String str = String.format("{size=%d, order=%d, nodes:\n", this.size, this.order);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(this.root);

		TreeNode node = null;
		while (!queue.isEmpty()) {
			node = queue.poll();
			if (node.leftChild != null && !queue.contains(node.leftChild)) {
				queue.add(node.leftChild);
			}
			if (node.rightChild != null && !queue.contains(node.rightChild)) {
				queue.add(node.rightChild);
			}
			str += String.format("\t%s\n", node);
		}
		System.out.println();
		return str + "}";
	}
}
