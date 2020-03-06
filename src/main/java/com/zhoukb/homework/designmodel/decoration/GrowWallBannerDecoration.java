package com.zhoukb.homework.designmodel.decoration;

public class GrowWallBannerDecoration extends AbstractBannerDecoration {

    public GrowWallBannerDecoration(IBanner baseBanner) {
        super(baseBanner);
    }

    @Override
    public String getBanner() {
        return super.getBanner()+"[成长墙]";
    }
}
