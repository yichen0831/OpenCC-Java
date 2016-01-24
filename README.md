# OpenCC-Java

## Introduction 簡介
OpenCC-Java is made by Java with the dictionary files of [OpenCC](https://github.com/BYVoid/OpenCC) which is developed by BYVoid(<byvoid.kcp@gmail.com>).

OpenCC-Java是用Java所寫, 使用由BYVoid(<byvoid.kcp@gmail.com>)所開發的[OpenCC](https://github.com/BYVoid/OpenCC)中的字典檔案.


## Dependencies 依賴套件
[json-simple](https://code.google.com/p/json-simple/) is used for parsing json files

使用[json-simple](https://code.google.com/p/json-simple/)解析json檔案

## Installation 安裝
Download the jar file from the [release](https://github.com/yichen0831/OpenCC-Java/releases) page and add it to the project build path.

從[release](https://github.com/yichen0831/OpenCC-Java/releases)下載jar檔案, 加到project build path.

## Usage 使用方式

    import opencc.OpenCC
    
    public class Sample {
        public static void main(String[] args) {
            // use default conversion "s2t", convert from Simplified Chinese to Traditional Chinese
            OpenCC openCC = new OpenCC();
            
            // can also set conversion when constructing
            // OpenCC openCC = new OpenCC("s2tw"); // convert from Simplified Chinese to Traditional Chinese (Taiwan Standard)
            
            // also can set a new conversion when needed
            // opencCC.setConversion("s2hk");
            
            String toConvert = "开放中文转换";
            String converted = openCC.convert(toConvert);
        }
    }


Conversions include 轉換包含:

'hk2s': Traditional Chinese (Hong Kong standard) to Simplified Chinese

's2hk': Simplified Chinese to Traditional Chinese (Hong Kong standard)

's2t': Simplified Chinese to Traditional Chinese

's2tw': Simplified Chinese to Traditional Chinese (Taiwan standard)

's2twp': Simplified Chinese to Traditional Chinese (Taiwan standard, with phrases)

't2hk': Traditional Chinese to Traditional Chinese (Hong Kong standard)

't2s': Traditional Chinese to Simplified Chinese

't2tw': Traditional Chinese to Traditional Chinese (Taiwan standard)

'tw2s': Traditional Chinese (Taiwan standard) to Simplified Chinese

'tw2sp': Traditional Chinese (Taiwan standard) to Simplified Chinese (with phrases)

## Issues 問題
When there are more than one conversion avaiable, only the first one is taken.

當轉換有兩個以上的字詞可能時, 程式只會使用第一個.
