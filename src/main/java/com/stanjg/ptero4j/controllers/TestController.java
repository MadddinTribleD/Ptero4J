package com.stanjg.ptero4j.controllers;

import com.stanjg.ptero4j.PteroAdminAPI;
import com.stanjg.ptero4j.util.HTTPMethod;
import com.stanjg.ptero4j.util.PteroUtils;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TestController extends Controller {

    public TestController(PteroAdminAPI api, String baseURL, String key) {
        super(api, baseURL, key);
    }

    public boolean testConnection() throws IOException {
        Response response = makeApiCall("/users", HTTPMethod.GET);


        switch (response.code()) {

            case 200:
                if(!this.CheckResponse(response)) {
                    break;
                }

                PteroUtils.log("Ptero4J loaded! Successfully made contact with the panel.");
                return true;
            case 401:
                PteroUtils.log("Ptero4J failed to load! Unable to authenticated. Your key might be invalid.");
                break;
            case 403:
                PteroUtils.log("Mixed: not authorized to access /users, no permission.");
                break;
            case 404:
                PteroUtils.log("Ptero4J failed to load! An invalid URL was provided.");
                break;
            case 500:
                PteroUtils.log("An error occurred on the panel side, please check panel logs/");
                break;
        }

        return false;
    }

    public boolean testUserConnection() throws IOException {
        Response response = makeApiCall("/", HTTPMethod.GET);

        switch (response.code()) {

            case 200:
                if(!this.CheckResponse(response)) {
                    break;
                }

                PteroUtils.log("Ptero4J loaded! Successfully made contact with the panel.");
                return true;
            case 401:
                PteroUtils.log("Ptero4J failed to load! Unable to authenticated. Your key might be invalid.");
                break;
            case 403:
                PteroUtils.log("Not authorized.");
                break;
            case 404:
                PteroUtils.log("Ptero4J failed to load! An invalid URL was provided.");
                break;
            case 500:
                PteroUtils.log("An error occurred on the panel side, please check panel logs/");
                break;
        }

        return false;
    }

    private boolean CheckResponse(Response response) throws IOException {
        try {
            JSONObject json = new JSONObject(response.body().string());

            if(!(json.has("meta") && json.has("object") && json.has("data"))) {
                PteroUtils.log("Ptero4J failed to load! Response has not the expected data.");
                return false;
            }
        } catch (JSONException exception) {
            PteroUtils.log("Ptero4J failed to load! Response was not a valid JSON.");
            return false;
        }

        return true;
    }
}
