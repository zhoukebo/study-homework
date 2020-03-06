package com.zhoukb.homework.designmodel.decoration;

public class HomeWorkBannerDecoration extends AbstractBannerDecoration {

    public HomeWorkBannerDecoration(IBanner baseBanner) {
        super(baseBanner);
    }

    @Override
    public String getBanner() {
        return super.getBanner()+"[作业]";
    }
}
