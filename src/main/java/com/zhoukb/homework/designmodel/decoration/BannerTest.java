package com.zhoukb.homework.designmodel.decoration;

public class BannerTest {

    public static void main(String[] args) {
        BaseBanner baseBanner = new BaseBanner();
        System.out.println(new BannerTest().getBanner(true));
    }

    public String getBanner(Boolean isLogin) {
        BaseBanner baseBanner = new BaseBanner();
        if (isLogin){
            GrowWallBannerDecoration banner1 = new GrowWallBannerDecoration(baseBanner);
            HomeWorkBannerDecoration banner2 = new HomeWorkBannerDecoration(banner1);
            QuestionBannerDecoration banner3 = new QuestionBannerDecoration(banner2);
            return banner3.getBanner();
        }else{
            return baseBanner.getBanner();
        }
    }
}
