package com.fbudreamteam.android.MinePost.model;

import java.util.List;

public interface QuizLoadedCallback {

    void onQuestionsLoaded(List<ParseQuizQuestion> questions);

}
