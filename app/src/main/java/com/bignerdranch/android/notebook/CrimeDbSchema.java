package com.bignerdranch.android.notebook;

/**
 * Created by G__Agee on 2016/11/20.
 */
public class CrimeDbSchema {
    //内部类CrimeTable唯一用途是定义描述数据元素的String常量
    public static final class CrimeTable{
        public static final String NAME="crimes";

        public static final class Cols{
            public static final String UUID="uuid";
            public static final String TITLE="title";
            public static final String DATE="date";
            public static final String SOLVED="solved";
            public static final String SUSPECT="suspect";
        }
    }
}
