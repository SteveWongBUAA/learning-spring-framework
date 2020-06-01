package com.vava.distlock;

/**
 * @author Steve
 * Created on 2020-04
 */
public class DistLockRepo {
    private int id;
    private String resourceName;
    private String nodeInfo;
    private long createAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(String nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "DistLockRepo{" +
                "id=" + id +
                ", resourceName='" + resourceName + '\'' +
                ", nodeInfo='" + nodeInfo + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
