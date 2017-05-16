package com.lenovohit.administrator.english.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017-05-13.
 */
@Entity
public class MyWord {
   public String word;
    String trans;
    String phonetic;
   @Generated(hash = 1735538669)
public MyWord(String word, String trans, String phonetic) {
    this.word = word;
    this.trans = trans;
    this.phonetic = phonetic;
}

@Generated(hash = 1120453050)
   public MyWord() {
   }

   public String getWord() {
       return this.word;
   }

   public void setWord(String word) {
       this.word = word;
   }

public String getTrans() {
    return this.trans;
}

public void setTrans(String trans) {
    this.trans = trans;
}

public String getPhonetic() {
    return this.phonetic;
}

public void setPhonetic(String phonetic) {
    this.phonetic = phonetic;
}


}
