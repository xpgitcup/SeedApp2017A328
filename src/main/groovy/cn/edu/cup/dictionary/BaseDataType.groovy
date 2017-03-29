package cn.edu.cup.dictionary

/**
 * Created by LiXiaoping on 2017/1/22.
 * 拆成两个枚举类，可能不好管理
 * 项目>算例>数学模型>引用数据>简单数据>枚举、数组（一维、二维、多维）>简单数据
 * 导入数据——一维、二维、多维、图片数据
 * 导入数据--附加数据如果是文件名，就是从文件中导入。
 */
enum BaseDataType {

    project,
    projectCase,
    dataModel,
    dataModelRef,           //模型引用
    enumData,
    arrayData,
    simpleData
}