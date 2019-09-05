package com.example.ja.kalkulatorwydatkw;

public class ExampleItem {
    private String mText1;
    private String mText2;
    private String mText3;
    private String mText_id;

    public ExampleItem(String text1, String text2, String text3, String Text_id) {
        mText1 = text1;
        mText2 = text2;
        mText3 = text3;
        mText_id = Text_id;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    public String getText3() {
        return mText3;
    }

    public String getText_id()
    {
        return mText_id;
    }
}
