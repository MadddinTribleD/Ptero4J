package com.stanjg.ptero4j.entities.panel.admin;

import com.stanjg.ptero4j.PteroAdminAPI;
import org.json.JSONObject;

public class Allocation {
    PteroAdminAPI api;

    private int port;
    private String ip;
    private String alias;
    private boolean assigned;
    private int id;


    public Allocation(PteroAdminAPI api, JSONObject json) {
        this(api,
             json.getInt("port"),
             json.getString("ip"),
             json.getString("alias"),
             json.getBoolean("assigned"),
             json.getInt("id")
             );
    }

    public Allocation(PteroAdminAPI api, int port, String ip, String alias, boolean assigned, int id) {
        this.api = api;
        this.port = port;
        this.ip = ip;
        this.alias = alias;
        this.assigned = assigned;
        this.id = id;
    }

    public int getPort() { return this.port; }
    public String getIp() { return this.ip; }
    public String getAlias() { return this.alias; }
    public boolean getAssigned() { return this.assigned; }
    public int getId() { return this.id; }
}
