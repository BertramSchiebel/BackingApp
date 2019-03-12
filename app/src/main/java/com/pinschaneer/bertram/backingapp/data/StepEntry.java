package com.pinschaneer.bertram.backingapp.data;

import org.json.JSONException;
import org.json.JSONObject;

public class StepEntry
{
    private final String BJS_ID = "id";
    private final String BJS_SHORT_DESCRIPTION = "shortDescritpion";
    private final String BJS_DESCRIPTION = "descritpion";
    private final String BJS_VIDEO_URL = "videoURL";
    private final String BJS_THUMBNAIL_URL = "thumbnailURL";

    private int id;
    private String shortDescritpion;
    private String descritpion;
    private String videoURL;
    private String thumbnailURL;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getShortDescritpion() {
        return shortDescritpion;
    }

    private void setShortDescritpion(String shortDescritpion) {
        this.shortDescritpion = shortDescritpion;
    }

    public String getDescritpion() {
        return descritpion;
    }

    private void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getVideoURL() {
        return videoURL;
    }

    private void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    private void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public StepEntry getStepEntry(String jsonData) {
        StepEntry entry = new StepEntry();
        try {
            JSONObject backingDataJSON = new JSONObject(jsonData);
            if (backingDataJSON.has(BJS_ID)) {
                entry.setId(backingDataJSON.getInt(BJS_ID));
            }
            if (backingDataJSON.has(BJS_DESCRIPTION)) {
                entry.setDescritpion(backingDataJSON.getString(BJS_DESCRIPTION));
            }
            if (backingDataJSON.has(BJS_SHORT_DESCRIPTION)) {
                entry.setShortDescritpion(backingDataJSON.getString(BJS_SHORT_DESCRIPTION));
            }
            if (backingDataJSON.has(BJS_THUMBNAIL_URL)) {
                entry.setThumbnailURL(backingDataJSON.getString(BJS_THUMBNAIL_URL));
            }
            if (backingDataJSON.has(BJS_VIDEO_URL)) {
                entry.setVideoURL(backingDataJSON.getString(BJS_VIDEO_URL));
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return entry;
    }
}
