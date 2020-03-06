package com.zhoukb.homework.designmodel.decoration;

abstract class AbstractBannerDecoration implements IBanner{

    private IBanner baseBanner;

    public AbstractBannerDecoration(IBanner baseBanner) {
        this.baseBanner = baseBanner;
    }

    @Override
    public String getBanner() {
        return baseBanner.getBanner();
    }

}
