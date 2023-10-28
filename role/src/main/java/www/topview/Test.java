package www.topview;

import cn.hutool.core.io.file.FileReader;

public class Test {
    public static void main(String[] args) {
        FileReader privateKey = new FileReader("cpt_template/cpt001.json");
        System.out.printf(privateKey.readString());
    }
}
