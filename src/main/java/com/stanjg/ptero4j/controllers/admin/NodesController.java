package com.stanjg.ptero4j.controllers.admin;

import com.stanjg.ptero4j.PteroAdminAPI;
import com.stanjg.ptero4j.actions.admin.nodes.NodeUpdateAction;
import com.stanjg.ptero4j.entities.panel.admin.Allocation;
import com.stanjg.ptero4j.entities.panel.admin.Node;
import org.json.JSONObject;

import java.util.List;

public class NodesController extends ResourceController<Node> {

    private String baseUrl;
    private String key;

    public NodesController(PteroAdminAPI api, String baseURL, String key) {

        super(api, baseURL, key, "nodes");
        this.baseUrl = baseURL;
        this.key = key;
    }

    public NodeUpdateAction editNode(int id) {
        return new NodeUpdateAction(getAdminAPI(), id);
    }

    @Override
    protected Node getNewInstance(JSONObject json) {
        return new Node(getAdminAPI(), json);
    }

    public List<Node> getAllNodes() {
        return super.getAllResources();
    }

    public List<Node> getNodes(String search) {
        return super.getResourcesWithQuery(search);
    }

    public Node getNode(int id) {
        return super.getResource(id);
    }

    public List<Allocation> getNodeAllocation(int id) {
        AllocationController allocationController = new AllocationController(this.getAdminAPI(), baseUrl, key, id);

        return allocationController.getAllAllocations();
    }

    public List<Node> getNodePage(int page) {
        return super.getResourcesPage(page);
    }

    public boolean deleteNode(int id) {
        return super.delete(id);
    }

}
