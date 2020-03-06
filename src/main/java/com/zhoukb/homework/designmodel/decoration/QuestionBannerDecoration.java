package com.zhoukb.homework.designmodel.decoration;

public class QuestionBannerDecoration extends AbstractBannerDecoration {

    public QuestionBannerDecoration(IBanner baseBanner) {
        super(baseBanner);
    }

    @Override
    public String getBanner() {
        return super.getBanner() + "[题库]";
    }
}
