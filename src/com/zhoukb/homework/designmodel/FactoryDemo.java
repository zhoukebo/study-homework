package com.zhoukb.homework.designmodel;

public class FactoryDemo {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        NativePaymentFactory nativePaymentFactory = new NativePaymentFactory();
        NativePayment payment = nativePaymentFactory.createPayment(AliPay.class);
        payment.pay();
    }
}

interface Payment{
    void pay();
}

interface NativePayment extends Payment{
    void nativeInit();
}

interface OutPayment extends Payment{
    void outInit();
}

class ApplePay implements OutPayment{
    @Override
    public void pay() {
        System.out.println("跨境苹果支付");
    }

    @Override
    public void outInit() {
        System.out.println("苹果支付初始化");
    }
}

class AliPay implements NativePayment{
    @Override
    public void pay() {
        System.out.println("使用支付宝支付。");
    }

    @Override
    public void nativeInit() {
        System.out.println("支付宝支付初始化");
    }
}

class WeChatPay implements  NativePayment{
    @Override
    public void pay() {
        System.out.println("使用微信支付。");
    }

    @Override
    public void nativeInit() {
        System.out.println("微信支付初始化");
    }
}

class unionPay implements NativePayment{
    @Override
    public void pay() {
        System.out.println("使用银联支付。");
    }

    @Override
    public void nativeInit() {
        System.out.println("银联支付初始化");
    }
}

interface IPaymentFactory{
    Payment createPayment(Class clazz) throws IllegalAccessException, InstantiationException;
}

class NativePaymentFactory implements IPaymentFactory{

    @Override
    public NativePayment createPayment(Class clazz) throws IllegalAccessException, InstantiationException {
        if (NativePayment.class.isAssignableFrom(clazz)){
            Class<NativePayment> aClass = (Class<NativePayment>)clazz;
            return aClass.newInstance();
        }else{
            throw new RuntimeException("传入参数不正确");
        }
    }
}
class OutPaymentFactory implements IPaymentFactory{
    @Override
    public OutPayment createPayment(Class clazz) throws IllegalAccessException, InstantiationException {
        if (OutPayment.class.isAssignableFrom(clazz)){
            Class<OutPayment> aClass = (Class<OutPayment>)clazz;
            return aClass.newInstance();
        }else{
            throw new RuntimeException("传入参数不正确");
        }
    }
}
