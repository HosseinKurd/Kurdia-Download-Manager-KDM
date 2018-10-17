package com.hosseinkurd.kdm;

import java.io.File;

public interface OnDownload {
    void onDownloadSuccess(File file);
    void onDownloadFailed(Exception e);

    /**
     * RubnRun on UI Thread
     * @param totalSize Total Download Size
     * @param downloadedSize Currently Downloaded Size
     * @param per Download Progress percentage
     */
    void onDownloadProgress(int totalSize, int downloadedSize, float per);
}