package com.pinschaneer.bertram.backingapp.data;

import org.json.JSONException;
import org.json.JSONObject;

public class StepEntry
{
    private static final String BJS_ID = "id";
    private static final String BJS_SHORT_DESCRIPTION = "shortDescription";
    private static final String BJS_DESCRIPTION = "description";
    private static final String BJS_VIDEO_URL = "videoURL";
    private static final String BJS_THUMBNAIL_URL = "thumbnailURL";

    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    static StepEntry getStepEntry(JSONObject jsonData) {
        StepEntry entry = new StepEntry();
        try {
            if (jsonData.has(BJS_ID)) {
                entry.setId(jsonData.getInt(BJS_ID));
            }
            if (jsonData.has(BJS_DESCRIPTION)) {
                entry.setDescription(jsonData.getString(BJS_DESCRIPTION));
            }
            if (jsonData.has(BJS_SHORT_DESCRIPTION)) {
                entry.setShortDescription(jsonData.getString(BJS_SHORT_DESCRIPTION));
            }
            if (jsonData.has(BJS_THUMBNAIL_URL)) {
                entry.setThumbnailURL(jsonData.getString(BJS_THUMBNAIL_URL));
            }
            if (jsonData.has(BJS_VIDEO_URL)) {
                entry.setVideoURL(jsonData.getString(BJS_VIDEO_URL));
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return entry;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    private void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
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

    private void setDescription(String description) {
        this.description = description;
    }
}
