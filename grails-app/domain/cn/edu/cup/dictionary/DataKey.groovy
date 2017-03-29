package cn.edu.cup.dictionary

import cn.edu.cup.physical.QuantityUnit

class DataKey {

    String keyContext                //标题
    String description               //说明
    QuantityUnit quantityUnit        //单位
    Boolean isNullable               //是否可以为空
    DataKey upKey                     //上级关键字
    BaseDataType dataValueType       //数据类型
    String appendValue               //附加数据
    Boolean importFromFile          //从文件中导入

    static hasMany = [subKeys: DataKey]

    static constraints = {
        keyContext(unique: false)
        description(nullable: true)
        quantityUnit(nullable: true)
        isNullable(nullable: true)
        dataValueType()
        appendValue(nullable: true)
        upKey(nullable: true)
        importFromFile(nullable: true)
    }

    static mapping = {
        //sort('dataValueType')
        //sort('keyContext')
        subKeys sort: 'keyContext', 'id'  //这是排序的标准做法
    }

    def processAppendValue() {

    }

    //处理appendValue, 根据值的类型规范appendValue的数值
    def beforeInsert() {
        //processAppendValue()
    }

    def beforeUpdate() {
        //processAppendValue()
    }

    String toString() {
        return "${id}/${keyContext}"
    }

    Boolean isLeaf() {
        return (subKeys == null)
    }

}
