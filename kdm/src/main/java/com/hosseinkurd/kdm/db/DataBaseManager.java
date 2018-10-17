package com.hosseinkurd.kdm.db;

import android.content.Context;

import java.util.List;

public class DataBaseManager {

    private static DataBaseManager sDataBaseManager;
    private final KDMInfoDao mKDMInfoDao;

    public static DataBaseManager getInstance(Context context) {
        if (sDataBaseManager == null) {
            sDataBaseManager = new DataBaseManager(context);
        }
        return sDataBaseManager;
    }

    private DataBaseManager(Context context) {
        mKDMInfoDao = new KDMInfoDao(context);
    }

    public synchronized void insert(KDMInfo KDMInfo) {
        mKDMInfoDao.insert(KDMInfo);
    }

    public synchronized void delete(String tag) {
        mKDMInfoDao.delete(tag);
    }

    public synchronized void update(String tag, int threadId, long finished) {
        mKDMInfoDao.update(tag, threadId, finished);
    }

    public List<KDMInfo> getThreadInfos(String tag) {
        return mKDMInfoDao.getThreadInfos(tag);
    }

    public List<KDMInfo> getThreadInfos(){
        return mKDMInfoDao.getThreadInfos();
    }

    public boolean exists(String tag, int threadId) {
        return mKDMInfoDao.exists(tag, threadId);
    }
}
