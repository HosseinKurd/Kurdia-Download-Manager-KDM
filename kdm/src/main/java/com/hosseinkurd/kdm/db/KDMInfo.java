package com.hosseinkurd.kdm.db;

public class KDMInfo {
    private int id;
    private String tag;
    private String url;
    private String status;
    private long start;
    private long end;
    private long finished;

    public KDMInfo() {

    }

    public KDMInfo(int id, String tag, String url, String status) {
        this.id = id;
        this.tag = tag;
        this.url = url;
        this.status = status;
    }

    public KDMInfo(int id, String tag, String url, String status, long start, long end, long finished) {
        this.id = id;
        this.tag = tag;
        this.url = url;
        this.status = status;
        this.start = start;
        this.end = end;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public KDMInfo setId(int id) {
        this.id = id;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public KDMInfo setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public KDMInfo setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public KDMInfo setStatus(String status) {
        this.status = status;
        return this;
    }

    public long getStart() {
        return start;
    }

    public KDMInfo setStart(long start) {
        this.start = start;
        return this;
    }

    public long getEnd() {
        return end;
    }

    public KDMInfo setEnd(long end) {
        this.end = end;
        return this;
    }

    public long getFinished() {
        return finished;
    }

    public KDMInfo setFinished(long finished) {
        this.finished = finished;
        return this;
    }
}
