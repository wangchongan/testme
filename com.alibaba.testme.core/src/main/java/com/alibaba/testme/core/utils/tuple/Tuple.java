package com.alibaba.testme.core.utils.tuple;

public class Tuple {

    public static <A, B> Tuple2<A, B> tuple2(A a, B b) {
        return new Tuple2<A, B>(a, b);
    }

    public static <A, B, C> Tuple3<A, B, C> tuple3(A a, B b, C c) {
        return new Tuple3<A, B, C>(a, b, c);
    }

}
