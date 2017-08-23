package gank.hyx.com.gank.network.model;

import java.util.ArrayList;

/**
 * Created by Black.C on 2017/8/2.
 */

public class CommonData {

    private boolean error;
    private ArrayList<Data> results = new ArrayList<>();

    public CommonData() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<Data> getResults() {
        return results;
    }

    public void setResults(ArrayList<Data> results) {
        this.results = results;
    }

    public static class Data {

        private String _id;
        private String createdAt;
        private String desc;
        private ArrayList<String> images = new ArrayList<>();
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public Data() {
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            if (_id != null){

            }
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            if (createdAt != null){
                this.createdAt = createdAt;
            }
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            if (desc != null){
                this.desc = desc;
            }
        }

        public ArrayList<String> getImages() {
            return images;
        }

        public void setImages(ArrayList<String> images) {
            if (images != null){
                this.images = images;
            }
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            if (publishedAt != null){
                this.publishedAt = publishedAt;
            }
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            if (source != null){
                this.source = source;
            }
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            if (type != null){
                this.type = type;
            }
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            if (url != null){
                this.url = url;
            }
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
                this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            if (who != null){
                this.who = who;
            }
        }
    }


}
