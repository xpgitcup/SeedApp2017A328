package cn.edu.cup.physical

/***
 * 物理量
 * */
class PhysicalQuantity {

    //字段定义
    String quantityName;
    String description;
    QuantityUnit isoUnit;

    //定义字段的顺序
    static constraints = {
        quantityName(unique: true);
        description();
        isoUnit(nullable: true);
    }

    //1对多关系定义
    static hasMany = [quantityUnits: QuantityUnit]

    static mapping = {
        sort('quantityName')
        sort('description')
        quantityUnits sort: 'factorA'
    }

    //
    String toString() {
        return "${quantityName}/${isoUnit}";
    }

}
