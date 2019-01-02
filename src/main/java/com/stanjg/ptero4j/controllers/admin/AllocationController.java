package com.stanjg.ptero4j.controllers.admin;

import com.stanjg.ptero4j.PteroAdminAPI;
import com.stanjg.ptero4j.entities.panel.admin.Allocation;
import org.json.JSONObject;

import java.io.StringWriter;
import java.util.List;

public class AllocationController extends ResourceController<Allocation> {

    public AllocationController(PteroAdminAPI api, String baseUrl, String key, int nodeId) {
        super( api, baseUrl, key, "nodes/" + nodeId + "/allocations" );
    }

    @Override
    protected Allocation getNewInstance(JSONObject json) {
        return new Allocation(getAdminAPI(), json);
    }
    public List<Allocation> getAllAllocations() {
        return super.getAllResources();
    }
}
