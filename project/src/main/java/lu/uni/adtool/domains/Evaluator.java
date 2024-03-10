/**
 * Author: Piotr Kordy (piotr.kordy@uni.lu <mailto:piotr.kordy@uni.lu>)
 * Date:   10/12/2015
 * Copyright (c) 2015,2013,2012 University of Luxembourg -- Faculty of Science,
 *     Technology and Communication FSTC
 * All rights reserved.
 * Licensed under GNU Affero General Public License 3.0;
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Affero General Public License as
 *    published by the Free Software Foundation, either version 3 of the
 *    License, or (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Affero General Public License for more details.
 *
 *    You should have received a copy of the GNU Affero General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package lu.uni.adtool.domains;

import lu.uni.adtool.domains.rings.Ring;
import lu.uni.adtool.tree.ADTNode;
import lu.uni.adtool.tree.Node;
import lu.uni.adtool.tree.SandNode;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Evaluate value of each node for given domain and value assignement map. Works
 * with both SandDomain and AtdDomain
 *
 * @author Piotr Kordy
 */
public class Evaluator<Type extends Ring> {
  /**
   * Constructs a new instance.
   *
   */

  public Evaluator(AdtDomain<Type> domain) {
    this.resultMap = new HashMap<>();
    this.atdDomain = domain;
    this.sandDomain = null;
  }

  public Evaluator(SandDomain<Type> domain) {
    resultMap = null;
    this.atdDomain = null;
    this.sandDomain = domain;
  }

  /**
   * Returns previously calculated value for a given node.
   *
   * @param node
   *             a node
   * @return value at a node
   */
  public final Type getValue(final Node node) {
    if (resultMap == null || node == null) {
      return null;
    } else {
      return resultMap.get(node);
    }
  }

  /**
   * Do bottom up evaluation.
   *
   * @param root
   *               node from which we do evaluation
   * @param newmap
   *               mapping between node names and values.
   * @return true if evaluation was successful.
   */
  public final Type reevaluate(final ADTNode root, final ValueAssignement<Type> valuesMap) {
    // Use collectSelectedNodes to get only selected nodes

    if (valuesMap == null) {
      System.err.println("NULL result");
      return null;
    }
    resultMap.clear();
    return this.evaluate(root, valuesMap);
  }

  public final Type reevaluate(final SandNode root, final ValueAssignement<Type> valueAssigment) {
    if (valueAssigment == null || root == null) {
      System.err.println("NULL result");
      return null;
    }
    resultMap = new HashMap<Node, Type>();
    return evaluate(root, valueAssigment);
  }

  /**
   * Do bottom up evaluation.
   *
   * @param root
   * @return
   */
  private Type evaluate(final ADTNode root, ValueAssignement<Type> valuesMap) {
    if (!anyHighlightedLeafExists(root)) {
      System.out.println("No highlighted nodes found. Performing standard evaluation.");
      return evaluateNode(root, valuesMap);
    } else {
      System.out.println("Highlighted nodes found. Evaluating only highlighted nodes.");
      return evaluateHighlightedNodes(root, valuesMap);
    }
  }

  private Type evaluateNode(final ADTNode node, ValueAssignement<Type> valuesMap) {
    Type result = null;
    if (node.hasDefault()) {
      result = valuesMap.get(node.getRole() == ADTNode.Role.PROPONENT, node.getName());
      if (result == null) {
        result = atdDomain.getDefaultValue(node);
      }
    } else {
      for (Node child : node.getChildren()) {
        Type childResult = evaluateNode((ADTNode) child, valuesMap);
        result = result == null ? childResult : atdDomain.calc(result, childResult, node.getType());
      }
    }
    if (node.isCountered()) {
      Type counterResult = evaluateNode((ADTNode) node.getChildren().get(node.getChildren().size() - 1), valuesMap);
      result = node.getRole() == ADTNode.Role.OPPONENT ? atdDomain.co(result, counterResult)
          : atdDomain.cp(result, counterResult);
    }
    resultMap.put(node, result);
    return result;
  }

  private boolean anyHighlightedLeafExists(final ADTNode node) {
    if (node.isLeaf() && node.isSelected())
      return true;
    for (Node child : node.getChildren()) {
      if (child instanceof ADTNode && anyHighlightedLeafExists((ADTNode) child)) {
        return true;
      }
    }
    return false;
  }

  private Type evaluateHighlightedNodes(final ADTNode node, ValueAssignement<Type> valuesMap) {
    if (!node.isSelected() && !node.isLeaf())
      return null; // Skip non-highlighted non-leaf nodes

    Type result = null;
    if (node.hasDefault() && node.isSelected()) {
      result = valuesMap.get(node.getRole() == ADTNode.Role.PROPONENT, node.getName());
      if (result == null) {
        result = atdDomain.getDefaultValue(node);
      }
    } else {
      for (Node child : node.getChildren()) {
        if (child instanceof ADTNode && child.isSelected()) {
          ADTNode adtChild = (ADTNode) child;
          Type childResult = evaluateHighlightedNodes(adtChild, valuesMap);
          if (childResult != null) {
            result = result == null ? childResult : atdDomain.calc(result, childResult, node.getType());
          }
        }
      }
    }

    if (node.isCountered() && node.isSelected()) {
      ADTNode counterChild = (ADTNode) node.getChildren().get(node.getChildren().size() - 1);
      if (counterChild.isSelected()) {
        Type counterResult = evaluateHighlightedNodes(counterChild, valuesMap);
        result = node.getRole() == ADTNode.Role.OPPONENT ? atdDomain.co(result, counterResult)
            : atdDomain.cp(result, counterResult);
      }
    }

    if (node.isSelected()) {
      resultMap.put(node, result); // Store results for highlighted nodes
    }
    return result;
  }

  private Type evaluate(final SandNode root, ValueAssignement<Type> map) {
    Type result = null;
    if (root.isLeaf()) {
      result = map.get(true, root.getName());
      if (result == null) {
        result = sandDomain.getDefaultValue(root);
      }
    } else {
      for (Node child : root.getChildren()) {
        if (result == null) {
          result = evaluate((SandNode) child, map);
        } else {
          result = sandDomain.calc(result, evaluate((SandNode) child, map), root.getType());
        }
      }
    }
    resultMap.put(root, result);
    return result;
  }

  private HashMap<Node, Type> resultMap;
  private AdtDomain<Type> atdDomain;
  private SandDomain<Type> sandDomain;
}