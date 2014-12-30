/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.treelayout;

import java.util.List;

/**
 *
 * @author Raazia
 */
public class Node {
    private int id;
    private String name;
    private List<Edge> ed;
    private double x;
    private double y;
        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getEd() {
        return ed;
    }

    public void setEd(List<Edge> ed) {
        this.ed = ed;
    }

}

