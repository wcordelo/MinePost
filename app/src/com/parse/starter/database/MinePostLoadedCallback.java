package com.parse.starter.database;

import java.util.List;

/**
 * Created by FBU Dream Team / William on 7/10/15.
 */
public interface MinePostLoadedCallback {
    void onMinePostsLoaded(List<MinePost> minePosts);
}
