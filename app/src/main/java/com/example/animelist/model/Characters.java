package com.example.animelist.model;

import java.util.List;

public class Characters {

    private PageInfo pageInfo;
    private List<Edges> edges;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Edges> getEdges() {
        return edges;
    }

    public void setEdges(List<Edges> edges) {
        this.edges = edges;
    }
}
