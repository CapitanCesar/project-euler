package com.example.demo.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import com.example.demo.classes.SimpleTree.TreeNode;

public class BreathFirst {
	// A* Node with total cost (f), cumulative cost to the node (g) & heuristic (h)
	class BreathFirstNode implements Comparable<BreathFirstNode> {
		private final TreeNode node;
		public int f; // estimated total cost f = g + h
		public int g; // cumulative cost from the node start
		public int h; // estimated heuristic to target
		public BreathFirstNode parent; // Parent node to reconstruct the path

		public BreathFirstNode(TreeNode node, int g, int h, BreathFirstNode parent) {
			this.node = node;
			this.g = g;
			this.h = h;
			this.f = g + h;
			this.parent = parent;
		}

		@Override
		public int compareTo(BreathFirstNode other) {
//            return Integer.compare(this.f, other.f);
			if (this.f > other.f) {
				return -1;
			} else if (this.f < other.f) {
				return 1;
			}
			return 0;
		}
	}

	private TreeNode start;
	private String goal;

	public BreathFirst(TreeNode start, String goal) {
		this.start = start;
		this.goal = goal;
	}

	// Compute the cost to get to a node from other
	private int f(TreeNode node, TreeNode nextNode) {
		// The distance to a node only depends on the nextNode
		return nextNode.data;
	}

	// Heuristic function (estimated cost from the current node to the goal)
	private int h(TreeNode node) {
		// In this case, we suppose that the heuristic is simply a constant value.
		return 0;
	}

	public ArrayList<Integer> solve() {
		// Initialize the data structures
		PriorityQueue<BreathFirstNode> open = new PriorityQueue<>(); // Open list
		HashMap<TreeNode, BreathFirstNode> allNodes = new HashMap<>(); // Map to reference nodes
		ArrayList<Integer> path = new ArrayList<>(); // Solution path from start to goal

		if (this.goal != "longestPath") {
			System.out.println("This method has not been implemented yet!");
			return path;
		}

		// Start node
		BreathFirstNode startNode = new BreathFirstNode(start, start.data, h(start), null);
		open.add(startNode);
		allNodes.put(start, startNode);

		// Closed nodes (already explored)
		PriorityQueue<BreathFirstNode> closed = new PriorityQueue<>();

		BreathFirstNode current;
		while (!open.isEmpty()) {
			// Extract the node with higher cost f
			current = open.poll();

			// Mark the current node as closed
			closed.add(current);

			// Explore the children
			for (TreeNode child : new TreeNode[] { current.node.leftChild, current.node.rightChild }) {
				if (child == null) {
					continue;
				}

				// Compute the cost g, h y f
				int g = current.g + f(current.node, child);
				int h = h(child);
				BreathFirstNode childNode = new BreathFirstNode(child, g, h, current);

				// If the child node is already in open list, verify if a better path is found
				BreathFirstNode existingNode = allNodes.get(child);
				if (existingNode == null || g > existingNode.g) {
					open.add(childNode);
					allNodes.put(child, childNode);
				}
			}
		}

		// If the goal is reached, reconstruct the path
		current = closed.peek();
		while (current != null) {
			path.addFirst(current.node.data);
			current = current.parent;
		}
		return path;
	}
}
